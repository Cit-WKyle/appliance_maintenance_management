package com.appl_maint_mngt.controllers.property_appliance;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.property_appliance.IPropertyApplianceService;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyApplianceController implements IPropertyApplianceController {

    private IPropertyApplianceService propertyApplianceService;

    public PropertyApplianceController() {
        propertyApplianceService = ServiceFactory.getInstance().getPropertyApplianceService();
    }

    @Override
    public void getPropertyAppliancesForProperty(Long propertyId, IErrorCallback errorCallback) {
        propertyApplianceService.findByPropertyId(propertyId, errorCallback);
    }
}
