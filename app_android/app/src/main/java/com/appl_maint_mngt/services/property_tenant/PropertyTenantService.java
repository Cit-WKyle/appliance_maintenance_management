package com.appl_maint_mngt.services.property_tenant;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.property_tenant.PropertyTenant;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.property_tenant.IPropertyTenantResources;
import com.appl_maint_mngt.web.models.property_tenant.PropertyTenantPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 19/03/2017.
 */
public class PropertyTenantService implements IPropertyTenantService {

    private AsyncHttpClient httpClient;
    private IPropertyTenantUpdateableRepository repository;
    private Context cntxt;

    public PropertyTenantService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateablePropertyTenantRepository();
        cntxt = MainActivity.getInstance();
    }

    @Override
    public void post(PropertyTenantPayload payload, final IErrorCallback errorCallback) {
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            errorCallback.callback(err);
        }
        httpClient.post(cntxt, IPropertyTenantResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<PropertyTenant>(){}.getType();
                PropertyTenant tnt = gson.fromJson(response.toString(), responseType);
                repository.update(tnt);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }

    @Override
    public void get(Long accountId, final IErrorCallback errorCallback) {
        httpClient.get(IPropertyTenantResources.GET_RESOURCE + accountId, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String data = "";
                try {
                    data = response.getJSONObject(IWebConstants.REST_REPO_EMBEDDED_KEY).getJSONObject(IWebConstants.REST_REPO_DATA_KEY).toString();
                } catch (JSONException e) {
                    ErrorPayload err = new ErrorPayload();
                    List<String> errs = new ArrayList<>();
                    errs.add(e.getMessage());
                    err.setErrors(errs);
                    errorCallback.callback(err);
                    e.printStackTrace();
                }
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<PropertyTenant>(){}.getType();
                PropertyTenant propTnt = gson.fromJson(data, responseType);
                repository.update(propTnt);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }
}
