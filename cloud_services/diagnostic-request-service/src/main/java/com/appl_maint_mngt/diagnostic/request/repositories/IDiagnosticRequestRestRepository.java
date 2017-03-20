package com.appl_maint_mngt.diagnostic.request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.diagnostic.request.models.DiagnosticRequest;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IDiagnosticRequestRestRepository extends JpaRepository<DiagnosticRequest, Long> {

}
