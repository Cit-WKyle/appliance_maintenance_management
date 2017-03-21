package com.appl_maint_mngt.repositories.maintenance_organisation;

import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IMaintenanceOrganisationReadableRepository {

    List<IMaintenanceOrganisationReadable> getAll();

    IMaintenanceOrganisationReadable getForId(Long id);
}
