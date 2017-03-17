package com.appl_maint_mngt.controllers.account;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.account.LoginForm;
import com.appl_maint_mngt.services.account.IUserAuthService;
import com.appl_maint_mngt.services.account.IUserProfileService;
import com.appl_maint_mngt.services.common.ServiceFactory;

/**
 * Created by Kyle on 15/03/2017.
 */
public class AccountController implements IAccountController {

    private IUserAuthService authService;
    private IUserProfileService profileService;

    public AccountController() {
        authService = ServiceFactory.getInstance().getUserAuthService();
        profileService = ServiceFactory.getInstance().getUserProfileService();
    }

    @Override
    public void login(LoginForm form, IErrorCallback callback) {
        authService.postLogin(form, callback);
    }

    @Override
    public void getProfile(Long id, IErrorCallback errorCallback) {
        profileService.get(id, errorCallback);
    }
}
