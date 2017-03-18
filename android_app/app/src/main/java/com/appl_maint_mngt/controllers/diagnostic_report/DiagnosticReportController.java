package com.appl_maint_mngt.controllers.diagnostic_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.diagnostic_report.IDiagnosticReportService;

/**
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReportController implements IDiagnosticReportController {

    private IDiagnosticReportService diagnosticReportService;

    public DiagnosticReportController() {
        this.diagnosticReportService = ServiceFactory.getInstance().getDiagnosticReportService();
    }

    @Override
    public void generateDiagnosticReport(DiagnosticReportForm form, IErrorCallback errorCallback) {
        diagnosticReportService.post(form, errorCallback);
    }
}
