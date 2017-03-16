package com.appl_maint_mngt.report.repair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.report.repair.models.RepairReport;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IRepairReportRestRepository extends JpaRepository<RepairReport, Long> {

}
