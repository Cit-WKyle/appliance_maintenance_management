package com.appl_maint_mngt.services.diagnostic_request;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.web.models.diagnostic_request.DiagnosticRequestPayload;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestService {

    void post(DiagnosticRequestPayload payload, IErrorCallback errorCallback);

    void findByDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback);
}
