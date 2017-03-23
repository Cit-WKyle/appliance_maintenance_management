package com.appl_maint_mngt.models.pending_maintenance_scheduling;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceScheduleReadable {

    public Long getId();

    public Long getRepairReportId();

    public Timestamp getStartTime();

    public Timestamp getEndTime();
    public SchedulerType getSchedulerType();

    public ScheduleStatus getScheduleStatus();
}
