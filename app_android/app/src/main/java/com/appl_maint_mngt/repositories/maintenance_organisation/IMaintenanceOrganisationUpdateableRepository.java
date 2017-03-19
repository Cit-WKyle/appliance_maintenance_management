package com.appl_maint_mngt.repositories.maintenance_organisation;

import com.appl_maint_mngt.models.maintenance_organisation.MaintenanceOrganisation;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IMaintenanceOrganisationUpdateableRepository {

    void addItems(List<MaintenanceOrganisation> maintOrg);
}
