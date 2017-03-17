package com.appl_maint_mngt.controllers.common;

import com.appl_maint_mngt.controllers.account.AccountController;
import com.appl_maint_mngt.controllers.account.IAccountController;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ControllerFactory implements IControllerFactory {

    private static ControllerFactory ourInstance = new ControllerFactory();

    private IAccountController accountController;


    public static ControllerFactory getInstance() {
        return ourInstance;
    }

    private ControllerFactory() {
    }

    @Override
    public IAccountController getAccountController() {
        if(accountController == null) accountController = new AccountController();
        return accountController;
    }
}
