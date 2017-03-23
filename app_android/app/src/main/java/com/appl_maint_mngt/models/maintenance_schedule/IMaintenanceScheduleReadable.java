package com.appl_maint_mngt.models.maintenance_schedule;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleReadable {

    public Long getId();

    public Long getRepairReportId();

    public Timestamp getStartTime();

    public Timestamp getEndTime();

    public ScheduleStatus getScheduleStatus();
}
