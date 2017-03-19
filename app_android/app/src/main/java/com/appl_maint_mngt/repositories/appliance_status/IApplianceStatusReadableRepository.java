package com.appl_maint_mngt.repositories.appliance_status;

import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IApplianceStatusReadableRepository {

    IApplianceStatusReadable getForId(Long id);
}
