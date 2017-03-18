package com.appl_maint_mngt.repositories.property_appliance;

import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyApplianceReadableRepository {

    IPropertyApplianceReadable getForId(Long id);

    List<IPropertyApplianceReadable> getAll();
}
