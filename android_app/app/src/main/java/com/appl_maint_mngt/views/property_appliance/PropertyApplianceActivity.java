package com.appl_maint_mngt.views.property_appliance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.models.appliance.IApplianceReadable;
import com.appl_maint_mngt.models.appliance_status.ApplianceStatus;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;
import com.appl_maint_mngt.models.appliance_status.StatusType;
import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;
import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;
import com.appl_maint_mngt.repositories.appliance.IApplianceObserverUpdateTypes;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusObserverUpdateTypes;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_report.DiagnosticReportGeneraterActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class PropertyApplianceActivity extends AppCompatActivity implements Observer {

    private IPropertyApplianceReadable propertyAppliance;

    private TextView currentStatusTv;
    private Button generateDiagnosticReportBtn;

    private StatusHistoryListAdapter statusHistoryListAdapter;
    private ListView statusHistoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_appliance);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            Long propertyApplianceId = extras.getLong(IPropertyApplianceViewConstants.ID_KEY);
            propertyAppliance = RepositoryFactory.getInstance().getReadablePropertyApplianceRepository().getForId(propertyApplianceId);
        }

        RepositoryFactory.getInstance().observeApplianceStatusRepository(this);
        RepositoryFactory.getInstance().observeApplianceRepository(this);

        setupPropertyApplianceView();

        ControllerFactory.getInstance().getApplianceStatusController().getForStatusId(propertyAppliance.getId(), new IErrorCallback() {
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
    }

    private void setupPropertyApplianceView() {
        TextView nameTitleTv = (TextView) findViewById(R.id.propertyappliance_textview_title);
        nameTitleTv.setText(propertyAppliance.getName());

        TextView ageValueTv = (TextView) findViewById(R.id.propertyappliance_textview_age_value);
        ageValueTv.setText(String.format(Locale.ENGLISH, "%d", propertyAppliance.getAge()));

        currentStatusTv = (TextView) findViewById(R.id.propertyappliance_textview_status_label);
        currentStatusTv.setVisibility(View.INVISIBLE);
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
                currentStatusTv.setText(applStat.getType().toString());
                currentStatusTv.setVisibility(View.VISIBLE);
                if(!applStat.getType().equals(StatusType.OKAY)) currentStatusTv.setVisibility(View.VISIBLE);
            } else if(o.equals(IApplianceStatusObserverUpdateTypes.STATUS_HISTORY_UPDATE)) {
                statusHistoryListAdapter.clear();
                statusHistoryListAdapter.addAll(propertyAppliance.getStatusHistory());
                statusHistoryListAdapter.notifyDataSetChanged();
            } else if(o.equals(IApplianceObserverUpdateTypes.MODEL_UPDATE)) {
                setupApplianceView();
            }
        }
    }
}
