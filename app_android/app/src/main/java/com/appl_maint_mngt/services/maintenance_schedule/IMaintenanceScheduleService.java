package com.appl_maint_mngt.services.maintenance_schedule;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleService {

    void findByRepairReportId(Long repairReportId, IErrorCallback errorCallback);
}
