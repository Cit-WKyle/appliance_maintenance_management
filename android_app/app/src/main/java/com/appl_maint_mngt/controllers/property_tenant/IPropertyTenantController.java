package com.appl_maint_mngt.controllers.property_tenant;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.property_tenant.PropertyTenantPayload;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IPropertyTenantController {

    void createPropertyTenant(PropertyTenantPayload payload, IErrorCallback errorCallback);

    void getPropertyTenant(Long accountId, IErrorCallback callback);
}
