package com.appl_maint_mngt.models.diagnostic_report;

import java.sql.Timestamp;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportReadable {

    public Long getId();
    public Long getPropApplId();
    public Timestamp getIssuedTime();
    public String getDescription();

}
