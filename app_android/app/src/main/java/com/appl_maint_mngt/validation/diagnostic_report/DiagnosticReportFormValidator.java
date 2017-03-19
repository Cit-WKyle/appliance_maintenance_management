package com.appl_maint_mngt.validation.diagnostic_report;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;
import com.appl_maint_mngt.validation.common.GenericValidatorResponse;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */
public class DiagnosticReportFormValidator implements IDiagnosticReportFormValidator {

    private Context cntxt;

    public DiagnosticReportFormValidator() {
        cntxt = MainActivity.getInstance();
    }

    @Override
    public IValidatorResponse validate(DiagnosticReportForm form) {
        List<String> errors = new ArrayList<>();

        if(form.getDescription().length() < IDiagnosticReportValidationConstants.DESCRIPTION_MIN) errors.add(cntxt.getString(R.string.validation_diagnostic_report_description_min));
        if(form.getDescription().length() > IDiagnosticReportValidationConstants.DESCRIPTION_MAX) errors.add(cntxt.getString(R.string.validation_diagnostic_report_description_max));

        return new GenericValidatorResponse(errors.size() == 0, errors);
    }
}
