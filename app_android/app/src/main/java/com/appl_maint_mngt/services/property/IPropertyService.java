package com.appl_maint_mngt.services.property;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyService {

    void findByIds(List<Long> ids, IErrorCallback callback);

    void get(Long id, IErrorCallback callback);
}
