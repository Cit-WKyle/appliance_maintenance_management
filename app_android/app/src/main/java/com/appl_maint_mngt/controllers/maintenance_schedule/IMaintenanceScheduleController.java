package com.appl_maint_mngt.controllers.maintenance_schedule;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleController {

    void getForRepairReport(Long repairReportId, IErrorCallback errorCallback);
}
