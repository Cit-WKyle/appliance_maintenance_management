package com.appl_maint_mngt.services.appliance_status;

import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.appliance.ApplianceType;
import com.appl_maint_mngt.models.appliance_status.ApplianceStatus;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusObserverUpdateTypes;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.web.constants.appliance_status.IApplianceStatusResources;
import com.appl_maint_mngt.web.constants.appliance_status.IApplianceStatusWebConstants;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
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
import java.util.Set;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 17/03/2017.
 */
public class ApplianceStatusService implements IApplianceStatusService {
    private static final Logger logger = LoggerManager.getLogger(ApplianceStatusService.class);

    private AsyncHttpClient httpClient;
    private IApplianceStatusUpdateableRepository repository;

    public ApplianceStatusService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableApplianceStatusRepository();
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        httpClient.get(IApplianceStatusResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("Get onSuccess %s", response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApplianceStatus>(){}.getType();
                ApplianceStatus status = gson.fromJson(response.toString(), responseType);
                repository.addItem(status);
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
    public void findByIdIn(Set<Long> ids, final IErrorCallback errorCallback) {

        String params = RequestParamGenerators.generateRequestParamsForIds(new ArrayList<>(ids), IApplianceStatusWebConstants.IDS_PARAM);
        httpClient.get(IApplianceStatusResources.FIND_BY_ID_IN_RESORUCE + params, new RequestParams(), new JsonHttpResponseHandler() {

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
                    errorCallback.callback(err);
                    e.printStackTrace();
                }
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<List<ApplianceStatus>>(){}.getType();
                List<ApplianceStatus> statuses = gson.fromJson(data, responseType);
                repository.addItems(statuses);
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
    public void findByApplianceTypeIn(Set<ApplianceType> types, final IErrorCallback errorCallback) {
        List<String> typesStr = new ArrayList<>();
        for(ApplianceType type: types) {
            typesStr.add(type.toString());
        }
        String params = RequestParamGenerators.generateRequestParamsForStrings(typesStr, IApplianceStatusWebConstants.TYPES_PARAM);
        httpClient.get(IApplianceStatusResources.FIND_BY_ID_APPLIANCE_TYPE_RESORUCE + params, new RequestParams(), new JsonHttpResponseHandler() {

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
                    errorCallback.callback(err);
                    e.printStackTrace();
                }
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<List<ApplianceStatus>>(){}.getType();
                List<ApplianceStatus> statuses = gson.fromJson(data, responseType);
                repository.addItems(statuses, IApplianceStatusObserverUpdateTypes.TYPES_UPDATE);
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
