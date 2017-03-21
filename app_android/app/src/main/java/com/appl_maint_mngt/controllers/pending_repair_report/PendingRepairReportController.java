package com.appl_maint_mngt.controllers.pending_repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.pending_repair_report.IPendingRepairReportService;

/**
 * Created by Kyle on 21/03/2017.
 */

public class PendingRepairReportController implements IPendingRepairReportController {

    private IPendingRepairReportService service;

    public PendingRepairReportController() {
        service = ServiceFactory.getInstance().getPendingRepairReportService();
    }

    @Override
    public void getForDiagnosticRequest(IDiagnosticRequestReadable request, IErrorCallback callback) {
        service.get(request.getDiagnosticReportId(), request.getMaintenanceOrganisationId(), callback);
    }

    @Override
    public void acceptPendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback) {
        
    }

    @Override
    public void declinePendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback) {

    }
}
