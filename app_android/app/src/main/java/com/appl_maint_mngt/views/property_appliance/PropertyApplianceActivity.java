package com.appl_maint_mngt.views.property_appliance;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.FormatException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.event_bus.common.LocalEventBus;
import com.appl_maint_mngt.event_bus.common.property_appliance_status_update.IPropertyApplianceStatusUpdateEvents;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.models.account.constants.UserType;
import com.appl_maint_mngt.models.appliance.IApplianceReadable;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;
import com.appl_maint_mngt.models.appliance_status.StatusType;
import com.appl_maint_mngt.models.diagnostic_report.IDiagnosticReportReadable;
import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;
import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;
import com.appl_maint_mngt.repositories.appliance.IApplianceObserverUpdateTypes;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusObserverUpdateTypes;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportObserverUpdateTypes;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.views.diagnostic_report.DiagnosticReportListAdapter;
import com.appl_maint_mngt.views.diagnostic_report.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.views.diagnostic_request.DiagnosticRequestsActivity;
import com.appl_maint_mngt.views.nfc.NFCActivity;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_report.DiagnosticReportGeneraterActivity;
import com.appl_maint_mngt.views.property_appliance_status_update.SendApplianceStatusUpdateDialog;
import com.appl_maint_mngt.views.repair_report.IRepairReportViewConstants;
import com.appl_maint_mngt.views.repair_report.RepairReportActivity;
import com.appl_maint_mngt.web.models.property_appliance_status_update.PropertyApplianceStatusUpdatePayload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class PropertyApplianceActivity extends NFCActivity implements Observer {

    private IPropertyApplianceReadable propertyAppliance;

    private TextView currentStatusLblTv;
    private Button generateDiagnosticReportBtn;

    private StatusHistoryListAdapter statusHistoryListAdapter;
    private ListView statusHistoryListView;

    private ListView diagRepListView;
    private DiagnosticReportListAdapter diagReportAdapter;

    private IAccountReadable account;

    private boolean updateStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_appliance);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long propertyApplianceId = extras.getLong(IPropertyApplianceViewConstants.ID_KEY);
            updateStatus = extras.getBoolean(IPropertyApplianceViewConstants.UPDATE_STATUS_KEY, false);
            propertyAppliance = RepositoryFactory.getInstance().getReadablePropertyApplianceRepository().getForId(propertyApplianceId);
        }

        account = RepositoryFactory.getInstance().getReadableAccountRepository().get();
        RepositoryFactory.getInstance().observeApplianceStatusRepository(this);
        RepositoryFactory.getInstance().observeApplianceRepository(this);

        setupPropertyApplianceView();

        ControllerFactory.getInstance().getApplianceStatusController().getForStatusId(propertyAppliance.getStatusId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
            }
        });

        ControllerFactory.getInstance().getApplianceStatusController().getForStatusHistory(propertyAppliance.getStatusHistory(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
            }
        });

        ControllerFactory.getInstance().getApplianceController().getForId(propertyAppliance.getApplianceId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
            }
        });

        statusHistoryListAdapter = new StatusHistoryListAdapter(this, new ArrayList<IStatusHistoryReadable>());
        statusHistoryListView = (ListView) findViewById(R.id.propertyappliance_listview_stathistory_list);
        statusHistoryListView.setAdapter(statusHistoryListAdapter);

        generateDiagnosticReportBtn = (Button) findViewById(R.id.propertyappliance_button_gendiagrep);
        generateDiagnosticReportBtn.setVisibility(View.INVISIBLE);
        generateDiagnosticReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diagIntent = new Intent(PropertyApplianceActivity.this, DiagnosticReportGeneraterActivity.class);
                diagIntent.putExtra(IPropertyApplianceViewConstants.ID_KEY, propertyAppliance.getId());
                startActivity(diagIntent);
            }
        });

        Button nfgTagBtn = (Button) findViewById(R.id.propertyappliance_button_writetag);
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            nfgTagBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        writeToTag(String.valueOf(propertyAppliance.getId()));
                        Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_success_write, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_err_cant_write, Toast.LENGTH_LONG).show();
                    } catch (FormatException e) {
                        Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_err_cant_write, Toast.LENGTH_LONG).show();
                    } catch(NullPointerException e) {
                        Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_err_cant_write, Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            nfgTagBtn.setVisibility(View.INVISIBLE);
        }

        diagRepListView = (ListView) findViewById(R.id.propertyappliance_diagreport_listview);
        diagReportAdapter = new DiagnosticReportListAdapter(this, new ArrayList<IDiagnosticReportReadable>());
        diagRepListView.setAdapter(diagReportAdapter);
        RepositoryFactory.getInstance().observeDiagnosticReportRepository(this);
        ControllerFactory.getInstance().getDiagnosticReportController().getForPropertyAppliance(propertyAppliance.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
            }
        });
        diagRepListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IDiagnosticReportReadable diagRep = (IDiagnosticReportReadable) parent.getItemAtPosition(position);
                if(RepositoryFactory.getInstance().getReadableRepairReportRepository().getForDiagnosticReportId(diagRep.getId()) != null) {
                    Intent intent = new Intent(PropertyApplianceActivity.this, RepairReportActivity.class);
                    intent.putExtra(IRepairReportViewConstants.ID_KEY, RepositoryFactory.getInstance().getReadableRepairReportRepository().getForDiagnosticReportId(diagRep.getId()).getId());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PropertyApplianceActivity.this, DiagnosticRequestsActivity.class);
                    intent.putExtra(IDiagnosticReportViewConstants.ID_KEY, diagRep.getId());
                    startActivity(intent);
                }
            }
        });
    }

    private void setupPropertyApplianceView() {
        TextView nameTitleTv = (TextView) findViewById(R.id.propertyappliance_textview_title);
        nameTitleTv.setText(propertyAppliance.getName());

        TextView ageValueTv = (TextView) findViewById(R.id.propertyappliance_textview_age_value);
        ageValueTv.setText(String.format(Locale.ENGLISH, "%d", propertyAppliance.getAge()));

        currentStatusLblTv = (TextView) findViewById(R.id.propertyappliance_textview_status_label);
        currentStatusLblTv.setVisibility(View.INVISIBLE);
    }

    private void setupApplianceView() {
        IApplianceReadable appliance = RepositoryFactory.getInstance().getReadableApplianceRepository().get(propertyAppliance.getApplianceId());

        TextView productNoTv = (TextView) findViewById(R.id.propertyappliance_textview_appl_productno_value);
        productNoTv.setText(appliance.getProductNo());

        TextView nameTv = (TextView) findViewById(R.id.propertyappliance_textview_appl_name_value);
        nameTv.setText(appliance.getName());

        TextView brandTv = (TextView) findViewById(R.id.propertyappliance_textview_appl_brand_value);
        brandTv.setText(appliance.getBrand());

        TextView typeTv = (TextView) findViewById(R.id.propertyappliance_textview_appl_type_value);
        typeTv.setText(appliance.getType().toString());
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IApplianceStatusObserverUpdateTypes.CURRENT_STATUS_UPDATE)) {
                IApplianceStatusReadable applStat = RepositoryFactory.getInstance().getReadableApplianceStatusRepository().getForId(propertyAppliance.getStatusId());
                currentStatusLblTv.setVisibility(View.VISIBLE);
                TextView statusTv = (TextView) findViewById(R.id.propertyappliance_textview_status_value);
                statusTv.setText(applStat.getType().toString());
                if(!applStat.getType().equals(StatusType.OKAY) && account.getUserType().equals(UserType.PROPERTY_MANAGER)) generateDiagnosticReportBtn.setVisibility(View.VISIBLE);
            } else if(o.equals(IApplianceStatusObserverUpdateTypes.STATUS_HISTORY_UPDATE)) {
                statusHistoryListAdapter.clear();
                statusHistoryListAdapter.addAll(propertyAppliance.getStatusHistory());
                statusHistoryListAdapter.notifyDataSetChanged();
            } else if(o.equals(IApplianceObserverUpdateTypes.MODEL_UPDATE)) {
                setupApplianceView();
                if(updateStatus) {
                    prepareForStatusUpdate();
                }
            } else if(o.equals(IApplianceStatusObserverUpdateTypes.TYPES_UPDATE)) {
                if(updateStatus) {
                    updateStatus();
                    updateStatus = false;
                }
            } else if(o.equals(IPropertyApplianceStatusUpdateEvents.SUCCESS_EVENT)) {
                Toast.makeText(this, R.string.prop_appl_stat_update_success, Toast.LENGTH_LONG).show();
                ControllerFactory.getInstance().getPropertyApplianceController().getPropertyAppliancesForProperty(propertyAppliance.getPropertyId(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
                    }
                });
            } else if (o.equals(IDiagnosticReportObserverUpdateTypes.MODEL_UPDATE)) {
                List<IDiagnosticReportReadable> reports = RepositoryFactory.getInstance().getReadableDiagnosticReportRepository().getAll();
                List<Long> reportIds = new ArrayList<>();
                for(IDiagnosticReportReadable rep: reports) {
                    reportIds.add(rep.getId());
                }
                System.out.println(Arrays.toString(reportIds.toArray()));
//                ControllerFactory.getInstance().getRepairReportController().getForDiagnosticIds(reportIds, new IErrorCallback() {
//                    @Override
//                    public void callback(ErrorPayload payload) {
//                        new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
//                    }
//                });
                diagReportAdapter.clear();
                diagReportAdapter.addAll(RepositoryFactory.getInstance().getReadableDiagnosticReportRepository().getAll());
                diagReportAdapter.notifyDataSetChanged();
            }
        }
    }

    private void prepareForStatusUpdate() {
        IApplianceReadable appliance = RepositoryFactory.getInstance().getReadableApplianceRepository().get(propertyAppliance.getApplianceId());
        ControllerFactory.getInstance().getApplianceStatusController().getForApplianceType(appliance.getType(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
            }
        });
    }

    private void updateStatus() {
        IApplianceReadable appliance = RepositoryFactory.getInstance().getReadableApplianceRepository().get(propertyAppliance.getApplianceId());
        IApplianceStatusReadableRepository statRepo = RepositoryFactory.getInstance().getReadableApplianceStatusRepository();
        IApplianceStatusReadable cur = statRepo.getForId(propertyAppliance.getStatusId());
        final SendApplianceStatusUpdateDialog dialog = new SendApplianceStatusUpdateDialog(this, statRepo.getForApplianceType(appliance.getType()), cur);
        dialog.setOnOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int which) {
                IApplianceStatusReadable selected = dialog.getSelected();
                PropertyApplianceStatusUpdatePayload payload = new PropertyApplianceStatusUpdatePayload();
                payload.setPropertyApplianceId(propertyAppliance.getId());
                payload.setNewApplianceStatusId(selected.getId());
                LocalEventBus.getInstance().addObserver(PropertyApplianceActivity.this);
                ControllerFactory.getInstance().getPropertyApplianceStatusUpdateController().updateStatus(payload, new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(PropertyApplianceActivity.this, payload.getErrors()).show();
                    }
                });
                dialog.close();
            }
        });
        dialog.create();
        dialog.show();
        //display dialog with spinner of choices
        //submit button
        // updates status
        //setup vert.x client
    }
}

