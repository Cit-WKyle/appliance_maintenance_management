package com.appl_maint_mngt.services.common;

import com.appl_maint_mngt.services.account.IUserAuthService;
import com.appl_maint_mngt.services.account.IUserProfileService;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IServiceFactory {

    IUserAuthService getUserAuthService();

    IUserProfileService getUserProfileService();
}
