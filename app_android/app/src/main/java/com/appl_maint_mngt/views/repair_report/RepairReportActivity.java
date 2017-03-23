package com.appl_maint_mngt.views.repair_report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.repair_report.IRepairReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;

public class RepairReportActivity extends AppCompatActivity {

    private IRepairReportReadable repairReport;

    private CardView maintSchedCV;
    private CardView pendingSchedEngCV;
    private CardView pendingSchedManagerCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_report);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long repairReportId = extras.getLong(IRepairReportViewConstants.ID_KEY);
            repairReport = RepositoryFactory.getInstance().getReadableRepairReportRepository().getForId(repairReportId);
        }

        TextView descTv = (TextView) findViewById(R.id.repair_report_textview_desc_value);
        descTv.setText(repairReport.getDescription());

        TextView durTv = (TextView) findViewById(R.id.repair_report_textview_dur_value);
        durTv.setText(String.valueOf(repairReport.getEstimatedDurationHours()));

        TextView onSiteTv = (TextView) findViewById(R.id.repair_report_textview_onsite_value);
        onSiteTv.setText(String.valueOf(repairReport.getEstimatedDurationHours()));

        TextView costTv = (TextView) findViewById(R.id.repair_report_textview_cost_value);
        costTv.setText(String.valueOf(repairReport.getCost()));

        maintSchedCV = (CardView) findViewById(R.id.repair_report_cardview_sched);
        maintSchedCV.setVisibility(View.INVISIBLE);

        //Check if accepted, if not, display card of your offers and card of engineer offers
        // If accepted, display scheduled maintenance info
    }
}
