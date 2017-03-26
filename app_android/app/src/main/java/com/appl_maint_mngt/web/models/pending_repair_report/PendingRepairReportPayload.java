package com.appl_maint_mngt.web.models.pending_repair_report;

import com.appl_maint_mngt.models.pending_repair_report.ResponseStatus;

import java.math.BigDecimal;

/**
 * Created by Kyle on 25/03/2017.
 */

public class PendingRepairReportPayload {

    private Long id;

    private Long organisationId;

    private Long diagnosticReportId;

    private Long engineerId;

    private String description;

    private int estimatedDurationHours;

    private boolean onSite;

    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public Long getDiagnosticReportId() {
        return diagnosticReportId;
    }

    public void setDiagnosticReportId(Long diagnosticReportId) {
        this.diagnosticReportId = diagnosticReportId;
    }

    public Long getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedDurationHours() {
        return estimatedDurationHours;
    }

    public void setEstimatedDurationHours(int estimatedDurationHours) {
        this.estimatedDurationHours = estimatedDurationHours;
    }

    public boolean isOnSite() {
        return onSite;
    }

    public void setOnSite(boolean onSite) {
        this.onSite = onSite;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
