package com.appl_maint_mngt.services.property_tenant;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.property_tenant.PropertyTenantPayload;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IPropertyTenantService {

    void post(PropertyTenantPayload payload, IErrorCallback errorCallback);

    void get(Long accountId, IErrorCallback errorCallback);
}
