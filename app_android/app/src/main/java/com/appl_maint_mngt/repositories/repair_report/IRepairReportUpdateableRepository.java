package com.appl_maint_mngt.repositories.repair_report;

import com.appl_maint_mngt.models.repair_report.RepairReport;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportUpdateableRepository {

    void addItems(List<RepairReport> repairReportsList);

    void addItem(RepairReport repairReport);
}
