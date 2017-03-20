package com.appl_maint_mngt.services.property_appliance_status_update;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.event_bus.common.LocalEventBus;
import com.appl_maint_mngt.event_bus.common.property_appliance_status_update.IPropertyApplianceStatusUpdateEvents;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.property_appliance_status_update.IPropertyApplianceStatusUpdateResources;
import com.appl_maint_mngt.web.models.common.ApiResponse;
import com.appl_maint_mngt.web.models.property_appliance_status_update.PropertyApplianceStatusUpdatePayload;
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
 * Created by Kyle on 20/03/2017.
 */

public class PropertyApplianceStatusUpdateService implements IPropertyApplianceStatusUpdateService {
    private static final Logger logger = LoggerManager.getLogger(PropertyApplianceStatusUpdateService.class);

    private AsyncHttpClient httpClient;
    private Context cntxt;

    public PropertyApplianceStatusUpdateService() {
        httpClient = new AsyncHttpClient();
        cntxt = MainActivity.getInstance();
    }

    @Override
    public void update(PropertyApplianceStatusUpdatePayload payload, final IErrorCallback errorCallback) {
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.d(e, "UnsupportedEncodingException on %s", gson.toJson(payload));
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            errorCallback.callback(err);
        }
        httpClient.post(cntxt, IPropertyApplianceStatusUpdateResources.UPDATE_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LocalEventBus.getInstance().sendEvent(IPropertyApplianceStatusUpdateEvents.SUCCESS_EVENT);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<Boolean>>(){}.getType();
                ApiResponse<Boolean> details = gson.fromJson(response.toString(), responseType);
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                errs.add(details.getMessage());
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }
}
