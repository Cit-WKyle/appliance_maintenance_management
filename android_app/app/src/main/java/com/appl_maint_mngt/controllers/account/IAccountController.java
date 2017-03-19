package com.appl_maint_mngt.controllers.account;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.account.LoginForm;
import com.appl_maint_mngt.web.models.account.IRegisterPayload;
import com.appl_maint_mngt.web.models.account.UserProfile;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountController {

    void login(LoginForm form, IErrorCallback errCallback);

    void getAuthDetails(String token, IErrorCallback errorCallback);

    void getProfile(Long id, IErrorCallback errorCallback);

    void createAccount(IRegisterPayload reg, IErrorCallback errorCallback);

    void createProfile(UserProfile profile, IErrorCallback errorCallback);
}
