package com.appl_maint_mngt.views.account;

import com.appl_maint_mngt.models.account.constants.UserType;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IDashboardForUserTypeRetriever {

    Class get(UserType type);
}
