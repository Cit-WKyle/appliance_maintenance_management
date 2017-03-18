package com.appl_maint_mngt.models.appliance_status;

import com.appl_maint_mngt.models.appliance.ApplianceType;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceStatusWriteable {
    void setId(Long id);
    void setType(StatusType type);
    void setMessage(String message);
    void setIconUrl(String iconUrl);
    void setApplianceType(ApplianceType applianceType);
}
