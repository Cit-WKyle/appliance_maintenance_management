package com.appl_maint_mngt.services.account;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.account.LoginForm;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IUserAuthService {

    void postLogin(LoginForm form, IErrorCallback errorCallback);
}
