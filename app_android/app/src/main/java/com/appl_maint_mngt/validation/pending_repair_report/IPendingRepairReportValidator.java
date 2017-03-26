package com.appl_maint_mngt.validation.pending_repair_report;

import com.appl_maint_mngt.forms.pending_repair_report.PendingRepairReportForm;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

/**
 * Created by Kyle on 25/03/2017.
 */

public interface IPendingRepairReportValidator {

    IValidatorResponse validate(PendingRepairReportForm form);
}
