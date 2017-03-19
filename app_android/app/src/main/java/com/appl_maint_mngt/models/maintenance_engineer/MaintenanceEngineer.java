package com.appl_maint_mngt.models.maintenance_engineer;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public class MaintenanceEngineer extends AMaintenanceEngineer {

    private Long currentOrganisationId;
    private List<Long> previousOrganisationIds;

    @Override
    public Long getCurrentOrganisationId() {
        return currentOrganisationId;
    }

    @Override
    public void setCurrentOrganisationId(Long currentOrganisationId) {
        this.currentOrganisationId = currentOrganisationId;
    }

    @Override
    public List<Long> getPreviousOrganisationIds() {
        return previousOrganisationIds;
    }

    @Override
    public void setPreviousOrganisationIds(List<Long> previousOrganisationIds) {
        this.previousOrganisationIds = previousOrganisationIds;
    }
}
