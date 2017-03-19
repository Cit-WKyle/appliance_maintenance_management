package com.appl_maint_mngt.controllers.property_manager;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.property_manager.IPropertyManagerService;
import com.appl_maint_mngt.web.models.property_manager.PropertyManagerPayload;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyManagerController implements IPropertyManagerController {

    private IPropertyManagerService propertyManagerService;

    public PropertyManagerController() {
        propertyManagerService = ServiceFactory.getInstance().getPropertyManagerService();
    }

    @Override
    public void getPropertyManager(Long accountId, IErrorCallback errorCallback) {
        propertyManagerService.get(accountId, errorCallback);
    }

    @Override
    public void createPropertyManager(PropertyManagerPayload payload, IErrorCallback errorCallback) {
        propertyManagerService.post(payload, errorCallback);
    }
}
