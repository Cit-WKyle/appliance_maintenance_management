package com.appl_maint_mngt.views.pending_repair_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportRepositoryObserverUpdateTypes;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_report.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.views.diagnostic_request.DiagnosticRequestsActivity;
import com.appl_maint_mngt.views.diagnostic_request.IDiagnosticRequestViewConstants;
import com.appl_maint_mngt.views.property_appliance.IPropertyApplianceViewConstants;
import com.appl_maint_mngt.views.property_appliance.PropertyApplianceActivity;

import java.util.Observable;
import java.util.Observer;

public class PendingRepairReportActivity extends AppCompatActivity implements Observer {

    private IDiagnosticRequestReadable diagnosticRequest;
    private IPendingRepairReportReadable pendingRepairReport;

    private TextView titleTv;
    private TextView descTv;
    private TextView durationTv;
    private TextView onSiteTv;
    private TextView costTv;

    private Button accept;
    private Button decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_repair_report);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long diagnosticRequestId = extras.getLong(IDiagnosticRequestViewConstants.ID_KEY);
            diagnosticRequest = RepositoryFactory.getInstance().getReadableDiagnosticRequestRepository().getForId(diagnosticRequestId);
        }

        RepositoryFactory.getInstance().observePendingRepairReportRepository(this);
        ControllerFactory.getInstance().getPendingRepairReportController().getForDiagnosticRequest(diagnosticRequest, new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PendingRepairReportActivity.this, payload.getErrors()).show();
            }
        });

        titleTv = (TextView) findViewById(R.id.pend_repair_rep_textview_title);
        descTv = (TextView) findViewById(R.id.pend_repair_rep_textview_desc);
        durationTv = (TextView) findViewById(R.id.pend_repair_rep_textview_duration_value);
        onSiteTv = (TextView) findViewById(R.id.pend_repair_rep_textview_onsite_value);
        costTv = (TextView) findViewById(R.id.pend_repair_rep_textview_onsite_value);

        accept = (Button) findViewById(R.id.pend_repair_rep_button_accept);
        accept.setVisibility(View.INVISIBLE);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerFactory.getInstance().getPendingRepairReportController().acceptPendingRepairReport(pendingRepairReport, new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {

                    }
                });
            }
        });
        decline = (Button) findViewById(R.id.pend_repair_rep_button_decline);
        decline.setVisibility(View.INVISIBLE);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerFactory.getInstance().getPendingRepairReportController().declinePendingRepairReport(pendingRepairReport, new IErrorCallback() {

                    @Override
                    public void callback(ErrorPayload payload) {
                    }
                });
                Intent intent = new Intent(PendingRepairReportActivity.this, DiagnosticRequestsActivity.class);
                intent.putExtra(IDiagnosticReportViewConstants.ID_KEY, pendingRepairReport.getDiagnosticReportId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            if(arg.equals(IPendingRepairReportRepositoryObserverUpdateTypes.MODEL_UPDATE)) {
                pendingRepairReport = RepositoryFactory.getInstance().getReadablePendingRepairReportRepository().getForDiagAndOrgIds(diagnosticRequest.getDiagnosticReportId(), diagnosticRequest.getMaintenanceOrganisationId());
                titleTv.setText(RepositoryFactory.getInstance().getReadableMaintenanceOrganisationRepository().getForId(pendingRepairReport.getOrganisationId()).getName());
                descTv.setText(pendingRepairReport.getDescription());
                durationTv.setText(String.valueOf(pendingRepairReport.getEstimatedDurationHours()));
                onSiteTv.setText(String.valueOf(pendingRepairReport.isOnSite()));
                costTv.setText(String.valueOf(pendingRepairReport.getCost()));
                accept.setVisibility(View.VISIBLE);
                decline.setVisibility(View.VISIBLE);
            }
        }
    }
}
