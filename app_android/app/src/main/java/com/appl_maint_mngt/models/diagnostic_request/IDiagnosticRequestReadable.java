package com.appl_maint_mngt.models.diagnostic_request;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestReadable {
    public Long getId();
    public Long getDiagnosticReportId();

    public ResponseStatus getResponseStatus();

    public Long getMaintenanceOrganisationId();
}
