package com.appl_maint_mngt.services.account;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.web.constants.account.IUserProfileResources;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.models.account.UserProfile;
import com.appl_maint_mngt.web.models.common.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 15/03/2017.
 */
public class UserProfileService implements IUserProfileService {

    private IAccountUpdateableRepository repository;
    private AsyncHttpClient httpClient;

    public UserProfileService() {
        httpClient = new AsyncHttpClient();
        repository = RepositoryFactory.getInstance().getUpdateableAccountRepository();
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        httpClient.get(IUserProfileResources.PROFILE_GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<UserProfile>>(){}.getType();
                ApiResponse<UserProfile> profile = gson.fromJson(response.toString(), responseType);
                repository.updateProfile(profile.getData());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<UserProfile>>(){}.getType();
                ApiResponse<UserProfile> profile = gson.fromJson(response.toString(), responseType);
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                errs.add(profile.getMessage());
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }
}