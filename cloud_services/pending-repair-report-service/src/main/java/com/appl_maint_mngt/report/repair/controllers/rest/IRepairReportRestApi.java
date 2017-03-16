package com.appl_maint_mngt.report.repair.controllers.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.report.repair.models.IRepairReport;

public interface IRepairReportRestApi {

	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody IRepairReport create(@RequestBody IRepairReport report);
}
