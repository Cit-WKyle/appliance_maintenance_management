package com.appl_maint_mngt.report.repair.pending.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;
import com.appl_maint_mngt.report.repair.pending.services.IPendingRepairReportService;
import com.appl_maint_mngt.report.repair.pending.web.constants.IResponseMessages;

@RestController
@RequestMapping("/")
public class PendingRepairReportRestController implements IPendingRepairReportRestApi {
	
	@Autowired
	private IPendingRepairReportService pendRepService;

	@Override
	public ApiResponse<Boolean> acceptReport(@PathVariable("id") Long id) {
		if(!pendRepService.doesPendingReportExist(id)) return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.PEND_REP_DOESNT_EXIST_ERR, false);
		
		PendingRepairReport rep = pendRepService.getforId(id);
		
		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(rep.getDiagnosticReportId())) return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.REP_ACCEPTED_ERR, false);
		
		boolean res = pendRepService.acceptPendingReport(id);
		
		if(!res) return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.ACCEPT_FAILED_ERR, false);

		return new ApiResponse<Boolean>(ApiResponseStatus.SUCCESS, IResponseMessages.ACCEPT_SUCCESS, true);
	}

	@Override
	public ApiResponse<Boolean> declineReport(@PathVariable("id") Long id) {
		if(!pendRepService.doesPendingReportExist(id)) return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.PEND_REP_DOESNT_EXIST_ERR, false);
		
		PendingRepairReport rep = pendRepService.getforId(id);
		
		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(rep.getDiagnosticReportId())) return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.REP_ACCEPTED_ERR, false);
		
		boolean res = pendRepService.declinePendingReport(id);
		
		if(!res) return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.DECLINE_FAILED_ERR, false);

		return new ApiResponse<Boolean>(ApiResponseStatus.SUCCESS, IResponseMessages.DECLINE_SUCCESS, true);
	}

	@Override
	public ApiResponse<PendingRepairReport> create(@Valid @RequestBody PendingRepairReport report) {
		boolean orgInvalid = pendRepService.isPendingReportAcceptedOrPendingForOrgAndDiagRep(report.getOrganisationId(), report.getDiagnosticReportId());
		
		if(orgInvalid) return new ApiResponse<>(ApiResponseStatus.ERROR, IResponseMessages.ORG_EXISTS_ERR, null);
		
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.CREATE_SUCCESS, pendRepService.createPendingRepairReport(report));
	}

	@Override
	public ApiResponse<List<PendingRepairReport>> getPendingForDiagnosticReport(@PathVariable("id") Long diagRepId) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.GENERIC_SUCCESS, pendRepService.getPendingForDiagnosticReportId(diagRepId));
	}

	@Override
	public ApiResponse<List<PendingRepairReport>> getPendingForOrganisation(@PathVariable("id") Long orgId) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.GENERIC_SUCCESS, pendRepService.getPendingForOrganisationId(orgId));
	}

}
