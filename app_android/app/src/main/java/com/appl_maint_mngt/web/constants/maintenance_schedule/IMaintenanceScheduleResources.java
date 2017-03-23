package com.appl_maint_mngt.web.constants.maintenance_schedule;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IMaintenanceScheduleResources {
    String MAINTENANCE_SCHEDULE_PATH = "/maintenance-schedule";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";

    String FIND_BY_REPAIR_REPORT_ID_RESOURCE = IWebConstants.API_URL + MAINTENANCE_SCHEDULE_PATH + DATA_PATH + SEARCH_PATH + "/findByRepairReportId";
}
