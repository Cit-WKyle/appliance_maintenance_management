package com.appl_maint_mngt.repositories.pending_maintenance_scheduling;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.pending_maintenance_scheduling.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.models.pending_maintenance_scheduling.PendingMaintenanceSchedule;
import com.appl_maint_mngt.models.pending_maintenance_scheduling.SchedulerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public class PendingMaintenanceSchedulingRepository extends APendingMaintenanceSchedulingRepository {

    private LongSparseArray<PendingMaintenanceSchedule> pendingMaintenanceSchedules;

    public PendingMaintenanceSchedulingRepository() {
        this.pendingMaintenanceSchedules = new LongSparseArray<>();
    }

    @Override
    public void addItems(List<PendingMaintenanceSchedule> pendingMaintenanceScheduleList) {
        for(PendingMaintenanceSchedule sched : pendingMaintenanceScheduleList) {
            this.pendingMaintenanceSchedules.put(sched.getId(), sched);
        }
        updateObservers(IPendingMaintenanceSchedulingObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public List<IPendingMaintenanceScheduleReadable> getForEngineerAndReportId(Long reportId) {
        List<IPendingMaintenanceScheduleReadable> scheds = new ArrayList<>();
        for(int i=0; i< pendingMaintenanceSchedules.size(); i++) {
            if(pendingMaintenanceSchedules.valueAt(i).getRepairReportId().equals(reportId) && pendingMaintenanceSchedules.valueAt(i).getSchedulerType().equals(SchedulerType.ENGINEER_REPRESENTITIVE)) {
                scheds.add(pendingMaintenanceSchedules.valueAt(i));
            }
        }
        return scheds;
    }

    @Override
    public List<IPendingMaintenanceScheduleReadable> getForManagerAndReportId(Long reportId) {
        List<IPendingMaintenanceScheduleReadable> scheds = new ArrayList<>();
        for(int i=0; i< pendingMaintenanceSchedules.size(); i++) {
            if(pendingMaintenanceSchedules.valueAt(i).getRepairReportId().equals(reportId) && pendingMaintenanceSchedules.valueAt(i).getSchedulerType().equals(SchedulerType.PROPERTY_REPRESENTITIVE)) {
                scheds.add(pendingMaintenanceSchedules.valueAt(i));
            }
        }
        return scheds;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
