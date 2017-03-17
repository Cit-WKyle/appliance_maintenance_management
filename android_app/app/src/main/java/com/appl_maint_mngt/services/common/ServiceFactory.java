package com.appl_maint_mngt.services.common;

import com.appl_maint_mngt.services.account.IUserProfileService;
import com.appl_maint_mngt.services.account.UserAuthService;
import com.appl_maint_mngt.services.account.IUserAuthService;
import com.appl_maint_mngt.services.account.UserProfileService;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ServiceFactory implements IServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();

    private IUserAuthService userAuthervice;
    private IUserProfileService profileService;

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }

    @Override
    public IUserAuthService getUserAuthService() {
        if(userAuthervice == null) userAuthervice = new UserAuthService();
        return userAuthervice;
    }

    @Override
    public IUserProfileService getUserProfileService() {
        if(profileService == null) profileService = new UserProfileService();
        return profileService;
    }
}
