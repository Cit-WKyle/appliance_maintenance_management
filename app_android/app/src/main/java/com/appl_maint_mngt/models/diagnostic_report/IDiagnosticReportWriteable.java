package com.appl_maint_mngt.models.diagnostic_report;

import java.sql.Timestamp;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportWriteable {
    public void setId(Long id);
    public void setPropApplId(Long propApplId);
    public void setIssuedTime(Timestamp issuedTime);
    public void setDescription(String description);
}
