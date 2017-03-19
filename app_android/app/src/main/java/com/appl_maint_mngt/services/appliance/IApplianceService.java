package com.appl_maint_mngt.services.appliance;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceService {

    void get(String id, IErrorCallback errorCallback);
}
