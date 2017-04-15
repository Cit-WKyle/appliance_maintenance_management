package com.appl_maint_mngt.diagnostic_request.utility;

import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.interfaces.IDiagnosticRequestsRetriever;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestsRetriever implements IDiagnosticRequestsRetriever {

    @Override
    public List<IDiagnosticRequestReadable> getPendingAndResponded() {
        List<IDiagnosticRequestReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getAll();

        List<IDiagnosticRequestReadable> diagReqs = new DiagnosticRequestListFilter().filterStatus(list, ResponseStatus.PENDING);
        diagReqs.addAll(new DiagnosticRequestListFilter().filterStatus(list, ResponseStatus.RESPONDED));

        return diagReqs;
    }

    @Override
    public List<IDiagnosticRequestReadable> getPending() {
        List<IDiagnosticRequestReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getAll();
        List<IDiagnosticRequestReadable> diagReqs = new DiagnosticRequestListFilter().filterStatus(list, ResponseStatus.PENDING);

        return diagReqs;
    }
}
