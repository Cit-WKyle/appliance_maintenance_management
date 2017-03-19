package com.appl_maint_mngt.web.constants.account;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IUserProfileResources {
    String USER_PROFILE_PATH = "/user-profile";
    String PROFILE_GET_RESOURCE = IWebConstants.API_URL + USER_PROFILE_PATH + "/";
    String PROFILE_CREATE_RESOURCE = IWebConstants.API_URL + USER_PROFILE_PATH + "/create";
}
