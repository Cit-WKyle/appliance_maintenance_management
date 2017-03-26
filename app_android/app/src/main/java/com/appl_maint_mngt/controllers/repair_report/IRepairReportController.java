package com.appl_maint_mngt.controllers.repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportController {

    void getForDiagnosticId(Long diagnosticId, IErrorCallback errorCallback);

    void getForDiagnosticIds(List<Long> diagnosticId, IErrorCallback errorCallback);

    void getForEngineer(Long engineerId, IErrorCallback errorCallback);
}
