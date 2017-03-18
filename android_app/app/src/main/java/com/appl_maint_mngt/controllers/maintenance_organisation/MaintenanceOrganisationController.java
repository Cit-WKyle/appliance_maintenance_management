package com.appl_maint_mngt.controllers.maintenance_organisation;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.maintenance_organisation.IMaintenanceOrganisationService;

/**
 * Created by Kyle on 18/03/2017.
 */

public class MaintenanceOrganisationController implements IMaintenanceOrganisationController {

    private IMaintenanceOrganisationService service;

    public MaintenanceOrganisationController() {
        service = ServiceFactory.getInstance().getMaintenanceOrganisationService();
    }

    @Override
    public void getAll(IErrorCallback callback) {
        service.getAll(callback);
    }
}
