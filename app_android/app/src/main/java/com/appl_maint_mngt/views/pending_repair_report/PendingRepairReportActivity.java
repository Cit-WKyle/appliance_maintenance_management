package com.appl_maint_mngt.views.pending_repair_report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.views.repair_report.IRepairReportViewConstants;

public class PendingRepairReportActivity extends AppCompatActivity {

    private IPendingRepairReportReadable pendingRepairReport;

    private TextView descTv;
    private TextView durValTv;
    private TextView onsiteTv;
    private TextView costTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_repair_report);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long pendingRepairReportId = extras.getLong(IPendingRepairReportViewConstants.ID_KEY);
            pendingRepairReport = RepositoryFactory.getInstance().getReadablePendingRepairReportRepository().getForId(pendingRepairReportId);
        }

        descTv = (TextView) findViewById(R.id.pend_repair_rep_textview_desc);
        descTv.setText(pendingRepairReport.getDescription());

        durValTv = (TextView) findViewById(R.id.pend_repair_rep_textview_duration_value);
        durValTv.setText(String.valueOf(pendingRepairReport.getEstimatedDurationHours()));

        onsiteTv = (TextView) findViewById(R.id.pend_repair_rep_textview_onsite_value);
        onsiteTv.setText(String.valueOf(pendingRepairReport.isOnSite()));

        costTv = (TextView) findViewById(R.id.pend_repair_rep_textview_cost_value);
        costTv.setText(pendingRepairReport.getCost().toString());

    }
}
