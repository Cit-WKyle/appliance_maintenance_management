package com.appl_maint_mngt.appliance.status.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.appliance.status.models.ApplianceStatus;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IApplianceStatusRestRepository extends JpaRepository<ApplianceStatus, Long> {

}
