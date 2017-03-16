package com.appl_maint_mngt.property.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appl_maint_mngt.property.manager.models.PropertyManager;

@Repository
public interface IPropertyManagerRepository extends JpaRepository<PropertyManager, Long> {

}
