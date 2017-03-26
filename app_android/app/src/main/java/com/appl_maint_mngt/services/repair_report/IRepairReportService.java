package com.appl_maint_mngt.services.repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportService {

    void findByDiagnosticReportId(Long id, IErrorCallback errorCallback);

    void findByDiagnosticReportIdsIn(List<Long> ids, IErrorCallback errorCallback);

    void findByEngineerId(Long engineerId, IErrorCallback errorCallback);
}
