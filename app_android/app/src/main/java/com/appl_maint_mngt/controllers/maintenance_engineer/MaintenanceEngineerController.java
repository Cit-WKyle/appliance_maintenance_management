package com.appl_maint_mngt.controllers.maintenance_engineer;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.maintenance_engineer.IMaintenanceEngineerService;
import com.appl_maint_mngt.web.models.maintenance_engineer.MaintenanceEngineerPayload;

/**
 * Created by Kyle on 19/03/2017.
 */

public class MaintenanceEngineerController implements IMaintenanceEngineerController {

    private IMaintenanceEngineerService service;

    public MaintenanceEngineerController() {
        service = ServiceFactory.getInstance().getMaintenanceEngineerService();
    }

    @Override
    public void create(MaintenanceEngineerPayload payload, IErrorCallback errorCallback) {
        service.post(payload, errorCallback);
    }

    @Override
    public void getEngineer(Long accountId, IErrorCallback errorCallback) {
        service.get(accountId, errorCallback);
    }
}
