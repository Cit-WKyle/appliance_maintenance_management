package com.appl_maint_mngt.controllers.maintenance_engineer;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.maintenance_engineer.MaintenanceEngineerPayload;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IMaintenanceEngineerController {

    void create(MaintenanceEngineerPayload payload, IErrorCallback errorCallback);

    void getEngineer(Long accountId, IErrorCallback errorCallback);
}
