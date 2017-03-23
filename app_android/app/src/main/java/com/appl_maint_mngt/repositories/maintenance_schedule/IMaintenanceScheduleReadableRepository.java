package com.appl_maint_mngt.repositories.maintenance_schedule;

import com.appl_maint_mngt.models.maintenance_schedule.IMaintenanceScheduleReadable;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleReadableRepository {

    IMaintenanceScheduleReadable getForReportId(Long id);
}
