package com.appl_maint_mngt.validation.pending_repair_report;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.forms.pending_repair_report.PendingRepairReportForm;
import com.appl_maint_mngt.validation.common.GenericValidatorResponse;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 25/03/2017.
 */

public class PendingRepairReportValidator implements IPendingRepairReportValidator {

    private Context cntxt;

    public PendingRepairReportValidator() {
        cntxt = MainActivity.getInstance();
    }

    @Override
    public IValidatorResponse validate(PendingRepairReportForm form) {
        List<String> errors = new ArrayList<>();


        if(form.getCost().compareTo(IPendingRepairReportValidationConstants.COST_MIN) == -1)  errors.add(cntxt.getString(R.string.validation_pend_rep_report_cost_min));
        if(form.getCost().compareTo(IPendingRepairReportValidationConstants.COST_MAX) == 1) errors.add(cntxt.getString(R.string.validation_pend_rep_report_cost_max));
        if(form.getDescription().length() < IPendingRepairReportValidationConstants.DESC_MIN) errors.add(cntxt.getString(R.string.validation_pend_rep_report_desc_min));
        if(form.getDescription().length() > IPendingRepairReportValidationConstants.DESC_MAX) errors.add(cntxt.getString(R.string.validation_pend_rep_report_desc_max));
        if(form.getDuration() < IPendingRepairReportValidationConstants.EST_DUR_MIN) errors.add(cntxt.getString(R.string.validation_pend_rep_report_est_dur_min));
        if(form.getDuration() > IPendingRepairReportValidationConstants.EST_DUR_MAX) errors.add(cntxt.getString(R.string.validation_pend_rep_report_est_dur_max));

        return new GenericValidatorResponse(errors.size() == 0, errors);
    }
}
