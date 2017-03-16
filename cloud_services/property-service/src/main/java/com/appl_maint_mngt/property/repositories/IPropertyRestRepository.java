package com.appl_maint_mngt.property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.property.models.Property;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPropertyRestRepository extends JpaRepository<Property, Long> {

}
