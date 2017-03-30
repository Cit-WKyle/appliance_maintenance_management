package com.appl_maint_mngt.repositories.diagnostic_report;

import com.appl_maint_mngt.models.diagnostic_report.IDiagnosticReportReadable;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */
public interface IDiagnosticReportReadableRepository {

    IDiagnosticReportReadable get(Long id);

    IDiagnosticReportReadable getMostRecent();

    List<IDiagnosticReportReadable> getAll();

    List<IDiagnosticReportReadable> getAllForPropertyAppliance(Long propApplId);
}
