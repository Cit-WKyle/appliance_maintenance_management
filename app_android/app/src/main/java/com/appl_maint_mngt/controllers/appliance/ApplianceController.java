package com.appl_maint_mngt.controllers.appliance;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.appliance.IApplianceService;
import com.appl_maint_mngt.services.common.ServiceFactory;

/**
 * Created by Kyle on 18/03/2017.
 */

public class ApplianceController implements IApplianceController {

    private IApplianceService applianceService;

    public ApplianceController() {
        this.applianceService = ServiceFactory.getInstance().getApplianceService();
    }

    @Override
    public void getForId(String id, IErrorCallback callback) {
        applianceService.get(id, callback);
    }
}
