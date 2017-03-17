package com.appl_maint_mngt.controllers.common;

import com.appl_maint_mngt.controllers.account.IAccountController;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IControllerFactory {
    IAccountController getAccountController();
}
