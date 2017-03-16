package com.appl_maint_mngt.report.repair.pending.services;

import java.util.List;

import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

public interface IPendingRepairReportService {
	
	PendingRepairReport getforId(Long id);

	boolean doesPendingReportExist(Long id);
	
	boolean acceptPendingReport(Long id);
	
	boolean declinePendingReport(Long id);
	
	PendingRepairReport createPendingRepairReport(PendingRepairReport report);
	
	List<PendingRepairReport> getPendingForDiagnosticReportId(Long diagRepId);
	List<PendingRepairReport> getPendingForOrganisationId(Long orgId);
	
	boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId);
	
	boolean isPendingReportAcceptedOrPendingForOrgAndDiagRep(Long orgId, Long diagRepId);

}
