package com.appl_maint_mngt.services.property_appliance_status_update;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.property_appliance_status_update.PropertyApplianceStatusUpdatePayload;

/**
 * Created by Kyle on 20/03/2017.
 */

public interface IPropertyApplianceStatusUpdateService {

    void update(PropertyApplianceStatusUpdatePayload payload, IErrorCallback errorCallback);
}
