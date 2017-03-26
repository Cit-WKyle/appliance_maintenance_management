package com.appl_maint_mngt.web.constants.diagnostic_report;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportResources {

    String DIAGNOSTIC_REPORT_PATH = "/diagnostic-report";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";

    String POST_RESOURCE = IWebConstants.API_URL + DIAGNOSTIC_REPORT_PATH + DATA_PATH  + "/";
    String FIND_BY_PROP_APPL_ID_RESOURCE = IWebConstants.API_URL + DIAGNOSTIC_REPORT_PATH + DATA_PATH + SEARCH_PATH + "/findByPropApplId";
    String FIND_BY_IDS_RRESOURCE = IWebConstants.API_URL + DIAGNOSTIC_REPORT_PATH + DATA_PATH + SEARCH_PATH + "/findByIdIn";
}
