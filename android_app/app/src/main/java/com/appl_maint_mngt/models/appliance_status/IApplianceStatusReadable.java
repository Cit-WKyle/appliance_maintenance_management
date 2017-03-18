package com.appl_maint_mngt.models.appliance_status;

import com.appl_maint_mngt.models.appliance.ApplianceType;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceStatusReadable {
    Long getId();
    StatusType getType();
    String getMessage();
    String getIconUrl();
    ApplianceType getApplianceType();
}
