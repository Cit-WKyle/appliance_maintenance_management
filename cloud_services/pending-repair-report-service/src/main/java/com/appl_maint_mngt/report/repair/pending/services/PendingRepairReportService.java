package com.appl_maint_mngt.report.repair.pending.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.report.repair.pending.clients.http.IRepairReportClient;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;
import com.appl_maint_mngt.report.repair.pending.models.constants.ResponseStatus;
import com.appl_maint_mngt.report.repair.pending.repositories.IPendingRepairReportRepository;

@Service
public class PendingRepairReportService implements IPendingRepairReportService {
	
	@Autowired
	private IPendingRepairReportRepository repo;

	@Autowired
	private IRepairReportClient repClient;

	@Override
	public PendingRepairReport getforId(Long id) {
		return repo.findOne(id);
	}

	@Override
	public boolean doesPendingReportExist(Long id) {
		return repo.findOne(id) != null;
	}

	@Override
	public boolean acceptPendingReport(Long id) {
		PendingRepairReport rep = repo.findOne(id);
		
		if(repClient.create(rep) == null) return false; 
		
		List<PendingRepairReport> list = repo.findByDiagnosticReportId(rep.getDiagnosticReportId());
		for(PendingRepairReport item : list) {
			item.setResponseStatus(ResponseStatus.DECLINED);
		}
		repo.save(list);
		rep.setResponseStatus(ResponseStatus.ACCEPTED);
		repo.save(rep);
		return true;
	}

	@Override
	public boolean declinePendingReport(Long id) {
		PendingRepairReport rep = repo.findOne(id);
		rep.setResponseStatus(ResponseStatus.DECLINED);
		repo.save(rep);
		return true;
	}

	@Override
	public PendingRepairReport createPendingRepairReport(PendingRepairReport report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendingRepairReport> getPendingForDiagnosticReportId(Long diagRepId) {
		List<PendingRepairReport> unfilteredList = repo.findByDiagnosticReportId(diagRepId);
		List<PendingRepairReport> filteredList = new ArrayList<>();
		for(PendingRepairReport rep: unfilteredList) {
			if(rep.getResponseStatus().equals(ResponseStatus.PENDING)) filteredList.add(rep);
		}
		return filteredList;
	}

	@Override
	public List<PendingRepairReport> getPendingForOrganisationId(Long orgId) {
		List<PendingRepairReport> unfilteredList = repo.findByOrganisationId(orgId);
		List<PendingRepairReport> filteredList = new ArrayList<>();
		for(PendingRepairReport rep: unfilteredList) {
			if(rep.getResponseStatus().equals(ResponseStatus.PENDING)) filteredList.add(rep);
		}
		return filteredList;
		
	}

	@Override
	public boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId) {
		List<PendingRepairReport> list = repo.findByDiagnosticReportId(diagRepId);
		for(PendingRepairReport rep: list) {
			if(rep.getResponseStatus().equals(ResponseStatus.ACCEPTED)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPendingReportAcceptedOrPendingForOrgAndDiagRep(Long orgId, Long diagRepId) {
		List<PendingRepairReport> list = repo.findByOrganisationId(orgId);
		for(PendingRepairReport rep: list) {
			boolean correctDiagRep = rep.getDiagnosticReportId().equals(diagRepId);
			boolean invalidStatus = rep.getResponseStatus().equals(ResponseStatus.ACCEPTED)|| rep.getResponseStatus().equals(ResponseStatus.PENDING);
			if(correctDiagRep && invalidStatus) {
				return true;
			}
		}
		
		return false;
	}
}