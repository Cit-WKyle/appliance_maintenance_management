package com.appl_maint_mngt.models.maintenance_organisation;

import com.appl_maint_mngt.models.address.Address;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IMaintenanceOrganisationWriteable {

    void setId(Long id);
    void setName(String name);
    void setDescription(String description);
    void setAddress(Address address);
}
