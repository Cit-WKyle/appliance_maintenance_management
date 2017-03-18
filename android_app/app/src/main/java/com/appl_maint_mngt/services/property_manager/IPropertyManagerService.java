package com.appl_maint_mngt.services.property_manager;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyManagerService {

    void get(Long accountId, IErrorCallback errorCallback);
}
