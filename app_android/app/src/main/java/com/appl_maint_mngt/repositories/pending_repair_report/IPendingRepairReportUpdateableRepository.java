package com.appl_maint_mngt.repositories.pending_repair_report;

import com.appl_maint_mngt.models.pending_repair_report.PendingRepairReport;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportUpdateableRepository {

    void addItem(PendingRepairReport pendingReport);
}
