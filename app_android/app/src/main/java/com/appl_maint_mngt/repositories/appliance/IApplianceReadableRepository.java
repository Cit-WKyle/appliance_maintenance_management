package com.appl_maint_mngt.repositories.appliance;

import com.appl_maint_mngt.models.appliance.IApplianceReadable;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceReadableRepository {

    IApplianceReadable get(String id);
}
