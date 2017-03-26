package com.appl_maint_mngt.views.pending_repair_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;

public class PendingRepairReportListActivity extends AppCompatActivity {

    private ListView pendRepairRepLV;
    private PendingRepairReportListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_repair_report_list);

        RepositoryFactory.getInstance().getReadablePendingRepairReportRepository().getAll();

        pendRepairRepLV = (ListView) findViewById(R.id.pend_repair_rep_list_listview);
        adapter = new PendingRepairReportListAdapter(this, RepositoryFactory.getInstance().getReadablePendingRepairReportRepository().getAll());
        pendRepairRepLV.setAdapter(adapter);
        pendRepairRepLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IPendingRepairReportReadable pendRepairReport = (IPendingRepairReportReadable) parent.getItemAtPosition(position);

                Intent intent = new Intent(PendingRepairReportListActivity.this, PendingRepairReportActivity.class);
                intent.putExtra(IPendingRepairReportViewConstants.ID_KEY, pendRepairReport.getId());
                startActivity(intent);
            }
        });

    }
}
