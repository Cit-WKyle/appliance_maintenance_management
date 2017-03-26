package com.appl_maint_mngt.repositories.repair_report;

import com.appl_maint_mngt.models.repair_report.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportReadableRepository {

    IRepairReportReadable getForId(Long id);

    IRepairReportReadable getForDiagnosticReportId(Long id);

    List<IRepairReportReadable> getAll();
}
