package com.appl_maint_mngt.controllers.diagnostic_request;

import android.util.LongSparseArray;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestUpdatePayload;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestController {

    void createRequests(Long diagReqId, LongSparseArray<IMaintenanceOrganisationReadable> maintOrgs, IErrorCallback errorCallback);

    void getForDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback);

    void getForMaintenanceOrgId(Long maintOrgId, IErrorCallback errorCallback);

    void updateDiagnosticRequest(DiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback);
}
