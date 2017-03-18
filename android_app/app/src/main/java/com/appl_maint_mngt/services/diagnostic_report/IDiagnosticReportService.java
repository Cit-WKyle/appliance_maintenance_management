package com.appl_maint_mngt.services.diagnostic_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportService {

    void post(DiagnosticReportForm diagRep, IErrorCallback callback);
}
