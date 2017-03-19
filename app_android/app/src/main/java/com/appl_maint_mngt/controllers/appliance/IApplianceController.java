package com.appl_maint_mngt.controllers.appliance;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceController {

    void getForId(String id, IErrorCallback callback);
}
