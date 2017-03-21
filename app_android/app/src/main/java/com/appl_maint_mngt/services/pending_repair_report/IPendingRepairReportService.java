package com.appl_maint_mngt.services.pending_repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportService {

    void get(Long diagRepId, Long orgId, IErrorCallback errorCallback);
}
