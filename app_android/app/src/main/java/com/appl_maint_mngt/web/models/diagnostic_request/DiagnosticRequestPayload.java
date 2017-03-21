package com.appl_maint_mngt.web.models.diagnostic_request;

/**
 * Created by Kyle on 21/03/2017.
 */

public class DiagnosticRequestPayload {
    private Long diagnosticReportId;
    private Long maintenanceOrganisationId;

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
}
