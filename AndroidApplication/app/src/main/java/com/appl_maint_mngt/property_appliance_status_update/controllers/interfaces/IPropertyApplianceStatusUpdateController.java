package com.appl_maint_mngt.property_appliance_status_update.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces.IPropertyApplianceStatusUpdatePayload;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IPropertyApplianceStatusUpdateController {
    void updateStatus(IPropertyApplianceStatusUpdatePayload payload, IErrorCallback errorCallback);
}
