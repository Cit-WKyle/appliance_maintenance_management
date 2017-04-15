package com.appl_maint_mngt.maintenance_schedule.services;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class MaintenanceScheduleService implements IMaintenanceScheduleService {

    @Override
    public void findByRepairReportId(Long repairReportId, IErrorCallback errorCallback) {

    }

    @Override
    public void findByRepairReportIdIn(List<Long> repairReportIds, IErrorCallback errorCallback) {
    }
}
