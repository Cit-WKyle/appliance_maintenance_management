package com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces;

import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 23/03/2017.
 */

public interface IPendingMaintenanceSchedulingReadableRepository {

    List<IPendingMaintenanceScheduleReadable> getForEngineerAndReportId(Long reportId);
    List<IPendingMaintenanceScheduleReadable> getForManagerAndReportId(Long reportId);
}
