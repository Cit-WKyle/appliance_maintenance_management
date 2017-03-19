package com.appl_maint_mngt.models.property_tenant;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IPropertyTenantReadable {

    public Long getCurrentPropertyId();

    public List<Long> getPreviousPropertyIds();

    public ResidenceStatus getResidenceStatus();
}
