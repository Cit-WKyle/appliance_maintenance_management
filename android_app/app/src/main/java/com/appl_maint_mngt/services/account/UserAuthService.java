package com.appl_maint_mngt.services.account;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.forms.account.LoginForm;
import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.web.constants.account.IAccountWebConstants;
import com.appl_maint_mngt.web.constants.account.IUserAuthResources;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.appl_maint_mngt.web.models.account.AuthDetails;
import com.appl_maint_mngt.web.models.account.JwtToken;
import com.appl_maint_mngt.web.models.common.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
 * Created by Kyle on 15/03/2017.
 */
public class UserAuthService implements IUserAuthService {

    private IAccountUpdateableRepository repository;
    private AsyncHttpClient httpClient;
    private Context cntxt;

    public UserAuthService() {
        httpClient = new AsyncHttpClient();
        cntxt = MainActivity.getInstance();
        repository = RepositoryFactory.getInstance().getUpdateableAccountRepository();
    }

    @Override
    public void postLogin(LoginForm form, final IErrorCallback callback) {
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(form));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            ErrorPayload err = new ErrorPayload();
            List<String> errs = new ArrayList<>();
            errs.add(e.getMessage());
            err.setErrors(errs);
            callback.callback(err);
        }
        httpClient.post(cntxt, IUserAuthResources.LOGIN_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<AuthDetails>>(){}.getType();
                ApiResponse<JwtToken> details = gson.fromJson(response.toString(), responseType);
                repository.updateToken(details.getData());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<AuthDetails>>(){}.getType();
                ApiResponse<JwtToken> details = gson.fromJson(response.toString(), responseType);
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                errs.add(details.getMessage());
                err.setErrors(errs);
                callback.callback(err);
            }
        });
    }

    @Override
    public void getDetails(String token, final IErrorCallback errorCallback) {
        httpClient.addHeader(IAccountWebConstants.X_AUTH_HEADER, IAccountWebConstants.TOKEN_PREFIX + token);
        httpClient.get(IUserAuthResources.DETAILS_RESOURCE, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<AuthDetails>>(){}.getType();
                ApiResponse<AuthDetails> details = gson.fromJson(response.toString(), responseType);
                repository.updateAuth(details.getData());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<AuthDetails>>(){}.getType();
                ApiResponse<AuthDetails> details = gson.fromJson(response.toString(), responseType);
                ErrorPayload err = new ErrorPayload();
                List<String> errs = new ArrayList<>();
                errs.add(details.getMessage());
                err.setErrors(errs);
                errorCallback.callback(err);
            }
        });
    }
}
