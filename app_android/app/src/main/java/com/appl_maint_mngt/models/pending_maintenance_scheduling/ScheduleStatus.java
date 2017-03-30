package com.appl_maint_mngt.models.pending_maintenance_scheduling;

import com.google.gson.annotations.SerializedName;

public enum ScheduleStatus {

	@SerializedName("ACCEPTED")
	ACCEPTED,

	@SerializedName("DECLINED")
	DECLINED,

	@SerializedName("PENDING")
	PENDING
}
