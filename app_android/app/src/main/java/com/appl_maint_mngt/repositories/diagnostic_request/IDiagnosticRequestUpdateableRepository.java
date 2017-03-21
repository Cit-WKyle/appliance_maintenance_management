package com.appl_maint_mngt.repositories.diagnostic_request;

import com.appl_maint_mngt.models.diagnostic_report.DiagnosticReport;
import com.appl_maint_mngt.models.diagnostic_request.DiagnosticRequest;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestUpdateableRepository {

    void addItem(DiagnosticRequest request);

    void addItems(List<DiagnosticRequest> reqs);
}
