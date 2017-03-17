package com.appl_maint_mngt.controllers.account;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.account.LoginForm;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountController {

    void login(LoginForm form, IErrorCallback errCallback);

    void getProfile(Long id, IErrorCallback errorCallback);
}
