package com.appl_maint_mngt.models.repair_report;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportReadable {

    public Long getId();
    public Long getEngineerId();

    public Long getDiagnosticReportId();
    public String getDescription();
    public int getEstimatedDurationHours();

    public boolean isOnSite();
    public BigDecimal getCost();
}
