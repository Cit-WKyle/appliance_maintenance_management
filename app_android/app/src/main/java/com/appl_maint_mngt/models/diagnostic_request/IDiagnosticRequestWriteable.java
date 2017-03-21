package com.appl_maint_mngt.models.diagnostic_request;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestWriteable {
    public void setId(Long id);
    public void setDiagnosticReportId(Long diagnosticReportId);
    public void setResponseStatus(ResponseStatus responseStatus);
    public void setMaintenanceOrganisationId(Long maintenanceOrganisationId);
}
