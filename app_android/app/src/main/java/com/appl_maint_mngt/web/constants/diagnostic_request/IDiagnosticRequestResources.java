package com.appl_maint_mngt.web.constants.diagnostic_request;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestResources {

    String DIAGNOSTIC_REQUEST_PATH = "/diagnostic-request";
    String DATA_PATH = "/data";
    String SEARCH_PATH= "/search";

    String POST_RESOURCE = IWebConstants.API_URL + DIAGNOSTIC_REQUEST_PATH + DATA_PATH  + "/";
    String PUT_RESOURCE = IWebConstants.API_URL + DIAGNOSTIC_REQUEST_PATH + DATA_PATH  + "/";
    String FIND_BY_DIAG_REP_ID = IWebConstants.API_URL + DIAGNOSTIC_REQUEST_PATH + DATA_PATH + SEARCH_PATH + "/findByDiagnosticReportId";
    String FIND_BY_MAINT_ORG_ID = IWebConstants.API_URL + DIAGNOSTIC_REQUEST_PATH + DATA_PATH + SEARCH_PATH + "/findByMaintenanceOrganisationId";
}
