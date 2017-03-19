package com.appl_maint_mngt.web.models.maintenance_engineer;

/**
 * Created by Kyle on 19/03/2017.
 */

public class MaintenanceEngineerPayload {

    private Long accountId;
    private Long currentOrganisationId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCurrentOrganisationId() {
        return currentOrganisationId;
    }

    public void setCurrentOrganisationId(Long currentOrganisationId) {
        this.currentOrganisationId = currentOrganisationId;
    }
}
