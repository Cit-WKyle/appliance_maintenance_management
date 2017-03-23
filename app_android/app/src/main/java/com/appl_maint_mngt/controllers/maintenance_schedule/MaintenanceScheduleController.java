package com.appl_maint_mngt.controllers.maintenance_schedule;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.maintenance_schedule.IMaintenanceScheduleService;

/**
 * Created by Kyle on 23/03/2017.
 */

public class MaintenanceScheduleController implements IMaintenanceScheduleController {

    private IMaintenanceScheduleService service;

    public MaintenanceScheduleController() {
        service = ServiceFactory.getInstance().getMaintenanceScheduleService();
    }

    @Override
    public void getForRepairReport(Long repairReportId, IErrorCallback errorCallback) {
        service.findByRepairReportId(repairReportId, errorCallback);
    }
}
