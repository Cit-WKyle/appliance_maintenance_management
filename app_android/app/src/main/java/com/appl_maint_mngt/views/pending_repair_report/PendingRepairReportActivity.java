package com.appl_maint_mngt.views.pending_repair_report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.views.diagnostic_request.IDiagnosticRequestViewConstants;

public class PendingRepairReportActivity extends AppCompatActivity {

    private IDiagnosticRequestReadable diagnosticRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_repair_report);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long diagnosticRequestId = extras.getLong(IDiagnosticRequestViewConstants.ID_KEY);
            diagnosticRequest = RepositoryFactory.getInstance().getReadableDiagnosticRequestRepository().getForId(diagnosticRequestId);
        }


    }
}
