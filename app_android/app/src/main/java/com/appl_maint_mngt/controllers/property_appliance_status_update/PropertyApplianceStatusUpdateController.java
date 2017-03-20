package com.appl_maint_mngt.controllers.property_appliance_status_update;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.property_appliance_status_update.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.web.models.property_appliance_status_update.PropertyApplianceStatusUpdatePayload;

/**
 * Created by Kyle on 20/03/2017.
 */

public class PropertyApplianceStatusUpdateController implements IPropertyApplianceStatusUpdateController {

    private IPropertyApplianceStatusUpdateService service;

    public PropertyApplianceStatusUpdateController() {
        this.service = ServiceFactory.getInstance().getPropertyApplianceUpdateService();
    }

    @Override
    public void updateStatus(PropertyApplianceStatusUpdatePayload payload, IErrorCallback errorCallback) {
        service.update(payload, errorCallback);
    }
}
