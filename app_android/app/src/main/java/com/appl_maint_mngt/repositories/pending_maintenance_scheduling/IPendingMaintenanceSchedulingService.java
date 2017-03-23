package com.appl_maint_mngt.repositories.pending_maintenance_scheduling;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.pending_maintenance_scheduling.IPendingSchedulePayload;

/**
 * Created by Kyle on 22/03/2017.
 */

public interface IPendingMaintenanceSchedulingService {

    void add(IPendingSchedulePayload payload, IErrorCallback errorCallback);
}
