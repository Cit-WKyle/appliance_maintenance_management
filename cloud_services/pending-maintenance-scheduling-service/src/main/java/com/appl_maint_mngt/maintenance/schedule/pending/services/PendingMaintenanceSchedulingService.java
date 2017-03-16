package com.appl_maint_mngt.maintenance.schedule.pending.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;
import com.appl_maint_mngt.maintenance.schedule.pending.repositories.IPendingMaintenanceSchedulingRepository;

@Service
public class PendingMaintenanceSchedulingService implements IPendingMaintenanceSchedulingService {
	
	@Autowired
	private IPendingMaintenanceSchedulingRepository repo;

	@Override
	public void acceptPendingSchedule(Long id) {
		PendingMaintenanceSchedule sched = repo.findOne(id);
		List<PendingMaintenanceSchedule> scheds = repo.findByRepairReportId(sched.getRepairReportId());
		for(PendingMaintenanceSchedule s : scheds) {
			s.setScheduleStatus(ScheduleStatus.DECLINED);
		}
		repo.save(scheds);
		sched.setScheduleStatus(ScheduleStatus.ACCEPTED);
		repo.save(sched);
	}

	@Override
	public void declinePendingSchedule(Long id) {
		PendingMaintenanceSchedule sched = repo.findOne(id);
		sched.setScheduleStatus(ScheduleStatus.DECLINED);
	}

	@Override
	public boolean doesReportHaveAcceptedSchedule(Long reportId) {
		List<PendingMaintenanceSchedule> scheds = repo.findByRepairReportId(reportId);
		for(PendingMaintenanceSchedule sched : scheds) {
			if(sched.getScheduleStatus().equals(ScheduleStatus.ACCEPTED)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<PendingMaintenanceSchedule> getAllForReportAndSchedulerType(Long reportId, SchedulerType type) {
		List<PendingMaintenanceSchedule> scheds = repo.findByRepairReportId(reportId);
		List<PendingMaintenanceSchedule> filtered = new ArrayList<>();
		for(PendingMaintenanceSchedule sched : scheds) {
			if(sched.getSchedulerType().equals(type)) {
				filtered.add(sched);
			}
		}
		return filtered;
	}

	@Override
	public Long getRepairReportForId(Long id) {
		return repo.findOne(id).getRepairReportId();
	}

	@Override
	public boolean doesItemExist(Long id) {
		return repo.findOne(id) != null;
	}

	@Override
	public void save(PendingMaintenanceSchedule sched) {
		repo.save(sched);
	}

	@Override
	public PendingMaintenanceSchedule getForId(Long id) {
		return repo.findOne(id);
	}

}
