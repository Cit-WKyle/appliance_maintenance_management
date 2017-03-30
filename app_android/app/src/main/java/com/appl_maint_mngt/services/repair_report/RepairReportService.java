package com.appl_maint_mngt.services.repair_report;

import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.repair_report.RepairReport;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.repair_report.IRepairReportResources;
import com.appl_maint_mngt.web.constants.repair_report.IRepairReportWebConstants;
import com.appl_maint_mngt.web.utility.RequestParamGenerators;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 21/03/2017.
 */

public class RepairReportService implements IRepairReportService {
    private static final Logger logger = LoggerManager.getLogger(RepairReportService.class);

    private AsyncHttpClient httpClient;
    private IRepairReportUpdateableRepository repository;

    public RepairReportService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableRepairReportRepository();
    }

    @Override
    public void findByDiagnosticReportId(Long id, final IErrorCallback errorCallback) {
        logger.d("findByDiagnosticReportId get: %d", id);
        RequestParams params = new RequestParams();
        params.put(IRepairReportWebConstants.DIAGNOSTIC_REPORT_ID_PARAM, id);
        httpClient.get(IRepairReportResources.FIND_BY_DIAG_REP_ID, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<RepairReport>(){}.getType();

                RepairReport repairReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(repairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "findByDiagnosticReportId onFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }

        });
    }

    @Override
    public void findByDiagnosticReportIdsIn(List<Long> ids, final IErrorCallback errorCallback) {
        if(ids.size() == 0) return;
        String params = RequestParamGenerators.generateRequestParamsForIds(ids, IRepairReportWebConstants.DIAGNOSTIC_REPORT_IDS_PARAM);
        logger.d("PARAMS: %s", params);
        httpClient.get(IRepairReportResources.FIND_BY_DIAG_REP_ID_IN + params, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<List<RepairReport>>(){}.getType();

                List<RepairReport> repairReport = gson.fromJson(response.toString(), responseType);
                repository.addItems(repairReport);
            }

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<RepairReport>(){}.getType();

                RepairReport repairReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(repairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "findByDiagnosticReportId onFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }

    @Override
    public void findByEngineerId(Long engineerId, final IErrorCallback errorCallback) {
        RequestParams params = new RequestParams();
        params.put(IRepairReportWebConstants.ENGINEER_ID_PARAM, engineerId);
        httpClient.get(IRepairReportResources.FIND_BY_ENGINEER_ID, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
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
                Type responseType = new TypeToken<List<RepairReport>>(){}.getType();

                List<RepairReport> repairReport = gson.fromJson(data, responseType);
                repository.addItems(repairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "findByDiagnosticReportId onFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }
}
