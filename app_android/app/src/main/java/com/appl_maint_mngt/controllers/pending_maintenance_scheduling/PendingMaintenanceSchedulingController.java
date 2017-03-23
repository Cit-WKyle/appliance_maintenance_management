package com.appl_maint_mngt.controllers.pending_maintenance_scheduling;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.pending_maintenance_scheduling.SchedulerType;
import com.appl_maint_mngt.views.pending_maintenance_scheduling.SelectedSchedule;
import com.appl_maint_mngt.web.models.pending_maintenance_scheduling.IPendingSchedulePayload;
import com.appl_maint_mngt.web.models.pending_maintenance_scheduling.PendingSchedulePayload;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kyle on 22/03/2017.
 */

public class PendingMaintenanceSchedulingController implements IPendingMaintenanceSchedulingController {
    @Override
    public void addPendingSchedules(Long reportId, SchedulerType type, List<SelectedSchedule> schedules, IErrorCallback callback) {
        for(SelectedSchedule sched: schedules) {
            IPendingSchedulePayload payload = new PendingSchedulePayload();
            payload.setReportId(reportId);
            payload.setSchedulerType(type);
            payload.setStartTime(new Timestamp(sched.getStart().getMillis()));
            payload.setEndTime(new Timestamp(sched.getEnd().getMillis()));
        }
    }
}
