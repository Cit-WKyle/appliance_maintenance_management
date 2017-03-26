package com.appl_maint_mngt.views.diagnostic_request;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.models.diagnostic_request.ResponseStatus;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestReadableRepository;
import com.appl_maint_mngt.views.common.AcceptDeclineDialog;
import com.appl_maint_mngt.views.diagnostic_report.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.views.pending_repair_report.CreatePendingRepairReportActivity;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestPayload;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestUpdatePayload;

public class DiagnosticRequestsReceivedActivity extends AppCompatActivity {

    private IDiagnosticRequestReadableRepository repository;

    private DiagnosticRequestsReceivedListAdapter adapter;
    private ListView diagReqLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_requests_received);

        repository = RepositoryFactory.getInstance().getReadableDiagnosticRequestRepository();

        adapter = new DiagnosticRequestsReceivedListAdapter(this, repository.getAllPending());
        diagReqLV = (ListView) findViewById(R.id.diagreq_rec_listview_requests);
        diagReqLV.setAdapter(adapter);
        diagReqLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final IDiagnosticRequestReadable diagReq = (IDiagnosticRequestReadable) parent.getItemAtPosition(position);
                final AcceptDeclineDialog dialog = new AcceptDeclineDialog(DiagnosticRequestsReceivedActivity.this, R.string.accept_request);
                dialog.setOnAcceptListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dg, int which) {
                        DiagnosticRequestUpdatePayload payload = new DiagnosticRequestUpdatePayload();
                        payload.setMaintenanceOrganisationId(diagReq.getMaintenanceOrganisationId());
                        payload.setDiagnosticReportId(diagReq.getDiagnosticReportId());
                        payload.setId(diagReq.getId());
                        payload.setResponseStatus(ResponseStatus.RESPONDED);
                        ControllerFactory.getInstance().getDiagnosticRequestController().updateDiagnosticRequest(payload, new IErrorCallback() {
                            @Override
                            public void callback(ErrorPayload payload) {

                            }
                        });
                        Intent intent = new Intent(DiagnosticRequestsReceivedActivity.this, CreatePendingRepairReportActivity.class);
                        intent.putExtra(IDiagnosticReportViewConstants.ID_KEY, diagReq.getDiagnosticReportId());
                        startActivity(intent);
                        //create pending report view
                        dialog.close();
                    }
                });
                dialog.setOnDeclineListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dg, int which) {
                        DiagnosticRequestUpdatePayload payload = new DiagnosticRequestUpdatePayload();
                        payload.setMaintenanceOrganisationId(diagReq.getMaintenanceOrganisationId());
                        payload.setDiagnosticReportId(diagReq.getDiagnosticReportId());
                        payload.setId(diagReq.getId());
                        payload.setResponseStatus(ResponseStatus.DECLINED);
                        ControllerFactory.getInstance().getDiagnosticRequestController().updateDiagnosticRequest(payload, new IErrorCallback() {
                            @Override
                            public void callback(ErrorPayload payload) {

                            }
                        });
                        recreate();
                        dialog.close();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

    }
}
