package com.appl_maint_mngt.repositories.maintenance_schedule;

import com.appl_maint_mngt.models.maintenance_schedule.MaintenanceSchedule;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleUpdateableRepository {

    void addItem(MaintenanceSchedule sched);
}
