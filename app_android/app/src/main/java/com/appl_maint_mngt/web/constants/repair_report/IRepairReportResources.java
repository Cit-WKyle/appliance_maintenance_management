package com.appl_maint_mngt.web.constants.repair_report;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportResources {
    String REPAIR_REPORT_PATH = "/repair-report";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";
    String FIND_BY_DIAG_REP_ID = IWebConstants.API_URL + REPAIR_REPORT_PATH + DATA_PATH + SEARCH_PATH + "/findByDiagnosticReportId";
    String FIND_BY_DIAG_REP_ID_IN = IWebConstants.API_URL + REPAIR_REPORT_PATH + DATA_PATH + SEARCH_PATH + "/findByDiagnosticReportIdIn";
}
