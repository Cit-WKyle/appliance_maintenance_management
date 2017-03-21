package com.appl_maint_mngt.services.pending_repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Kyle on 21/03/2017.
 */

public class PendingRepairReportService implements IPendingRepairReportService {

    private AsyncHttpClient httpClient;

    @Override
    public void get(Long diagRepId, Long orgId, IErrorCallback errorCallback) {

    }
}
