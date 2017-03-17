package com.appl_maint_mngt.services.account;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IUserProfileService {

    void get(Long id, IErrorCallback errorCallback);
}
