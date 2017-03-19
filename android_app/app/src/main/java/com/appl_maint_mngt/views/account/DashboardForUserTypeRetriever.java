package com.appl_maint_mngt.views.account;

import com.appl_maint_mngt.models.account.constants.UserType;
import com.appl_maint_mngt.views.property_manager.PropertyManagerDashboardActivity;
import com.appl_maint_mngt.views.property_tenant.PropertyTenantDashboardActivity;

/**
 * Created by Kyle on 15/03/2017.
 */
public class DashboardForUserTypeRetriever implements IDashboardForUserTypeRetriever {

    @Override
    public Class get(UserType type) {
        switch(type) {
            case MAINTENANCE_ENGINEER:
                return null;
            case PROPERTY_MANAGER:
                return PropertyManagerDashboardActivity.class;
            case PROPERTY_TENANT:
                return PropertyTenantDashboardActivity.class;
            default:
                return null;
        }
    }
}
