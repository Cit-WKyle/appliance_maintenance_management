package com.appl_maint_mngt.controllers.pending_repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportController {

    void getForDiagnosticRequest(IDiagnosticRequestReadable request, IErrorCallback callback);

    void acceptPendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback);

    void declinePendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback);
}
