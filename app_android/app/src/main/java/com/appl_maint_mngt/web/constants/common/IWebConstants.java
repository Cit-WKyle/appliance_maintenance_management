package com.appl_maint_mngt.web.constants.common;

import cz.msebera.android.httpclient.entity.ContentType;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IWebConstants {

    String API_URL = "http://192.168.1.5:8200/api";

    String CONTENT_TYPE_JSON = ContentType.APPLICATION_JSON.getMimeType();

    String REST_REPO_EMBEDDED_KEY = "_embedded";
    String REST_REPO_DATA_KEY = "data";

    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
}
