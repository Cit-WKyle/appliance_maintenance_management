package com.appl_maint_mngt.web.models.account;

import com.appl_maint_mngt.models.account.constants.UserType;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAuthDetails {
    Long getId();
    String getEmail();
    UserType getUserType();
}
