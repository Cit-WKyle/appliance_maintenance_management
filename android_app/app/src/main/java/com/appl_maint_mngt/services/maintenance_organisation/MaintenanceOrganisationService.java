package com.appl_maint_mngt.services.maintenance_organisation;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Kyle on 18/03/2017.
 */

public class MaintenanceOrganisationService implements IMaintenanceOrganisationService {

    private AsyncHttpClient httpClient;

    public MaintenanceOrganisationService() {
        httpClient = new AsyncHttpClient();
    }

    @Override
    public void getAll(IErrorCallback callback) {

    }
}
