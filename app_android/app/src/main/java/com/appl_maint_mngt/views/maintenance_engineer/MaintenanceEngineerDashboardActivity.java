package com.appl_maint_mngt.views.maintenance_engineer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.controllers.maintenance_engineer.IMaintenanceEngineerController;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.models.maintenance_engineer.IMaintenanceEngineerReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportObserverUpdateTypes;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestObserverUpdateTypes;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerObserverUpdateTypes;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerReadableRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportRepositoryObserverUpdateTypes;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportObserverUpdateTypes;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_request.DiagnosticRequestsReceivedActivity;
import com.appl_maint_mngt.views.pending_repair_report.PendingRepairReportListActivity;
import com.appl_maint_mngt.views.repair_report.RepairReportListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MaintenanceEngineerDashboardActivity extends AppCompatActivity implements Observer {

    private IAccountReadable account;
    private IMaintenanceEngineerReadable engineer;
    private IMaintenanceEngineerReadableRepository repository;
    private IMaintenanceEngineerController controller;

    private Button viewRepairRepBtn;
    private Button viewDiagnosticReqBtn;
    private Button viewPendingRepairRepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_engineer_dashboard);

        account = RepositoryFactory.getInstance().getReadableAccountRepository().get();
        System.out.println("Account: ID: " +  account.getId());

        repository = RepositoryFactory.getInstance().getReadableMaintenanceEngineerRepository();
        controller = ControllerFactory.getInstance().getMaintenanceEngineerController();

        ControllerFactory.getInstance().getMaintenanceEngineerController().getEngineer(account.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                System.out.println("MaintEngController");
                new ErrorAlertDialogBuilder().build(MaintenanceEngineerDashboardActivity.this, payload.getErrors()).show();
            }
        });

        ControllerFactory.getInstance().getRepairReportController().getForEngineer(account.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                System.out.println("RepairReportController");
                new ErrorAlertDialogBuilder().build(MaintenanceEngineerDashboardActivity.this, payload.getErrors()).show();
            }
        });

        ControllerFactory.getInstance().getPendingRepairReportController().getForEngineer(account.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                System.out.println("PendingRepairReportController");
                new ErrorAlertDialogBuilder().build(MaintenanceEngineerDashboardActivity.this, payload.getErrors()).show();
            }
        });

        viewRepairRepBtn = (Button) findViewById(R.id.maint_eng_dashb_button_repair_reports);
        viewRepairRepBtn.setVisibility(View.INVISIBLE);
        viewRepairRepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceEngineerDashboardActivity.this, RepairReportListActivity.class);
                startActivity(intent);
            }
        });

        viewDiagnosticReqBtn = (Button) findViewById(R.id.maint_eng_dashb_button_diag_req);
        viewDiagnosticReqBtn.setVisibility(View.INVISIBLE);
        viewDiagnosticReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceEngineerDashboardActivity.this, DiagnosticRequestsReceivedActivity.class);
                startActivity(intent);
            }
        });

        viewPendingRepairRepBtn = (Button) findViewById(R.id.maint_eng_dashb_button_pend_rep_reports);
        viewPendingRepairRepBtn.setVisibility(View.INVISIBLE);
        viewPendingRepairRepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceEngineerDashboardActivity.this, PendingRepairReportListActivity.class);
                startActivity(intent);
            }
        });

        RepositoryFactory.getInstance().observerMaintenanceEngineerRepository(this);
        RepositoryFactory.getInstance().observeRepairReportRepository(this);
        RepositoryFactory.getInstance().observeDiagnosticRequestRepository(this);
        RepositoryFactory.getInstance().observeDiagnosticReportRepository(this);
        RepositoryFactory.getInstance().observePendingRepairReportRepository(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            if(arg.equals(IMaintenanceEngineerObserverUpdateTypes.MODEL_UPDATE)) {
                engineer = repository.get();
                System.out.println("DiagnosticRequestController.getForMaintOrgId");
                ControllerFactory.getInstance().getDiagnosticRequestController().getForMaintenanceOrgId(engineer.getCurrentOrganisationId(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        System.out.println("DiagnosticRequestController");
                        new ErrorAlertDialogBuilder().build(MaintenanceEngineerDashboardActivity.this, payload.getErrors()).show();
                    }
                });
            } else if(arg.equals(IRepairReportObserverUpdateTypes.MODEL_UPDATE)) {
                viewRepairRepBtn.setVisibility(View.VISIBLE);
            } else if(arg.equals(IDiagnosticRequestObserverUpdateTypes.MODEL_UPDATE)) {
                List<Long> diagRepIds = new ArrayList<>();
                for(IDiagnosticRequestReadable diagReq: RepositoryFactory.getInstance().getReadableDiagnosticRequestRepository().getAll()) {
                    diagRepIds.add(diagReq.getDiagnosticReportId());
                }
                if(!diagRepIds.isEmpty()) {
                    System.out.println("DiagnosticReportController.getForDiagnosticReportIds");
                    ControllerFactory.getInstance().getDiagnosticReportController().getForDiagnosticReportIds(diagRepIds, new IErrorCallback() {
                        @Override
                        public void callback(ErrorPayload payload) {
                            System.out.println("DiagnosticReportController");
                            new ErrorAlertDialogBuilder().build(MaintenanceEngineerDashboardActivity.this, payload.getErrors()).show();
                        }
                    });
                }
            } else if(arg.equals(IDiagnosticReportObserverUpdateTypes.MODEL_UPDATE)) {
                viewDiagnosticReqBtn.setVisibility(View.VISIBLE);
            } else if(arg.equals(IPendingRepairReportRepositoryObserverUpdateTypes.MODEL_UPDATE)) {
                viewPendingRepairRepBtn.setVisibility(View.VISIBLE);
            }
        }
    }
}
