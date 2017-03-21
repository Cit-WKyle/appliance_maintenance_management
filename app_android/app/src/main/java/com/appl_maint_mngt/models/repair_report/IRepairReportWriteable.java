package com.appl_maint_mngt.models.repair_report;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportWriteable {
    public void setId(Long id);

    public void setEngineerId(Long engineerId);

    public void setDiagnosticReportId(Long diagnosticReportId);

    public void setDescription(String description);
    public void setEstimatedDurationHours(int estimatedDurationHours);

    public void setOnSite(boolean onSite);

    public void setCost(BigDecimal cost);
}
