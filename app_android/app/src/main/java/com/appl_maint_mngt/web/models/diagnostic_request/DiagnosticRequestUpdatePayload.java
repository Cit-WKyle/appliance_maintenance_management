package com.appl_maint_mngt.web.models.diagnostic_request;

import com.appl_maint_mngt.models.diagnostic_request.ResponseStatus;

/**
 * Created by Kyle on 25/03/2017.
 */

public class DiagnosticRequestUpdatePayload {

    private Long id;
    private Long diagnosticReportId;
    private Long maintenanceOrganisationId;
    private ResponseStatus responseStatus;

    public Long getDiagnosticReportId() {
        return diagnosticReportId;
    }

    public void setDiagnosticReportId(Long diagnosticReportId) {
        this.diagnosticReportId = diagnosticReportId;
    }

    public Long getMaintenanceOrganisationId() {
        return maintenanceOrganisationId;
    }

    public void setMaintenanceOrganisationId(Long maintenanceOrganisationId) {
        this.maintenanceOrganisationId = maintenanceOrganisationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
