package com.appl_maint_mngt.services.property;

import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.property.Property;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property.IPropertyUpdateableRepository;
import com.appl_maint_mngt.services.property_manager.PropertyManagerService;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.property.IPropertyResources;
import com.appl_maint_mngt.web.constants.property.IPropertyWebConstants;
import com.appl_maint_mngt.web.utility.RequestParamGenerators;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyService implements IPropertyService {
    private static final Logger logger = LoggerManager.getLogger(PropertyService.class);

    private AsyncHttpClient httpClient;
    private IPropertyUpdateableRepository repository;

    public PropertyService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateablePropertyRepository();
    }

    @Override
    public void findByIds(List<Long> ids, final IErrorCallback callback) {
        String params = RequestParamGenerators.generateRequestParamsForIds(ids, IPropertyWebConstants.IDS_PARAM);
        httpClient.get(IPropertyResources.FIND_BY_ID_IN_RESORUCE + params, new RequestParams(), new JsonHttpResponseHandler() {

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
                Type responseType = new TypeToken<List<Property>>(){}.getType();
                List<Property> props = gson.fromJson(data, responseType);
                repository.addItems(props);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "Entered getProperties onFailure response: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                callback.callback(err);
            }

        });
    }

    @Override
    public void get(Long id, final IErrorCallback callback) {
        httpClient.get(IPropertyResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<Property>(){}.getType();
                Property prop = gson.fromJson(response.toString(), responseType);
                repository.addItem(prop);
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
