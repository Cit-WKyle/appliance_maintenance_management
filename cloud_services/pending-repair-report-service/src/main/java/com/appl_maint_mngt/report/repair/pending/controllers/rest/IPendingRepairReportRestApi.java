package com.appl_maint_mngt.report.repair.pending.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

public interface IPendingRepairReportRestApi {

	@RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
	@ResponseBody ApiResponse<Boolean> acceptReport(@PathVariable("id") Long id);
	
	@RequestMapping(value="/{id}/decline", method=RequestMethod.POST)
	@ResponseBody ApiResponse<Boolean> declineReport(@PathVariable("id") Long id);
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingRepairReport> create(@Valid @RequestBody PendingRepairReport report);
	
	@RequestMapping(value="/pending/diagnostic-report/{id}", method=RequestMethod.GET)
	@ResponseBody ApiResponse<List<PendingRepairReport>> getPendingForDiagnosticReport(@PathVariable("id") Long diagRepId);
	
	@RequestMapping(value="/pending/organisation/{id}", method=RequestMethod.GET)
	@ResponseBody ApiResponse<List<PendingRepairReport>> getPendingForOrganisation(@PathVariable("id") Long orgId);
}
