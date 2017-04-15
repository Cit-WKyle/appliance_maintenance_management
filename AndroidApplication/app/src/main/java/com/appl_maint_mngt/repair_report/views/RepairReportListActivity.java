package com.appl_maint_mngt.repair_report.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.repair_report.views.interfaces.IRepairReportListView;

import java.util.Observable;

public class RepairReportListActivity extends ACommonActivity {

    private IRepairReportListView repairReportListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_report_list);

        repairReportListView = new RepairReportListView(this);
    }

    @Override
    protected void updateModels() {

    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeRepairReportRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveRepairReportRepository(this);
    }

    @Override
    protected void updateView() {
        repairReportListView.update(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getAll());
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
