package com.appl_maint_mngt.controllers.property;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.property.IPropertyService;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyController implements IPropertyController {

    private IPropertyService service;

    public PropertyController() {
        service = ServiceFactory.getInstance().getPropertyService();
    }

    @Override
    public void getProperties(List<Long> ids, IErrorCallback callback) {
        service.findByIds(ids, callback);
    }
}
