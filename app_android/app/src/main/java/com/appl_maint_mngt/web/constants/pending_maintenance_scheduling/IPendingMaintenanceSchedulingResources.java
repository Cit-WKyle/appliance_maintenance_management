package com.appl_maint_mngt.web.constants.pending_maintenance_scheduling;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 22/03/2017.
 */

public interface IPendingMaintenanceSchedulingResources {
    String PENDING_MAINTENANCE_SCHEDULING_PATH = "/pending-maintenance-scheduling";
    String PENDING_PATH = "/pending";

    String ACCEPT_PATH = "/accept";
    String DECLINE_PATH = "/decline";

    String BASE_RESOURCE = IWebConstants.API_URL + PENDING_MAINTENANCE_SCHEDULING_PATH + "/";
    String ADD_RESOURCE = BASE_RESOURCE;
    String REPORT_RESOURCE = IWebConstants.API_URL + PENDING_MAINTENANCE_SCHEDULING_PATH + "/report/";
}
