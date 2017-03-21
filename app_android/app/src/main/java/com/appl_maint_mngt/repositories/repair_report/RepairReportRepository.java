package com.appl_maint_mngt.repositories.repair_report;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.repair_report.ARepairReport;
import com.appl_maint_mngt.models.repair_report.IRepairReportReadable;
import com.appl_maint_mngt.models.repair_report.RepairReport;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public class RepairReportRepository extends ARepairReportRepository {

    private LongSparseArray<ARepairReport> repairReports;

    public RepairReportRepository() {
        repairReports = new LongSparseArray<>();
    }

    @Override
    public IRepairReportReadable getForId(Long id) {
        return repairReports.get(id);
    }

    @Override
    public IRepairReportReadable getForDiagnosticReportId(Long id) {
        for(int i=0; i<repairReports.size(); i++) {
            if(repairReports.valueAt(i).getDiagnosticReportId() == id) return repairReports.valueAt(i);
        }
        return null;
    }

    @Override
    public void addItems(List<RepairReport> repairReportsList) {
        for(RepairReport rep : repairReportsList) {
            repairReports.put(rep.getId(), rep);
        }
    }

    @Override
    public void addItem(RepairReport repairReport) {
        repairReports.put(repairReport.getId(), repairReport);
    }
}
