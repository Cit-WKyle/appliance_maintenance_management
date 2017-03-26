package com.appl_maint_mngt.services.diagnostic_request;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.diagnostic_request.DiagnosticRequest;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.diagnostic_report.IDiagnosticReportResources;
import com.appl_maint_mngt.web.constants.diagnostic_request.IDiagnosticRequestResources;
import com.appl_maint_mngt.web.constants.diagnostic_request.IDiagnosticRequestWebCosntants;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestPayload;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestUpdatePayload;
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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 21/03/2017.
 */

public class DiagnosticRequestService implements IDiagnosticRequestService {
    private static final Logger logger = LoggerManager.getLogger(DiagnosticRequestService.class);

    private Context cntxt;
    private AsyncHttpClient httpClient;
    private IDiagnosticRequestUpdateableRepository repository;

    public DiagnosticRequestService() {
        cntxt = MainActivity.getInstance();
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableDiagnosticRequestRepository();
    }

    @Override
    public void post(DiagnosticRequestPayload payload, final IErrorCallback errorCallback) {
        logger.d("In post for diagnostic request payload");
        Gson gson = new GsonBuilder().create();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.d(e, "Unsupported encoding diagnostic request");
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            errorCallback.callback(err);
        }
        httpClient.post(cntxt, IDiagnosticRequestResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<DiagnosticRequest>(){}.getType();

                DiagnosticRequest diagRep = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagRep);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }

            public void onFailure(int i, Header[] h , String s, Throwable t) {
                logger.d(t, "post onFailure post: %s %i", s, i);
            }
        });
    }

    @Override
    public void findByDiagnosticReportId(Long diagRepId, final IErrorCallback errorCallback) {
        logger.d("In findByDiagnosticReportId");
        RequestParams params = new RequestParams();
        params.put(IDiagnosticRequestWebCosntants.DIAG_REP_ID, diagRepId);
        httpClient.get(IDiagnosticRequestResources.FIND_BY_DIAG_REP_ID, params, new JsonHttpResponseHandler() {
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
                Type responseType = new TypeToken<List<DiagnosticRequest>>(){}.getType();

                List<DiagnosticRequest> diagRep = gson.fromJson(data, responseType);
                repository.addItems(diagRep);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }
            public void onFailure(int i, Header[] h , String s, Throwable t) {
                logger.d(t, "findByDiagnosticReportId onFailure post: %s %i", s, i);
            }
        });
    }

    @Override
    public void findByMaintenanceOrganisationId(Long maintOrgId, final IErrorCallback errorCallback) {
        logger.d("In findByMaintenanceOrganisationId");
        RequestParams params = new RequestParams();
        params.put(IDiagnosticRequestWebCosntants.MAINT_ORG_ID, maintOrgId);
        httpClient.get(IDiagnosticRequestResources.FIND_BY_MAINT_ORG_ID, params, new JsonHttpResponseHandler() {
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
                Type responseType = new TypeToken<List<DiagnosticRequest>>(){}.getType();

                List<DiagnosticRequest> diagRep = gson.fromJson(data, responseType);
                repository.addItems(diagRep);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }

            public void onFailure(int i, Header[] h , String s, Throwable t) {
                logger.d(t, "findByMainOrgId onFailure post: %s %i", s, i);
            }
        });
    }

    @Override
    public void put(DiagnosticRequestUpdatePayload payload, final IErrorCallback errorCallback) {
        logger.d("In post for diagnostic request payload");
        Gson gson = new GsonBuilder().create();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.d(e, "Unsupported encoding diagnostic request");
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            errorCallback.callback(err);
        }
        httpClient.put(cntxt, IDiagnosticRequestResources.PUT_RESOURCE + payload.getId(), entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<DiagnosticRequest>(){}.getType();

                DiagnosticRequest diagRep = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagRep);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                errorCallback.callback(err);
            }

            public void onFailure(int i, Header[] h , String s, Throwable t) {
                logger.d(t, "put onFailure post: %s %i", s, i);
            }
        });
    }
}
