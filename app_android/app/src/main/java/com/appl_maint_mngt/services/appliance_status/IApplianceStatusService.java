package com.appl_maint_mngt.services.appliance_status;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.appliance.ApplianceType;

import java.util.Set;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IApplianceStatusService {

    void get(Long id, IErrorCallback errorCallback);

    void findByIdIn(Set<Long> ids, IErrorCallback errorCallback);

    void findByApplianceTypeIn(Set<ApplianceType> types, IErrorCallback errorCallback);
}
