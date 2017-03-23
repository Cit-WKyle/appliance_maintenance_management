package com.appl_maint_mngt.services.maintenance_schedule;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.maintenance_schedule.MaintenanceSchedule;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.maintenance_schedule.IMaintenanceScheduleUpdateableRepository;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.constants.maintenance_schedule.IMaintenanceScheduleResources;
import com.appl_maint_mngt.web.constants.maintenance_schedule.IMaintenanceScheduleWebConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 23/03/2017.
 */

public class MaintenanceScheduleService implements IMaintenanceScheduleService {
    private static final Logger logger = LoggerManager.getLogger(MaintenanceScheduleService.class);

    private AsyncHttpClient httpClient;
    private Context context;
    private IMaintenanceScheduleUpdateableRepository repository;

    public MaintenanceScheduleService() {
        context = MainActivity.getInstance();
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableMaintenanceScheduleRepository();
    }

    @Override
    public void findByRepairReportId(Long repairReportId, final IErrorCallback errorCallback) {
        RequestParams params = new RequestParams();
        params.put(IMaintenanceScheduleWebConstants.REPAIR_REPORT_ID_PARAM, repairReportId);
        httpClient.get(context, IMaintenanceScheduleResources.FIND_BY_REPAIR_REPORT_ID_RESOURCE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().setDateFormat(IWebConstants.DATE_FORMAT).create();
                Type responseType = new TypeToken<MaintenanceSchedule>(){}.getType();

                MaintenanceSchedule mainSched = gson.fromJson(response.toString(), responseType);
                repository.addItem(mainSched);
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
