package com.appl_maint_mngt.web.constants.pending_repair_report;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportResources {
    String PENDING_REPAIR_REPORT_PATH = "/pending-repair-report";
    String GET_RESOURCE = IWebConstants.API_URL + PENDING_REPAIR_REPORT_PATH + "/";
    String BASE_PATH = IWebConstants.API_URL + PENDING_REPAIR_REPORT_PATH;
    String ACCEPT_ROUTE = "/accept";
    String DECLINE_ROUTE = "/decline";
}
