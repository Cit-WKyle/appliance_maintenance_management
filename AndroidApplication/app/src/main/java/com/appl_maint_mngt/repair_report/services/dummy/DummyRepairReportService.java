package com.appl_maint_mngt.repair_report.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.repair_report.models.RepairReport;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportUpdateableRepository;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public class DummyRepairReportService implements IRepairReportService {
    @Override
    public void findByDiagnosticReportId(Long id, IErrorCallback errorCallback) {
//        IRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
//        RepairReport repairReport = new RepairReport();
//        repairReport.setId((long) 1);
//        repairReport.setCost(new BigDecimal(500));
//        repairReport.setDescription("Description");
//        repairReport.setDiagnosticReportId(id);
//        repairReport.setEngineerId((long) 1);
//        repairReport.setEstimatedDurationHours(5);
//        repairReport.setOnSite(true);
//        repairReport.setTitle("Title");
//        repository.addItem(repairReport);
        findByDiagnosticReportIdsIn(null, errorCallback);
    }

    @Override
    public void findByDiagnosticReportIdsIn(List<Long> ids, IErrorCallback errorCallback) {
        IRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
        List<RepairReport> list = new ArrayList<>();

        RepairReport repairReport1 = new RepairReport();
        repairReport1.setId((long) 5);
        repairReport1.setCost(new BigDecimal(500));
        repairReport1.setDescription("Description");
        repairReport1.setDiagnosticReportId((long) 4);
        repairReport1.setEngineerId((long) 1);
        repairReport1.setEstimatedDurationHours(5);
        repairReport1.setOnSite(true);
        repairReport1.setTitle("Title1");
        list.add(repairReport1);

        RepairReport repairReport2 = new RepairReport();
        repairReport2.setId((long) 6);
        repairReport2.setCost(new BigDecimal(500));
        repairReport2.setDescription("Description");
        repairReport2.setDiagnosticReportId((long) 5);
        repairReport2.setEngineerId((long) 1);
        repairReport2.setEstimatedDurationHours(5);
        repairReport2.setOnSite(true);
        repairReport2.setTitle("Title2");
        list.add(repairReport2);

        RepairReport repairReport3 = new RepairReport();
        repairReport3.setId((long) 7);
        repairReport3.setCost(new BigDecimal(500));
        repairReport3.setDescription("Description");
        repairReport3.setDiagnosticReportId((long) 6);
        repairReport3.setEngineerId((long) 1);
        repairReport3.setEstimatedDurationHours(5);
        repairReport3.setOnSite(true);
        repairReport3.setTitle("Title3");
        list.add(repairReport3);

        RepairReport repairReport4 = new RepairReport();
        repairReport4.setId((long) 1);
        repairReport4.setCost(new BigDecimal(500));
        repairReport4.setDescription("Description");
        repairReport4.setDiagnosticReportId((long) 1);
        repairReport4.setEngineerId((long) 1);
        repairReport4.setEstimatedDurationHours(5);
        repairReport4.setOnSite(true);
        repairReport4.setTitle("Title4");
        list.add(repairReport1);

        repository.addItems(list);
    }

    @Override
    public void findByEngineerId(Long engineerId, IErrorCallback errorCallback) {
        IRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
        RepairReport repairReport = new RepairReport();
        repairReport.setId((long) 1);
        repairReport.setCost(new BigDecimal(500));
        repairReport.setDescription("Description");
        repairReport.setDiagnosticReportId((long) 1);
        repairReport.setEngineerId(engineerId);
        repairReport.setEstimatedDurationHours(5);
        repairReport.setOnSite(true);
        repairReport.setTitle("Title");
        repository.addItem(repairReport);
    }
}
