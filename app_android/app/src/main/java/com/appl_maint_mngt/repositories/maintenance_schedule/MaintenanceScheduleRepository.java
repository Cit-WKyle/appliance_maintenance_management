package com.appl_maint_mngt.repositories.maintenance_schedule;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.maintenance_schedule.AMaintenanceSchedule;
import com.appl_maint_mngt.models.maintenance_schedule.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.models.maintenance_schedule.MaintenanceSchedule;

/**
 * Created by Kyle on 23/03/2017.
 */

public class MaintenanceScheduleRepository extends AMaintenanceScheduleRepository {

    private LongSparseArray<AMaintenanceSchedule> maintenanceSchedules;

    public MaintenanceScheduleRepository() {
        maintenanceSchedules = new LongSparseArray<>();
    }

    @Override
    public void addItem(MaintenanceSchedule sched) {
        maintenanceSchedules.put(sched.getId(), sched);
    }

    @Override
    public IMaintenanceScheduleReadable getForReportId(Long id) {
        for(int i=0; i< maintenanceSchedules.size(); i++) {
            if(maintenanceSchedules.valueAt(i).getRepairReportId().equals(id)) return maintenanceSchedules.valueAt(i);
        }
        return null;
    }
}
