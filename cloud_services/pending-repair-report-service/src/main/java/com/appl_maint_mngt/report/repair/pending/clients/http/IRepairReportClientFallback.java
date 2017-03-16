package com.appl_maint_mngt.report.repair.pending.clients.http;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.report.repair.models.IRepairReport;

@Component
public class IRepairReportClientFallback implements IRepairReportClient {

	@Override
	public IRepairReport create(IRepairReport report) {
		return null;
	}

}
