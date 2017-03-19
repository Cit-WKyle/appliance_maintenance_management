package com.appl_maint_mngt.repositories.appliance_status;

import com.appl_maint_mngt.models.appliance_status.ApplianceStatus;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IApplianceStatusUpdateableRepository {

    void additem(ApplianceStatus status);

    void addItems(List<ApplianceStatus> statuses);
}
