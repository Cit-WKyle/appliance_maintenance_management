package com.appl_maint_mngt.views.repair_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.repair_report.IRepairReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportReadableRepository;

public class RepairReportListActivity extends AppCompatActivity {

    private IRepairReportReadableRepository repository;
    private RepairReportListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_report_list);

        repository = RepositoryFactory.getInstance().getReadableRepairReportRepository();

        ListView reportLV = (ListView) findViewById(R.id.repair_report_list_listview_items);
        adapter = new RepairReportListAdapter(this, repository.getAll());
        reportLV.setAdapter(adapter);

        reportLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IRepairReportReadable repairReport = (IRepairReportReadable) parent.getItemAtPosition(position);
                Intent intent = new Intent(RepairReportListActivity.this, RepairReportActivity.class);
                intent.putExtra(IRepairReportViewConstants.ID_KEY, repairReport.getId());
                startActivity(intent);
            }
        });
    }
}
