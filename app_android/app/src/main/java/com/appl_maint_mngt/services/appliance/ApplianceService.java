package com.appl_maint_mngt.services.appliance;

import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.appliance.Appliance;
import com.appl_maint_mngt.repositories.appliance.IApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.web.constants.appliance.IApplianceResources;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
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
 * Created by Kyle on 18/03/2017.
 */
public class ApplianceService implements IApplianceService {
    private static final Logger logger = LoggerManager.getLogger(ApplianceService.class);

    private AsyncHttpClient httpClient;
    private IApplianceUpdateableRepository repository;

    public ApplianceService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableApplianceRepository();
    }

    @Override
    public void get(String id, final IErrorCallback errorCallback) {
        httpClient.get(IApplianceResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<Appliance>(){}.getType();
                Appliance appliance = gson.fromJson(response.toString(), responseType);
                repository.addItem(appliance);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "get/{id} onFailure %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }
}
