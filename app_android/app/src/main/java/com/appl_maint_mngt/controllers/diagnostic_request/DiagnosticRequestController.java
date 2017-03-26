package com.appl_maint_mngt.controllers.diagnostic_request;

import android.util.LongSparseArray;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.services.common.ServiceFactory;
import com.appl_maint_mngt.services.diagnostic_request.IDiagnosticRequestService;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestPayload;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestUpdatePayload;

/**
 * Created by Kyle on 21/03/2017.
 */
public class DiagnosticRequestController implements IDiagnosticRequestController {

    private IDiagnosticRequestService service;

    public DiagnosticRequestController() {
        service = ServiceFactory.getInstance().getDiagnosticRequestService();
    }

    @Override
    public void createRequests(Long diagReqId, LongSparseArray<IMaintenanceOrganisationReadable> maintOrgs, IErrorCallback errorCallback) {
        for(int i=0; i< maintOrgs.size(); i++) {
            DiagnosticRequestPayload payload = new DiagnosticRequestPayload();
            payload.setDiagnosticReportId(diagReqId);
            payload.setMaintenanceOrganisationId(maintOrgs.keyAt(i));
            service.post(payload, errorCallback);
        }
    }

    @Override
    public void getForDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback) {
        service.findByDiagnosticReportId(diagRepId, errorCallback);
    }

    @Override
    public void getForMaintenanceOrgId(Long maintOrgId, IErrorCallback errorCallback) {
        service.findByMaintenanceOrganisationId(maintOrgId, errorCallback);
    }

    @Override
    public void updateDiagnosticRequest(DiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback) {
        service.put(payload, errorCallback);
    }
}
