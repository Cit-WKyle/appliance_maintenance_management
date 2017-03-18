package com.appl_maint_mngt.forms.diagnostic_report;


import java.sql.Timestamp;

/**
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReportForm {

    private Long propertyApplianceId;
    private Timestamp issuedTime;
    private String description;

    public DiagnosticReportForm(){}

    public Long getPropertyApplianceId() {
        return propertyApplianceId;
    }

    public void setPropertyApplianceId(Long propertyApplianceId) {
        this.propertyApplianceId = propertyApplianceId;
    }

    public Timestamp getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Timestamp issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
