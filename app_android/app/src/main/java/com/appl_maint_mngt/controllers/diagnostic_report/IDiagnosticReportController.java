package com.appl_maint_mngt.controllers.diagnostic_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportController {

    void generateDiagnosticReport(DiagnosticReportForm form, IErrorCallback errorCallback);

    void getForPropertyAppliance(Long propertyApplianceId, IErrorCallback errorCallback);

    void getForDiagnosticReportIds(List<Long> ids, IErrorCallback errorCallback);
}
