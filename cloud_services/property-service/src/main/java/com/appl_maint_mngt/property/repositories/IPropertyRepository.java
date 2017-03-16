package com.appl_maint_mngt.property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appl_maint_mngt.property.models.Property;

@Repository
public interface IPropertyRepository extends JpaRepository<Property, Long> {

}
