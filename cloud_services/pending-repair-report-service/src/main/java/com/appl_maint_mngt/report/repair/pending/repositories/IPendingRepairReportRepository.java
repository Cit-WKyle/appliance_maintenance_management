package com.appl_maint_mngt.report.repair.pending.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

@Repository
public interface IPendingRepairReportRepository extends JpaRepository<PendingRepairReport, Long> {

	List<PendingRepairReport> findByDiagnosticReportId(Long id);
	List<PendingRepairReport> findByOrganisationId(Long id);
	List<PendingRepairReport> findByEngineerId(Long id);
}
