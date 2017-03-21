package com.appl_maint_mngt.views.diagnostic_request;

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
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestObserverUpdateTypes;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestReadableRepository;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_report.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.views.property_appliance.PropertyApplianceActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DiagnosticRequestsActivity extends AppCompatActivity implements Observer{

    private Long diagnosticReportId;

    private IDiagnosticRequestReadableRepository repository;

    private ListView requestsLv;
    private DiagnosticRequestListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_requests);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            diagnosticReportId = extras.getLong(IDiagnosticReportViewConstants.ID_KEY);
        }

        repository = RepositoryFactory.getInstance().getReadableDiagnosticRequestRepository();
        ControllerFactory.getInstance().getDiagnosticRequestController().getForDiagnosticReportId(diagnosticReportId, new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(DiagnosticRequestsActivity.this, payload.getErrors()).show();
            }
        });

        requestsLv = (ListView) findViewById(R.id.diagreq_listview_requests);
        adapter = new DiagnosticRequestListAdapter(this, repository.getAll());
        requestsLv.setAdapter(adapter);
        requestsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IDiagnosticRequestReadable request = (IDiagnosticRequestReadable) parent.getItemAtPosition(position);
                if(request.getResponseStatus().equals(ResponseStatus.RESPONDED)) {
                    //PENDING REPAIR REPORT ACTIVITY
                }
            }
        });
    }


    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String) {
            if(o.equals(IDiagnosticRequestObserverUpdateTypes.MODEL_UPDATE) || o.equals(IDiagnosticRequestObserverUpdateTypes.NEW_ITEM)) {
                adapter.clear();
                adapter.addAll(repository.getAll());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
