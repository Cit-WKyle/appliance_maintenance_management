package com.appl_maint_mngt.controllers.property;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyController {

    void getProperties(List<Long> ids, IErrorCallback callback);
}
