package com.appl_maint_mngt.controllers.property_appliance;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyApplianceController {

    void getPropertyAppliancesForProperty(Long propertyId, IErrorCallback errorCallback);
}
