package com.appl_maint_mngt.services.diagnostic_report;

import android.app.DownloadManager;
import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;
import com.appl_maint_mngt.models.diagnostic_report.DiagnosticReport;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.diagnostic_report.IDiagnosticReportResources;
import com.appl_maint_mngt.web.constants.diagnostic_report.IDiagnosticReportWebConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONArray;
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
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReportService implements IDiagnosticReportService {
    private static final Logger logger = LoggerManager.getLogger(DiagnosticReportService.class);

    private AsyncHttpClient httpClient;
    private IDiagnosticReportUpdateableRepository repository;
    private Context cntxt;

    public DiagnosticReportService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableDiagnosticReportRepository();
        cntxt = MainActivity.getInstance();
    }

    @Override
    public void post(DiagnosticReportForm diagRep, final IErrorCallback callback) {
        Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(diagRep));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.d(e, "Unsupported encoding diagnostic report");
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            callback.callback(err);
        }
        logger.d("Payload pre-post : %s", gson.toJson(diagRep));
        httpClient.post(cntxt, IDiagnosticReportResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<DiagnosticReport>(){}.getType();

                DiagnosticReport diagRep = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagRep);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.d(throwable, "oNFailure post: %s", response.toString());
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                err.setErrors(errs);
                callback.callback(err);
            }

            @Override
            public void onFailure(int c, Header[] h, String s, Throwable t) {
                logger.d(t, "oNFailure post: %d %s", c, s);
            }
        });
    }

    @Override
    public void getForPropertyApplianceId(Long propertyApplianceId, final IErrorCallback errorCallback) {
        RequestParams params = new RequestParams();
        params.put(IDiagnosticReportWebConstants.PROP_APPL_ID_PARAM, propertyApplianceId);
        httpClient.get(IDiagnosticReportResources.FIND_BY_PROP_APPL_ID_RESOURCE, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                logger.d("onSuccess post: %s", response.toString());
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<List<DiagnosticReport>>(){}.getType();

                List<DiagnosticReport> diagReps = gson.fromJson(response.toString(), responseType);
                repository.addItems(diagReps);
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
