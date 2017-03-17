package com.appl_maint_mngt.models.account;

import com.appl_maint_mngt.models.account.constants.UserType;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountWriteable {

    void setId(Long id);
    void setUserType(UserType type);
    void setEmail(String email);
    void setFirstName(String firstName);
    void setSurname(String surname);
    void setToken(String token);
}
