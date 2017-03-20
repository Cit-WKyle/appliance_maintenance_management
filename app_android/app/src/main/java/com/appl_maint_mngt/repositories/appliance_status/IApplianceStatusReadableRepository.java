package com.appl_maint_mngt.repositories.appliance_status;

import com.appl_maint_mngt.models.appliance.ApplianceType;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IApplianceStatusReadableRepository {

    IApplianceStatusReadable getForId(Long id);

    List<IApplianceStatusReadable> getForApplianceType(ApplianceType type);
}
