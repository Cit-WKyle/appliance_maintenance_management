package com.appl_maint_mngt.services.property_appliance;

import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.property_appliance.PropertyAppliance;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.property_appliance.IPropertyApplianceResources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyApplianceService implements IPropertyApplianceService{

    private AsyncHttpClient httpClient;
    private IPropertyApplianceUpdateableRepository repository;

    public PropertyApplianceService() {
        this.httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateablePropertyApplianceRepository();
    }

    @Override
    public void findByPropertyId(Long propertyId, final IErrorCallback callback) {
        httpClient.get(IPropertyApplianceResources.FIND_BY_PROPERTY_ID_RESOURCE, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                String data = "";
                try {
                    data = response.getJSONObject(IWebConstants.REST_REPO_EMBEDDED_KEY).getJSONArray(IWebConstants.REST_REPO_DATA_KEY).toString();
                } catch (JSONException e) {
                    ErrorPayload err = new ErrorPayload();
                    List<String> errs = new ArrayList<>();
                    errs.add(e.getMessage());
                    err.setErrors(errs);
                    callback.callback(err);
                    e.printStackTrace();
                }

                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<List<PropertyAppliance>>(){}.getType();
                List<PropertyAppliance> propAppls = gson.fromJson(data, responseType);
                repository.addItems(propAppls);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                callback.callback(err);
            }
        });
    }
}
