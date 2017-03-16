package com.appl_maint_mngt.maintenance.schedule.pending.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;
import com.appl_maint_mngt.maintenance.schedule.pending.models.converters.IPendingSchedulePayloadConverter;
import com.appl_maint_mngt.maintenance.schedule.pending.models.web.PendingSchedulePayload;
import com.appl_maint_mngt.maintenance.schedule.pending.services.ICreateMaintenanceScheduleService;
import com.appl_maint_mngt.maintenance.schedule.pending.services.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.maintenance.schedule.pending.web.constants.IResponseMessages;

@RestController
@RequestMapping("/")	
public class PendingMaintenanceSchedulingRestController implements IPendingMaintenanceSchedulingRestApi {
	
	@Autowired
	private IPendingMaintenanceSchedulingService pendMainSchedServ;
	
	@Autowired
	private ICreateMaintenanceScheduleService maintSchedService;
	
	@Autowired
	private IPendingSchedulePayloadConverter converter;

	@Override
	public ApiResponse<Boolean> accept(@PathVariable("id") Long id) {
		if(!pendMainSchedServ.doesItemExist(id)) {
			return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_ID_ERR, false);
		}
		Long reportId = pendMainSchedServ.getRepairReportForId(id);
		if(pendMainSchedServ.doesReportHaveAcceptedSchedule(reportId)) {
			return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.REP_SCHED_ACCEPTED_ERR_MSG, false);
		}
		boolean res = maintSchedService.attemptScheduleCreation(pendMainSchedServ.getForId(id));
		if(!res) {
			return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.CREATE_SCHED_ERR, false);
		}
		pendMainSchedServ.acceptPendingSchedule(id);
		return new ApiResponse<Boolean>(ApiResponseStatus.SUCCESS, IResponseMessages.ACCEPTED_MSG, true);
	}

	@Override
	public ApiResponse<Boolean> decline(@PathVariable("id") Long id) {
		if(!pendMainSchedServ.doesItemExist(id)) {
			return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_ID_ERR, false);
		}
		Long reportId = pendMainSchedServ.getRepairReportForId(id);
		if(pendMainSchedServ.doesReportHaveAcceptedSchedule(reportId)) {
			return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.REP_SCHED_ACCEPTED_ERR_MSG, false);
		}
		pendMainSchedServ.declinePendingSchedule(id);
		return new ApiResponse<Boolean>(ApiResponseStatus.SUCCESS, IResponseMessages.DECLINE_MSG, true);
	}

	@Override
	public ApiResponse<List<PendingMaintenanceSchedule>> getPendingSchedules(@PathVariable("id") Long id, @RequestParam("schedulerType") String type) {
		if(!pendMainSchedServ.doesItemExist(id)) {
			return new ApiResponse<>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_ID_ERR, new ArrayList<>());
		}
		SchedulerType schedType;
		try {
			schedType = SchedulerType.valueOf(type);
		} catch(Exception e) { 
			return new ApiResponse<>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_SCHED_TYPE, new ArrayList<>());
		}
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.GENERIC_SUCCESS, pendMainSchedServ.getAllForReportAndSchedulerType(id, schedType));
	}

	@Override
	public ApiResponse<Boolean> add(@Valid @RequestBody PendingSchedulePayload payload) {
		Long reportId = pendMainSchedServ.getRepairReportForId(payload.getReportId());
		if(pendMainSchedServ.doesReportHaveAcceptedSchedule(reportId)) {
			return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.REP_SCHED_ACCEPTED_ERR_MSG, false);
		}
		pendMainSchedServ.save(converter.toPendingMaintSched(payload));
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.CREATE_SUCCESS, true);
	}

}
