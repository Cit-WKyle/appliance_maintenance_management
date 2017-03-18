package com.appl_maint_mngt.controllers.property_manager;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyManagerController {

    void getPropertyManager(Long accountId, IErrorCallback errorCallback);
}
