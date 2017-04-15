package com.appl_maint_mngt.report.repair.pending.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

public interface IPendingRepairReportRestApi {

	@RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingRepairReport> acceptReport(@PathVariable("id") Long id);
	
	@RequestMapping(value="/{id}/decline", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingRepairReport> declineReport(@PathVariable("id") Long id);
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingRepairReport> create(@Valid @RequestBody PendingRepairReport report);
	
	@RequestMapping(value="/pending/diagnostic-report/", method=RequestMethod.GET)
	@ResponseBody ResponseEntity<List<PendingRepairReport>> getPendingForDiagnosticReport(@RequestParam("diagRepIds") Long[] diagRepIds);
	
	@RequestMapping(value="/pending/diagnostic-report/{id}", method=RequestMethod.GET)
	@ResponseBody ApiResponse<List<PendingRepairReport>> getPendingForDiagnosticReport(@PathVariable("id") Long diagRepId);
	
	@RequestMapping(value="/pending/organisation/{id}", method=RequestMethod.GET)
	@ResponseBody ApiResponse<List<PendingRepairReport>> getPendingForOrganisation(@PathVariable("id") Long orgId);
	
	@RequestMapping(value="/pending/engineer/{id}", method=RequestMethod.GET)
	@ResponseBody ApiResponse<List<PendingRepairReport>> getPendingForEngineer(@PathVariable("id") Long engId);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody ApiResponse<PendingRepairReport> get(@RequestParam("diagRepId") Long diagRepId, @RequestParam("orgId") Long orgId);
}
