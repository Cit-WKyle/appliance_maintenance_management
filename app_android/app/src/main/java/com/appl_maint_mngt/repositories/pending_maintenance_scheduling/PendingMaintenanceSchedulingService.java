package com.appl_maint_mngt.repositories.pending_maintenance_scheduling;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.services.diagnostic_report.DiagnosticReportService;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.pending_maintenance_scheduling.IPendingMaintenanceSchedulingResources;
import com.appl_maint_mngt.web.models.common.ApiResponse;
import com.appl_maint_mngt.web.models.pending_maintenance_scheduling.IPendingSchedulePayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

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
 * Created by Kyle on 22/03/2017.
 */

public class PendingMaintenanceSchedulingService implements IPendingMaintenanceSchedulingService {

    private static final Logger logger = LoggerManager.getLogger(PendingMaintenanceSchedulingService.class);

    private Context context;
    private AsyncHttpClient httpClient;

    public PendingMaintenanceSchedulingService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();
    }

    @Override
    public void add(IPendingSchedulePayload payload, final IErrorCallback errorCallback) {
        Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.d(e, "Unsupported encoding diagnostic report");
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            errorCallback.callback(err);
        }
        httpClient.post(context, IPendingMaintenanceSchedulingResources.ADD_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.d("onSuccess post: %s", response.toString());

                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<ApiResponse<Boolean>>(){}.getType();
                ApiResponse<Boolean> apiResponse = gson.fromJson(response.toString(), responseType);
                if(apiResponse.getData()) {

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
