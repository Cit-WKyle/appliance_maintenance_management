package com.appl_maint_mngt.repositories.diagnostic_report;

import com.appl_maint_mngt.models.diagnostic_report.DiagnosticReport;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportUpdateableRepository {

    void addItem(DiagnosticReport report);
}
