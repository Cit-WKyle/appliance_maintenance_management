package com.appl_maint_mngt.repositories.pending_repair_report;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;
import com.appl_maint_mngt.models.pending_repair_report.PendingRepairReport;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationObserverUpdateTypes;

/**
 * Created by Kyle on 21/03/2017.
 */
public class PendingRepairReportRepository extends APendingRepairReportRepository {

    private LongSparseArray<PendingRepairReport> repairReports;

    public PendingRepairReportRepository() {
        repairReports = new LongSparseArray<>();
    }

    @Override
    public IPendingRepairReportReadable getForDiagAndOrgIds(Long diagReportId, Long maintOrgId) {
        for(int i = 0; i<repairReports.size(); i++) {
            PendingRepairReport rep = repairReports.valueAt(i);
            if(rep.getDiagnosticReportId().equals(diagReportId) && rep.getOrganisationId().equals(maintOrgId)) {
                return rep;
            }
        }
        return null;
    }

    @Override
    public void addItem(PendingRepairReport pendingReport) {
        repairReports.put(pendingReport.getId(), pendingReport);
        updateObservers(IMaintenanceOrganisationObserverUpdateTypes.MODEL_UPDATE);
    }


    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
