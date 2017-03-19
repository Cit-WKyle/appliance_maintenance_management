package com.appl_maint_mngt.controllers.property_tenant;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.property_tenant.IPropertyTenantService;
import com.appl_maint_mngt.web.models.property_tenant.PropertyTenantPayload;

/**
 * Created by Kyle on 19/03/2017.
 */

public class PropertyTenantController implements IPropertyTenantController {

    private IPropertyTenantService propertyTenantService;

    public PropertyTenantController() {
        propertyTenantService = ServiceFactory.getInstance().getPropertyTenantService();
    }

    @Override
    public void createPropertyTenant(PropertyTenantPayload payload, IErrorCallback errorCallback) {
        propertyTenantService.post(payload, errorCallback);
    }

    @Override
    public void getPropertyTenant(Long accountId, IErrorCallback callback) {
        propertyTenantService.get(accountId, callback);
    }
}
