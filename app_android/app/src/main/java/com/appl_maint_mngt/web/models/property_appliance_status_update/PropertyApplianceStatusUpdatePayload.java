package com.appl_maint_mngt.web.models.property_appliance_status_update;

/**
 * Created by Kyle on 20/03/2017.
 */

public class PropertyApplianceStatusUpdatePayload {

    private Long propertyApplianceId;

    private Long newApplianceStatusId;

    public Long getPropertyApplianceId() {
        return propertyApplianceId;
    }

    public Long getNewApplianceStatusId() {
        return newApplianceStatusId;
    }

    public void setPropertyApplianceId(Long id) {
        this.propertyApplianceId= id;
    }

    public void setNewApplianceStatusId(Long id) {
        this.newApplianceStatusId = id;
    }
}
