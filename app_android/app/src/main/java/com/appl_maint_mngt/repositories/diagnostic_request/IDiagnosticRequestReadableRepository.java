package com.appl_maint_mngt.repositories.diagnostic_request;

import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestReadableRepository {

    IDiagnosticRequestReadable getForId(Long id);

    List<IDiagnosticRequestReadable> getAll();

    List<IDiagnosticRequestReadable> getAllPending();
}
