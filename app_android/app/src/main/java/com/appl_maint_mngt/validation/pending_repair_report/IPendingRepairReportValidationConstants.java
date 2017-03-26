package com.appl_maint_mngt.validation.pending_repair_report;

import java.math.BigDecimal;

/**
 * Created by Kyle on 25/03/2017.
 */

public interface IPendingRepairReportValidationConstants {

    int DESC_MIN = 10;
    int DESC_MAX = 100;

    int EST_DUR_MIN = 1;
    int EST_DUR_MAX = 12;

    BigDecimal COST_MIN = new BigDecimal(10);
    BigDecimal COST_MAX = new BigDecimal(2000);
}