package com.appl_maint_mngt.models.pending_maintenance_scheduling;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceScheduleWriteable {

    public void setRepairReportId(Long repairReportId);

    public void setStartTime(Timestamp startTime);

    public void setId(Long id);

    public void setEndTime(Timestamp endTime);

    public void setSchedulerType(SchedulerType schedulerType);

    public void setScheduleStatus(ScheduleStatus scheduleStatus);
}
