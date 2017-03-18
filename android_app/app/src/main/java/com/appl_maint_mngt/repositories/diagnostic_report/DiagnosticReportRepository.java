package com.appl_maint_mngt.repositories.diagnostic_report;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.diagnostic_report.ADiagnosticReport;
import com.appl_maint_mngt.models.diagnostic_report.DiagnosticReport;
import com.appl_maint_mngt.models.diagnostic_report.IDiagnosticReportReadable;

/**
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReportRepository extends ADiagnosticReportRepository {

    private LongSparseArray<DiagnosticReport> diagnosticReports;
    private ADiagnosticReport mostRecent;

    public DiagnosticReportRepository() {
        this.diagnosticReports = new LongSparseArray<>();
    }

    @Override
    public void addItem(DiagnosticReport report) {
        diagnosticReports.put(report.getId(), report);
        mostRecent = report;
    }

    @Override
    public IDiagnosticReportReadable get(Long id) {
        return diagnosticReports.get(id);
    }

    @Override
    public IDiagnosticReportReadable getMostRecent() {
        return mostRecent;
    }
}
