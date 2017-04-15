package com.appl_maint_mngt.report.repair.pending.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.report.repair.pending.clients.http.IDiagnosticRequestsClient;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;
import com.appl_maint_mngt.report.repair.pending.services.IPendingRepairReportService;
import com.appl_maint_mngt.report.repair.pending.web.constants.IResponseMessages;

@RestController
@RequestMapping("/")
public class PendingRepairReportRestController implements IPendingRepairReportRestApi {
	
	@Autowired
	private IDiagnosticRequestsClient diagReqClient;
	
	@Autowired
	private IPendingRepairReportService pendRepService;

	@Override
	public ApiResponse<PendingRepairReport> acceptReport(@PathVariable("id") Long id) {
		if(!pendRepService.doesPendingReportExist(id)) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.PEND_REP_DOESNT_EXIST_ERR, null);
		
		PendingRepairReport rep = pendRepService.getforId(id);
		
		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(rep.getDiagnosticReportId())) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.REP_ACCEPTED_ERR, null);
		
		PendingRepairReport report = pendRepService.acceptPendingReport(id);
		
		if(report == null) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.ACCEPT_FAILED_ERR, null);

		ResponseEntity<Boolean> diagReqResp = diagReqClient.deleteForDiagnosticReportId(rep.getDiagnosticReportId());
		if(!diagReqResp.getBody()) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.ACCEPT_FAILED_ERR, null);
			
		return new ApiResponse<PendingRepairReport>(ApiResponseStatus.SUCCESS, IResponseMessages.ACCEPT_SUCCESS, report);
	}

	@Override
	public ApiResponse<PendingRepairReport> declineReport(@PathVariable("id") Long id) {
		if(!pendRepService.doesPendingReportExist(id)) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.PEND_REP_DOESNT_EXIST_ERR, null);
		
		PendingRepairReport rep = pendRepService.getforId(id);
		
		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(rep.getDiagnosticReportId())) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.REP_ACCEPTED_ERR, null);
		
		PendingRepairReport report = pendRepService.declinePendingReport(id);
		
		if(report == null) return new ApiResponse<PendingRepairReport>(ApiResponseStatus.ERROR, IResponseMessages.DECLINE_FAILED_ERR, null);

		return new ApiResponse<PendingRepairReport>(ApiResponseStatus.SUCCESS, IResponseMessages.DECLINE_SUCCESS, report);
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

	@Override
	public ApiResponse<PendingRepairReport> get(@RequestParam("diagRepId") Long diagRepId, @RequestParam("orgId") Long orgId) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.GENERIC_SUCCESS, pendRepService.getForRequest(diagRepId, orgId));
	}

	@Override
	public ApiResponse<List<PendingRepairReport>> getPendingForEngineer(@PathVariable("id") Long engId) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.GENERIC_SUCCESS, pendRepService.getPendingForEngineerId(engId));
	}

	@Override
	public ResponseEntity<List<PendingRepairReport>> getPendingForDiagnosticReport(@RequestParam("diagRepIds") Long[] diagRepIds) {
		return new ResponseEntity<List<PendingRepairReport>>(pendRepService.getPendingForDiagnosticReportIds(diagRepIds), HttpStatus.OK);
	}

}
