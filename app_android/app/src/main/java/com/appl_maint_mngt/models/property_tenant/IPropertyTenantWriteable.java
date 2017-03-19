package com.appl_maint_mngt.models.property_tenant;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IPropertyTenantWriteable {

    void setCurrentPropertyId(Long currentPropertyId);

    void setPreviousPropertyIds(List<Long> previousPropertyIds);

    void setResidenceStatus(ResidenceStatus residenceStatus);
}
