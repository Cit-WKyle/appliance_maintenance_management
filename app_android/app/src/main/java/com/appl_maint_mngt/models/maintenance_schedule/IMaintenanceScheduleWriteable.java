package com.appl_maint_mngt.models.maintenance_schedule;

import java.sql.Timestamp;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleWriteable {
    public void setId(Long id);

    public void setRepairReportId(Long repairReportId);

    public void setStartTime(Timestamp startTime);

    public void setEndTime(Timestamp endTime);

    public void setScheduleStatus(ScheduleStatus scheduleStatus);
}
