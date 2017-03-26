package com.appl_maint_mngt.services.maintenance_engineer;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.maintenance_engineer.MaintenanceEngineerPayload;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IMaintenanceEngineerService {

    void post(MaintenanceEngineerPayload payload, IErrorCallback callback);

    void get(Long id, IErrorCallback errorCallback);
}
