package com.appl_maint_mngt.diagnostic_request.services;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_request.models.DiagnosticRequest;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestPayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestService implements IDiagnosticRequestService{
    @Override
    public void post(IDiagnosticRequestPayload payload, IErrorCallback errorCallback) {
    }

    @Override
    public void findByDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback) {
    }

    @Override
    public void findByDiagnosticReportIds(List<Long> diagRepIds, IErrorCallback errorCallback) {
    }

    @Override
    public void findByMaintenanceOrganisationId(Long maintOrgId, IErrorCallback errorCallback) {
    }

    @Override
    public void put(IDiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback) {
    }
}
