package com.appl_maint_mngt.models.maintenance_engineer;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IMaintenanceEngineerReadable {

    public Long getCurrentOrganisationId() ;

    public List<Long> getPreviousOrganisationIds();
}
