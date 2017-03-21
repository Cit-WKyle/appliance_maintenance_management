package com.appl_maint_mngt.repositories.diagnostic_request;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.diagnostic_request.ADiagnosticRequest;
import com.appl_maint_mngt.models.diagnostic_request.DiagnosticRequest;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public class DiagnosticRequestRepository extends ADiagnosticRequestRepository {

    private LongSparseArray<ADiagnosticRequest> diagnosticRequests;

    public DiagnosticRequestRepository() {
        diagnosticRequests = new LongSparseArray<>();
    }

    @Override
    public void addItem(DiagnosticRequest request) {
        diagnosticRequests.append(request.getId(), request);
        updateObservers(IDiagnosticRequestObserverUpdateTypes.NEW_ITEM);
    }

    @Override
    public void addItems(List<DiagnosticRequest> reqs) {
        for(DiagnosticRequest req: reqs) {
            diagnosticRequests.put(req.getId(), req);
        }
        updateObservers(IDiagnosticRequestObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IDiagnosticRequestReadable getForId(Long id) {
        return diagnosticRequests.get(id);
    }

    @Override
    public List<IDiagnosticRequestReadable> getAll() {
        List<IDiagnosticRequestReadable> list = new ArrayList<>();
        for(int i = 0; i<diagnosticRequests.size(); i++) {
            list.add(diagnosticRequests.valueAt(i));
        }
        return list;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
