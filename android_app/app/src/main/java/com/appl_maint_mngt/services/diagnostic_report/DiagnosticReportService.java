package com.appl_maint_mngt.services.diagnostic_report;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

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
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReportService implements IDiagnosticReportService {

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
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(diagRep));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            callback.callback(err);
        }

        httpClient.post(cntxt, IDiagnosticReportResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<DiagnosticReport>(){}.getType();

                DiagnosticReport diagRep = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagRep);
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