package com.appl_maint_mngt.web.constants.appliance_status;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IApplianceStatusResources {
    String APPLIANCE_STATUS_PATH = "/appliance-status";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";
    String FIND_BY_ID_IN_RESORUCE =  IWebConstants.API_URL + APPLIANCE_STATUS_PATH + DATA_PATH + SEARCH_PATH + "/findByIdIn";
    String FIND_BY_ID_APPLIANCE_TYPE_RESORUCE =  IWebConstants.API_URL + APPLIANCE_STATUS_PATH + DATA_PATH + SEARCH_PATH + "/findByApplianceTypeIn";
    String GET_RESOURCE =  IWebConstants.API_URL + APPLIANCE_STATUS_PATH + DATA_PATH +  "/";
}
