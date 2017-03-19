package com.appl_maint_mngt.web.constants.account;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IUserAuthResources {
    String USER_AUTH_PATH = "/user-authentication";
    String LOGIN_RESOURCE = IWebConstants.API_URL + USER_AUTH_PATH + "/login";
    String DETAILS_RESOURCE = IWebConstants.API_URL + USER_AUTH_PATH + "/details";
    String REGISTER_RESOURCE = IWebConstants.API_URL + USER_AUTH_PATH + "/register";
}
