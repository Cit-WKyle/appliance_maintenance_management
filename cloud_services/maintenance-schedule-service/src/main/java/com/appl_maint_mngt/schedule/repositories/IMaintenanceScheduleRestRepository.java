package com.appl_maint_mngt.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.schedule.models.MaintenanceSchedule;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IMaintenanceScheduleRestRepository extends JpaRepository<MaintenanceSchedule, Long> {

}
