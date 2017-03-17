package com.appl_maint_mngt.services.account;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.web.constants.account.IUserProfileResources;
import com.appl_maint_mngt.web.constants.common.IWebConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 15/03/2017.
 */
public class UserProfileService implements IUserProfileService {

    private IAccountUpdateableRepository repository;
    private AsyncHttpClient httpClient;
    private Context cntxt;

    public UserProfileService() {
        httpClient = new AsyncHttpClient();
        cntxt = MainActivity.getInstance();
        repository = RepositoryFactory.getInstance().getUpdateableAccountRepository();
    }

    @Override
    public void get(Long id, IErrorCallback errorCallback) {
        httpClient.get(IUserProfileResources.PROFILE_GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {

            }
        });
    }
}
