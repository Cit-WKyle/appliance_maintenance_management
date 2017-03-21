package com.appl_maint_mngt.models.pending_repair_report;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportReadable {


    public Long getId();

    public Long getOrganisationId();
    public Long getDiagnosticReportId();
    public ResponseStatus getResponseStatus();

    public Long getEngineerId();

    public String getDescription();

    public int getEstimatedDurationHours();

    public boolean isOnSite();
    public BigDecimal getCost();
}
