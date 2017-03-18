package com.appl_maint_mngt.models.property_appliance;

import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IPropertyApplianceReadable {

    Long getId();

    String getApplianceId();

    Long getPropertyId();

    Long getStatusId();

    String getName();

    int getAge();

    List<IStatusHistoryReadable> getStatusHistory();
}
