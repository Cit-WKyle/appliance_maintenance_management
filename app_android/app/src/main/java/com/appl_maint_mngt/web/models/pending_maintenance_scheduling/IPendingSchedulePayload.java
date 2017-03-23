package com.appl_maint_mngt.web.models.pending_maintenance_scheduling;

import com.appl_maint_mngt.models.pending_maintenance_scheduling.SchedulerType;

import java.sql.Timestamp;

public interface IPendingSchedulePayload {

	Long getReportId();
	
	Timestamp getStartTime();
	
	Timestamp getEndTime();
	
	SchedulerType getSchedulerType();
	
	void setReportId(Long id);
	
	void setStartTime(Timestamp time);
	
	void setEndTime(Timestamp time);
	
	void setSchedulerType(SchedulerType type);
}
