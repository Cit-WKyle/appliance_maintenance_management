package com.appl_maint_mngt.controllers.pending_maintenance_scheduling;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.pending_maintenance_scheduling.SchedulerType;
import com.appl_maint_mngt.views.pending_maintenance_scheduling.SelectedSchedule;

import java.util.List;

/**
 * Created by Kyle on 22/03/2017.
 */

public interface IPendingMaintenanceSchedulingController {

    void addPendingSchedules(Long reportId, SchedulerType type, List<SelectedSchedule> schedules, IErrorCallback callback);
}
