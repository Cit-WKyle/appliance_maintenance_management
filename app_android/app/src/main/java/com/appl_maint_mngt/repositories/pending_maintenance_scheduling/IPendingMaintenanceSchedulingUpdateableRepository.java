package com.appl_maint_mngt.repositories.pending_maintenance_scheduling;

import com.appl_maint_mngt.models.pending_maintenance_scheduling.PendingMaintenanceSchedule;

import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceSchedulingUpdateableRepository {

    void addItems(List<PendingMaintenanceSchedule> pendingMaintenanceScheduleList);
}
