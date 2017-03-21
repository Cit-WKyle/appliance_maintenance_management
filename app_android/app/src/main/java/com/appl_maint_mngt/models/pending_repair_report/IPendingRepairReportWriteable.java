package com.appl_maint_mngt.models.pending_repair_report;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportWriteable {
    public void setId(Long id);

    public void setOrganisationId(Long organisationId);

    public void setDiagnosticReportId(Long diagnosticReportId);

    public void setResponseStatus(ResponseStatus responseStatus);

    public void setEngineerId(Long engineerId);

    public void setDescription(String description);

    public void setEstimatedDurationHours(int estimatedDurationHours);

    public void setOnSite(boolean onSite);

    public void setCost(BigDecimal cost);
}
