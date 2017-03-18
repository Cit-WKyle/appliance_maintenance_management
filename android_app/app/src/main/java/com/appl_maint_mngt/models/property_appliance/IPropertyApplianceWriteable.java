package com.appl_maint_mngt.models.property_appliance;

import com.appl_maint_mngt.models.status_history.StatusHistory;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IPropertyApplianceWriteable {

    void setId(Long id);

    void setApplianceId(String applianceId);

    void setPropertyId(Long propertyId);

    void setStatusId(Long statusId);

    void setName(String name);

    void setAge(int age);

    void setStatusHistory(List<StatusHistory> statusHistory);
}
