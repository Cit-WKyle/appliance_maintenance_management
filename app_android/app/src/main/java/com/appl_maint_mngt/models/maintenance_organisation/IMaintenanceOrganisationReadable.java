package com.appl_maint_mngt.models.maintenance_organisation;

import com.appl_maint_mngt.models.address.Address;

/**
 * Created by Kyle on 18/03/2017.
 */
public interface IMaintenanceOrganisationReadable {
    Long getId();

    String getName();

    String getDescription();

    Address getAddress();
}
