package com.appl_maint_mngt.web.clients.common;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IRestClient {

    void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler);

    void post(String url, HttpEntity entity, JsonHttpResponseHandler responseHandler);

    AsyncHttpClient getClient();
}
