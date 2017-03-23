package com.appl_maint_mngt.web.models.pending_maintenance_scheduling;

import com.appl_maint_mngt.models.pending_maintenance_scheduling.SchedulerType;

import java.sql.Timestamp;

/**
 * Created by Kyle on 22/03/2017.
 */

public class PendingSchedulePayload implements IPendingSchedulePayload{

    private Long reportId;

    private Timestamp startTime;

    private Timestamp endTime;

    private SchedulerType schedulerType;

    @Override
    public Long getReportId() {
        return reportId;
    }

    @Override
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    @Override
    public Timestamp getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public Timestamp getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public SchedulerType getSchedulerType() {
        return schedulerType;
    }

    @Override
    public void setSchedulerType(SchedulerType schedulerType) {
        this.schedulerType = schedulerType;
    }
}
