package com.appl_maint_mngt.services.pending_repair_report;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.event_bus.common.LocalEventBus;
import com.appl_maint_mngt.event_bus.common.pending_repair_report.IPendingRepairReportEvents;
import com.appl_maint_mngt.models.pending_repair_report.PendingRepairReport;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.pending_repair_report.IPendingRepairReportResources;
import com.appl_maint_mngt.web.constants.pending_repair_report.IPendingRepairReportWebConstants;
import com.appl_maint_mngt.web.models.common.ApiResponse;
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
import cz.msebera.android.httpclient.entity.EntityTemplate;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Kyle on 21/03/2017.
 */

public class PendingRepairReportService implements IPendingRepairReportService {
    private static final Logger logger = LoggerManager.getLogger(PendingRepairReport.class);


    private AsyncHttpClient httpClient;
    private IPendingRepairReportUpdateableRepository repository;
    private Context cntxt;

    public PendingRepairReportService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateablePendingRepairReportRepository();
        cntxt = MainActivity.getInstance();
    }

    @Override
    public void get(Long diagRepId, Long orgId, final IErrorCallback errorCallback) {
        RequestParams params = new RequestParams();
        params.put(IPendingRepairReportWebConstants.DIAG_REP_ID_PARAM, diagRepId);
        params.put(IPendingRepairReportWebConstants.MAINT_ORG_ID_PARAM, orgId);
        httpClient.get(IPendingRepairReportResources.GET_RESOURCE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
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
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<PendingRepairReport>(){}.getType();

                PendingRepairReport pendRepRep = gson.fromJson(response.toString(), responseType);
                repository.addItem(pendRepRep);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }


        });
    }

    @Override
    public void accept(Long id, final IErrorCallback errorCallback) {
        httpClient.post(IPendingRepairReportResources.BASE_PATH + id + IPendingRepairReportResources.ACCEPT_ROUTE, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<Boolean>>(){}.getType();

                ApiResponse<Boolean> apiResp = gson.fromJson(response.toString(), responseType);
                if(apiResp.getData()) {
                    LocalEventBus.getInstance().sendEvent(IPendingRepairReportEvents.RESPONSE_ACCEPT_UPDATE_SUCCESS);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }

        });
    }

    @Override
    public void decline(Long id, final IErrorCallback errorCallback) {
        httpClient.post(IPendingRepairReportResources.BASE_PATH + id + IPendingRepairReportResources.DECLINE_ROUTE, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<Boolean>>(){}.getType();

                ApiResponse<Boolean> apiResp = gson.fromJson(response.toString(), responseType);

                if(apiResp.getData()) {
                    LocalEventBus.getInstance().sendEvent(IPendingRepairReportEvents.RESPONSE_DECLINE_UPDATE_SUCCESS);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }

        });
    }
}