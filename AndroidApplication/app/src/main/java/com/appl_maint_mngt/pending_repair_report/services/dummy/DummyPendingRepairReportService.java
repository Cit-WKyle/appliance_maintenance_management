package com.appl_maint_mngt.pending_repair_report.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.pending_repair_report.events.constants.IPendingRepairReportEvents;
import com.appl_maint_mngt.pending_repair_report.models.PendingRepairReport;
import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.appl_maint_mngt.pending_repair_report.models.web.interfaces.IPendingRepairReportPayload;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public class DummyPendingRepairReportService implements IPendingRepairReportService {
    @Override
    public void accept(Long id, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId(id);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId((long) 1);
        pendingRepairReport.setResponseStatus(ResponseStatus.ACCEPTED);

        ApplicationEventBus.getInstance().sendEvent(IPendingRepairReportEvents.ACCEPT_EVENT);
        repository.addItem(pendingRepairReport);
    }

    @Override
    public void decline(Long id, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId(id);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId((long) 1);
        pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);

        ApplicationEventBus.getInstance().sendEvent(IPendingRepairReportEvents.DECLINE_EVENT);
        repository.addItem(pendingRepairReport);
    }

    @Override
    public void findByDiagnosticRequestId(Long diagnosticRequestId, IErrorCallback errorCallback) {
//        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();
//
//        PendingRepairReport pendingRepairReport = new PendingRepairReport();
//        pendingRepairReport.setTitle("Title");
//        pendingRepairReport.setOnSite(true);
//        pendingRepairReport.setEstimatedDurationHours(5);
//        pendingRepairReport.setId(diagnosticRequestId);
//        pendingRepairReport.setCost(new BigDecimal(500));
//        pendingRepairReport.setDescription("Description");
//        pendingRepairReport.setDiagnosticRequestId((long) 1);
//        pendingRepairReport.setEngineerId((long) 1);
//        pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);
//
//        repository.addItem(pendingRepairReport);
        findByDiagnosticRequestIdIn(null, errorCallback);
    }

    @Override
    public void findByDiagnosticRequestIdIn(List<Long> ids, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        List<PendingRepairReport> list = new ArrayList<>();

        PendingRepairReport pendingRepairReport1 = new PendingRepairReport();
        pendingRepairReport1.setTitle("Title1");
        pendingRepairReport1.setOnSite(true);
        pendingRepairReport1.setEstimatedDurationHours(5);
        pendingRepairReport1.setId((long) 4);
        pendingRepairReport1.setCost(new BigDecimal(500));
        pendingRepairReport1.setDescription("Description");
        pendingRepairReport1.setDiagnosticRequestId((long) 4);
        pendingRepairReport1.setEngineerId((long) 1);
        pendingRepairReport1.setResponseStatus(ResponseStatus.PENDING);
        list.add(pendingRepairReport1);

        PendingRepairReport pendingRepairReport2 = new PendingRepairReport();
        pendingRepairReport2.setTitle("Title2");
        pendingRepairReport2.setOnSite(true);
        pendingRepairReport2.setEstimatedDurationHours(5);
        pendingRepairReport2.setId((long) 5);
        pendingRepairReport2.setCost(new BigDecimal(500));
        pendingRepairReport2.setDescription("Description");
        pendingRepairReport2.setDiagnosticRequestId((long) 5);
        pendingRepairReport2.setEngineerId((long) 1);
        pendingRepairReport2.setResponseStatus(ResponseStatus.ACCEPTED);
        list.add(pendingRepairReport2);

        PendingRepairReport pendingRepairReport3 = new PendingRepairReport();
        pendingRepairReport3.setTitle("Title2");
        pendingRepairReport3.setOnSite(true);
        pendingRepairReport3.setEstimatedDurationHours(5);
        pendingRepairReport3.setId((long) 6);
        pendingRepairReport3.setCost(new BigDecimal(500));
        pendingRepairReport3.setDescription("Description");
        pendingRepairReport3.setDiagnosticRequestId((long) 6);
        pendingRepairReport3.setEngineerId((long) 1);
        pendingRepairReport3.setResponseStatus(ResponseStatus.ACCEPTED);
        list.add(pendingRepairReport3);

        PendingRepairReport pendingRepairReport4 = new PendingRepairReport();
        pendingRepairReport4.setTitle("Title2");
        pendingRepairReport4.setOnSite(true);
        pendingRepairReport4.setEstimatedDurationHours(5);
        pendingRepairReport4.setId((long) 7);
        pendingRepairReport4.setCost(new BigDecimal(500));
        pendingRepairReport4.setDescription("Description");
        pendingRepairReport4.setDiagnosticRequestId((long) 7);
        pendingRepairReport4.setEngineerId((long) 1);
        pendingRepairReport4.setResponseStatus(ResponseStatus.ACCEPTED);
        list.add(pendingRepairReport4);

        repository.addItems(list);
    }

    @Override
    public void create(IPendingRepairReportPayload payload, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setEngineerId(payload.getEngineerId());
        pendingRepairReport.setDiagnosticRequestId(payload.getDiagnosticRequestId());
        pendingRepairReport.setId((long) 1);
        pendingRepairReport.setOnSite(payload.isOnSite());
        pendingRepairReport.setCost(payload.getCost());
        pendingRepairReport.setDescription(payload.getDescription());
        pendingRepairReport.setEstimatedDurationHours(payload.getEstimatedDurationHours());
        pendingRepairReport.setTitle(payload.getTitle());
        pendingRepairReport.setResponseStatus(ResponseStatus.PENDING);

        repository.addItem(pendingRepairReport);
    }

    @Override
    public void findByEngineerId(Long engineerId, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId((long) 1);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId(engineerId);
        pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);

        repository.addItem(pendingRepairReport);
    }
}
