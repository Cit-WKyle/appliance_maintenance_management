package com.appl_maint_mngt.report.diagnostic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.report.diagnostic.models.DiagnosticReport;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IDiagnosticReportRestRepository extends JpaRepository<DiagnosticReport, Long> {

}
