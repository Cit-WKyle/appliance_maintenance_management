package com.appl_maint_mngt.models.account;

import com.appl_maint_mngt.models.account.constants.UserType;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountReadable {

    Long getId();
    UserType getUserType();
    String getEmail();
    String getFirstName();
    String getSurname();
    String getToken();
}
