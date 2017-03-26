package com.appl_maint_mngt.controllers.repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.repair_report.IRepairReportService;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */
public class RepairReportController implements  IRepairReportController {

    private IRepairReportService service;

    public RepairReportController() {
        service = ServiceFactory.getInstance().getRepairReportService();
    }

    @Override
    public void getForDiagnosticId(Long diagnosticId, IErrorCallback errorCallback) {
        service.findByDiagnosticReportId(diagnosticId, errorCallback);
    }

    @Override
    public void getForDiagnosticIds(List<Long> diagnosticId, IErrorCallback errorCallback) {
        service.findByDiagnosticReportIdsIn(diagnosticId, errorCallback);
    }

    @Override
    public void getForEngineer(Long engineerId, IErrorCallback errorCallback) {
        service.findByEngineerId(engineerId, errorCallback);
    }
}
