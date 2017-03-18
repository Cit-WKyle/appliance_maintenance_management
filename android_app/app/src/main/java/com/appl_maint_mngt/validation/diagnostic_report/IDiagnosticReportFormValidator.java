package com.appl_maint_mngt.validation.diagnostic_report;

import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

/**
 * Created by Kyle on 18/03/2017.
 */
public interface IDiagnosticReportFormValidator {

    IValidatorResponse validate(DiagnosticReportForm form);
}
