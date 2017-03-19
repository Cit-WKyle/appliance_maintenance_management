package com.appl_maint_mngt.web.constants.property;

import com.appl_maint_mngt.web.constants.common.IWebConstants;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IPropertyResources {
    String PROPERTY_PATH = "/property";
    String DATA_PATH = "/data";
    String SEARCH_PATH = "/search";
    String FIND_BY_ID_IN_RESORUCE =  IWebConstants.API_URL + PROPERTY_PATH + DATA_PATH + SEARCH_PATH + "/findByIdIn";
    String GET_RESOURCE = IWebConstants.API_URL + PROPERTY_PATH + DATA_PATH + "/";
}
