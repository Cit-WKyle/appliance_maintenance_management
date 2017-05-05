package com.appl_maint_mngt.diagnostic_request.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_request.models.DiagnosticRequest;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestPayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DummyDiagnosticRequestService implements IDiagnosticRequestService {

    @Override
    public void post(IDiagnosticRequestPayload payload, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        DiagnosticRequest diagnosticRequest = new DiagnosticRequest();
        diagnosticRequest.setId((long) 1);
        diagnosticRequest.setDiagnosticReportId(payload.getDiagnosticReportId());
        diagnosticRequest.setMaintenanceOrganisationId(payload.getMaintenanceOrganisationId());
        diagnosticRequest.setResponseStatus(ResponseStatus.PENDING);

        repository.addItem(diagnosticRequest);
    }

    @Override
    public void findByDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback) {
        findByDiagnosticReportIds(null, errorCallback);

    }

    @Override
    public void findByDiagnosticReportIds(List<Long> diagRepIds, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        List<DiagnosticRequest> diagnosticRequestList = new ArrayList<>();

        DiagnosticRequest diagnosticRequest1 = new DiagnosticRequest();
        diagnosticRequest1.setResponseStatus(ResponseStatus.PENDING);
        diagnosticRequest1.setId((long) 1);
        diagnosticRequest1.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest1.setDiagnosticReportId((long) 1);
        diagnosticRequestList.add(diagnosticRequest1);

        DiagnosticRequest diagnosticRequest2 = new DiagnosticRequest();
        diagnosticRequest2.setResponseStatus(ResponseStatus.PENDING);
        diagnosticRequest2.setId((long) 2);
        diagnosticRequest2.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest2.setDiagnosticReportId((long) 2);
        diagnosticRequestList.add(diagnosticRequest2);

        DiagnosticRequest diagnosticRequest3 = new DiagnosticRequest();
        diagnosticRequest3.setResponseStatus(ResponseStatus.PENDING);
        diagnosticRequest3.setId((long) 3);
        diagnosticRequest3.setMaintenanceOrganisationId((long) 2);
        diagnosticRequest3.setDiagnosticReportId((long) 2);
        diagnosticRequestList.add(diagnosticRequest3);

        DiagnosticRequest diagnosticRequest4 = new DiagnosticRequest();
        diagnosticRequest4.setResponseStatus(ResponseStatus.RESPONDED);
        diagnosticRequest4.setId((long) 4);
        diagnosticRequest4.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest4.setDiagnosticReportId((long) 3);
        diagnosticRequestList.add(diagnosticRequest4);

        DiagnosticRequest diagnosticRequest5 = new DiagnosticRequest();
        diagnosticRequest5.setResponseStatus(ResponseStatus.RESPONDED);
        diagnosticRequest5.setId((long) 5);
        diagnosticRequest5.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest5.setDiagnosticReportId((long) 4);
        diagnosticRequestList.add(diagnosticRequest5);

        DiagnosticRequest diagnosticRequest6 = new DiagnosticRequest();
        diagnosticRequest6.setResponseStatus(ResponseStatus.RESPONDED);
        diagnosticRequest6.setId((long) 6);
        diagnosticRequest6.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest6.setDiagnosticReportId((long) 5);
        diagnosticRequestList.add(diagnosticRequest6);

        DiagnosticRequest diagnosticRequest7 = new DiagnosticRequest();
        diagnosticRequest7.setResponseStatus(ResponseStatus.RESPONDED);
        diagnosticRequest7.setId((long) 7);
        diagnosticRequest7.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest7.setDiagnosticReportId((long) 6);
        diagnosticRequestList.add(diagnosticRequest7);

        repository.addItems(diagnosticRequestList);
    }

    @Override
    public void findByMaintenanceOrganisationId(Long maintOrgId, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        List<DiagnosticRequest> diagnosticRequestList = new ArrayList<>();

        DiagnosticRequest diagnosticRequest1 = new DiagnosticRequest();
        diagnosticRequest1.setResponseStatus(ResponseStatus.PENDING);
        diagnosticRequest1.setId((long) 1);
        diagnosticRequest1.setMaintenanceOrganisationId(maintOrgId);
        diagnosticRequest1.setDiagnosticReportId((long) 1);
        diagnosticRequestList.add(diagnosticRequest1);

        repository.addItems(diagnosticRequestList);
    }

    @Override
    public void put(IDiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        DiagnosticRequest diagnosticRequest = new DiagnosticRequest();
        diagnosticRequest.setId(payload.getId());
        diagnosticRequest.setDiagnosticReportId(payload.getDiagnosticReportId());
        diagnosticRequest.setMaintenanceOrganisationId(payload.getMaintenanceOrganisationId());
        diagnosticRequest.setResponseStatus(payload.getResponseStatus());

        repository.addItem(diagnosticRequest);
    }
}
