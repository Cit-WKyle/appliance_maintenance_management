package com.appl_maint_mgt.property_appliance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mgt.property_appliance.models.PropertyAppliance;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPropertyApplianceRestRepository extends JpaRepository<PropertyAppliance, Long> {

}
