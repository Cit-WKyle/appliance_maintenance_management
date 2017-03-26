package com.appl_maint_mngt.repositories.pending_repair_report;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;
import com.appl_maint_mngt.models.pending_repair_report.PendingRepairReport;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

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
    public List<IPendingRepairReportReadable> getAll() {
        List<IPendingRepairReportReadable> list = new ArrayList<>();
        for(int i = 0; i<repairReports.size(); i++) {
            list.add(repairReports.valueAt(i));
        }
        return list;
    }

    @Override
    public IPendingRepairReportReadable getForId(Long id) {
        return repairReports.get(id);
    }

    @Override
    public void addItem(PendingRepairReport pendingReport) {
        repairReports.put(pendingReport.getId(), pendingReport);
        updateObservers(IPendingRepairReportRepositoryObserverUpdateTypes.ADD_ITEM);
    }

    @Override
    public void addItems(List<PendingRepairReport> pendingReports) {
        for(PendingRepairReport report: pendingReports) {
            repairReports.put(report.getId(), report);
        }
        updateObservers(IPendingRepairReportRepositoryObserverUpdateTypes.MODEL_UPDATE);
    }


    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
