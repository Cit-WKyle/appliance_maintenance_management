package com.appl_maint_mngt.services.property_appliance;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyApplianceService {

    void findByPropertyId(Long propertyId, IErrorCallback callback);
}
