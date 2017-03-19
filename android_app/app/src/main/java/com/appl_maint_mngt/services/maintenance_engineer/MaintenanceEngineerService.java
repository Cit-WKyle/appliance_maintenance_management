package com.appl_maint_mngt.services.maintenance_engineer;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.maintenance_engineer.MaintenanceEngineer;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.maintenance_engineer.IMaintenanceEngineerResources;
import com.appl_maint_mngt.web.models.maintenance_engineer.MaintenanceEngineerPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

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
 * Created by Kyle on 19/03/2017.
 */

public class MaintenanceEngineerService implements IMaintenanceEngineerService {

    private AsyncHttpClient httpClient;
    private IMaintenanceEngineerUpdateableRepository repository;
    private Context cntxt;

    public MaintenanceEngineerService() {
        httpClient = new AsyncHttpClient();
        cntxt = MainActivity.getInstance();
        repository = RepositoryFactory.getInstance().getUpdateableMaintenanceEngineerRepository();
    }

    @Override
    public void post(MaintenanceEngineerPayload payload, final IErrorCallback callback) {
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            callback.callback(err);
        }
        httpClient.post(cntxt, IMaintenanceEngineerResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<MaintenanceEngineer>(){}.getType();
                MaintenanceEngineer mainEng = gson.fromJson(response.toString(), responseType);
                repository.update(mainEng);
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
