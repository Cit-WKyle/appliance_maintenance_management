package com.appl_maint_mngt.web.models.account;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IRegisterPayload {
    String getEmail();
    String getPassword();
    String getPasswordConfirmation();
    String getUserType();

    void setEmail(String email);
    void setPassword(String password);
    void setPasswordConfirmation(String passwordConfirmation);
    void setUserType(String userType);
}
