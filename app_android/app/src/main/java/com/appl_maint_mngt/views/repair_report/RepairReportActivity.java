package com.appl_maint_mngt.views.repair_report;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.event_bus.common.LocalEventBus;
import com.appl_maint_mngt.event_bus.common.pending_maintenance_scheduling.IPendingMaintenanceSchedulingEvents;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.models.account.constants.UserType;
import com.appl_maint_mngt.models.maintenance_schedule.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.models.pending_maintenance_scheduling.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.models.repair_report.IRepairReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.maintenance_schedule.IMaintenanceScheduleReadableRepository;
import com.appl_maint_mngt.repositories.maintenance_schedule.IMaintenanceSchedulingObserverUpdateTypes;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.IPendingMaintenanceSchedulingObserverUpdateTypes;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.IPendingMaintenanceSchedulingReadableRepository;
import com.appl_maint_mngt.views.common.AcceptDeclineDialog;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_request.DiagnosticRequestsActivity;
import com.appl_maint_mngt.views.pending_maintenance_scheduling.PendingMaintenanceScheduleListAdapter;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RepairReportActivity extends AppCompatActivity implements Observer{

    private static final Logger logger = LoggerManager.getLogger(RepairReportActivity.class);

    private IAccountReadable account;

    private IRepairReportReadable repairReport;
    private IPendingMaintenanceSchedulingReadableRepository pendSchedRepo;
    private IMaintenanceScheduleReadableRepository schedRepo;

    private CardView maintSchedCV;
    private CardView pendingSchedEngCV;
    private CardView pendingSchedManagerCV;

    private ListView pendSchedEngLV;
    private ListView pendSchedMngLV;

    private PendingMaintenanceScheduleListAdapter engAdapter;
    private PendingMaintenanceScheduleListAdapter mngAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_report);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long repairReportId = extras.getLong(IRepairReportViewConstants.ID_KEY);
            repairReport = RepositoryFactory.getInstance().getReadableRepairReportRepository().getForId(repairReportId);
        }

        pendSchedRepo = RepositoryFactory.getInstance().getReadablePendingMaintenanceSchedulingRepository();
        schedRepo = RepositoryFactory.getInstance().getReadableMaintenanceScheduleRepository();
        account = RepositoryFactory.getInstance().getReadableAccountRepository().get();

        RepositoryFactory.getInstance().observePendingMaintenanceSchedulingRepository(this);
        RepositoryFactory.getInstance().observeMaintenanceScheduleRepository(this);

        LocalEventBus.getInstance().addObserver(this);

        TextView descTv = (TextView) findViewById(R.id.repair_report_textview_desc_value);
        descTv.setText(repairReport.getDescription());

        TextView durTv = (TextView) findViewById(R.id.repair_report_textview_dur_value);
        durTv.setText(String.valueOf(repairReport.getEstimatedDurationHours()));

        TextView onSiteTv = (TextView) findViewById(R.id.repair_report_textview_onsite_value);
        onSiteTv.setText(String.valueOf(repairReport.isOnSite()));

        TextView costTv = (TextView) findViewById(R.id.repair_report_textview_cost_value);
        costTv.setText(String.valueOf(repairReport.getCost()));

        maintSchedCV = (CardView) findViewById(R.id.repair_report_cardview_sched);
        maintSchedCV.setVisibility(View.INVISIBLE);

        pendingSchedEngCV = (CardView) findViewById(R.id.repair_report_cardview_pending_engineer_sched);
        pendSchedEngLV = (ListView) findViewById(R.id.repair_report_listview_pending_engineer_sched);
        engAdapter = new PendingMaintenanceScheduleListAdapter(this, pendSchedRepo.getForEngineerAndReportId(repairReport.getId()));
        pendSchedEngLV.setAdapter(engAdapter);
        pendSchedEngLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
                    final AcceptDeclineDialog dialog = new AcceptDeclineDialog(RepairReportActivity.this, R.string.accept_schedule_request);
                    dialog.setOnAcceptListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().acceptPendingSchedule(repairReport.getId(), new IErrorCallback() {
                                @Override
                                public void callback(ErrorPayload payload) {
                                    new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
                                }
                            });
                            dialog.close();
                        }
                    });
                    dialog.setOnDeclineListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().declinePendingSchedule(repairReport.getId(), new IErrorCallback() {
                                @Override
                                public void callback(ErrorPayload payload) {
                                    new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
                                }
                            });
                            dialog.close();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
            }
        });

        pendingSchedManagerCV = (CardView) findViewById(R.id.repair_report_cardview_pending_manager_sched);
        pendSchedMngLV = (ListView) findViewById(R.id.repair_report_listview_pending_manager_sched);
        mngAdapter = new PendingMaintenanceScheduleListAdapter(this, pendSchedRepo.getForManagerAndReportId(repairReport.getId()));
        pendSchedMngLV.setAdapter(mngAdapter);
        pendSchedMngLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
                    final AcceptDeclineDialog dialog = new AcceptDeclineDialog(RepairReportActivity.this, R.string.accept_schedule_request);
                    dialog.setOnAcceptListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().acceptPendingSchedule(repairReport.getId(), new IErrorCallback() {
                                @Override
                                public void callback(ErrorPayload payload) {
                                    new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
                                }
                            });
                            dialog.close();
                        }
                    });
                    dialog.setOnDeclineListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().declinePendingSchedule(repairReport.getId(), new IErrorCallback() {
                                @Override
                                public void callback(ErrorPayload payload) {
                                    new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
                                }
                            });
                            dialog.close();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
            }
        });

        ControllerFactory.getInstance().getMaintenanceScheduleController().getForRepairReport(repairReport.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
            }
        });

        ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().getAllScheduledByManager(repairReport.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
            }
        });

        ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().getAllScheduledByEngineer(repairReport.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            if(arg.equals(IPendingMaintenanceSchedulingObserverUpdateTypes.MODEL_UPDATE)) {

                mngAdapter.clear();
                mngAdapter.addAll(pendSchedRepo.getForManagerAndReportId(repairReport.getId()));
                mngAdapter.notifyDataSetChanged();

                engAdapter.clear();
                engAdapter.addAll(pendSchedRepo.getForEngineerAndReportId(repairReport.getId()));
                engAdapter.notifyDataSetChanged();

            } else if(arg.equals(IMaintenanceSchedulingObserverUpdateTypes.NEW_ITEM)) {
                maintSchedCV.setVisibility(View.VISIBLE);
                pendingSchedManagerCV.setVisibility(View.INVISIBLE);
                pendingSchedEngCV.setVisibility(View.INVISIBLE);
                IMaintenanceScheduleReadable maintSched = schedRepo.getForReportId(repairReport.getId());

                TextView startTimeTv = (TextView) findViewById(R.id.repair_report_textview_sched_stattime);
                startTimeTv.setText(maintSched.getStartTime().toString());
                TextView endTimeTv = (TextView) findViewById(R.id.repair_report_textview_sched_endtime);
                endTimeTv.setText(maintSched.getEndTime().toString());
                TextView statusTv = (TextView) findViewById(R.id.repair_report_textview_sched_status);
                statusTv.setText(maintSched.getScheduleStatus().toString());
            } else if(arg.equals(IPendingMaintenanceSchedulingEvents.ACCEPTED_EVENT) || arg.equals(IPendingMaintenanceSchedulingEvents.DECLINED_EVENT)) {
                recreate();
            } else if(arg.equals(IPendingMaintenanceSchedulingEvents.CREATED_EVENT)) {
                ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().getAllScheduledByManager(repairReport.getId(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
                    }
                });

                ControllerFactory.getInstance().getPendingMaintenanceSchedulingController().getAllScheduledByEngineer(repairReport.getId(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(RepairReportActivity.this, payload.getErrors()).show();
                    }
                });
            }
        }
    }
}
