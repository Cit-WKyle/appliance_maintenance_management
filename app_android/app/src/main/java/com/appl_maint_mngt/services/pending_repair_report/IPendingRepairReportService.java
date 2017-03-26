package com.appl_maint_mngt.services.pending_repair_report;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.pending_repair_report.PendingRepairReportPayload;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportService {

    void get(Long diagRepId, Long orgId, IErrorCallback errorCallback);

    void accept(Long id, IErrorCallback errorCallback);

    void decline(Long id, IErrorCallback errorCallback);

    void create(PendingRepairReportPayload payload, IErrorCallback errorCallback);

    void findByEngineerId(Long engineerId, IErrorCallback errorCallback);
}
