package com.appl_maint_mngt.report.repair.pending.services;

import java.util.List;

import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

public interface IPendingRepairReportService {
	
	PendingRepairReport getforId(Long id);

	boolean doesPendingReportExist(Long id);
	
	PendingRepairReport acceptPendingReport(Long id);
	
	PendingRepairReport declinePendingReport(Long id);
	
	PendingRepairReport createPendingRepairReport(PendingRepairReport report);
	
	List<PendingRepairReport> getPendingForDiagnosticReportIds(Long[] diagRepIds);
	List<PendingRepairReport> getPendingForDiagnosticReportId(Long diagRepId);
	List<PendingRepairReport> getPendingForOrganisationId(Long orgId);
	List<PendingRepairReport> getPendingForEngineerId(Long engId);
	
	boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId);
	
	boolean isPendingReportAcceptedOrPendingForOrgAndDiagRep(Long orgId, Long diagRepId);
	
	PendingRepairReport getForRequest(Long diagRepId, Long orgId);

}