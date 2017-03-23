package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.AAccountRepository;
import com.appl_maint_mngt.repositories.account.AccountRepository;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.appliance.AApplianceRepository;
import com.appl_maint_mngt.repositories.appliance.ApplianceRepository;
import com.appl_maint_mngt.repositories.appliance.IApplianceReadableRepository;
import com.appl_maint_mngt.repositories.appliance.IApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.appliance_status.AApplianceStatusRepository;
import com.appl_maint_mngt.repositories.appliance_status.ApplianceStatusRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.repositories.diagnostic_report.ADiagnosticReportRepository;
import com.appl_maint_mngt.repositories.diagnostic_report.DiagnosticReportRepository;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportReadableRepository;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.repositories.diagnostic_request.ADiagnosticRequestRepository;
import com.appl_maint_mngt.repositories.diagnostic_request.DiagnosticRequestRepository;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestReadableRepository;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.repositories.maintenance_engineer.AMaintenanceEngineerRepository;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerReadableRepository;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerUpdateableRepository;
import com.appl_maint_mngt.repositories.maintenance_engineer.MaintenanceEngineerRepository;
import com.appl_maint_mngt.repositories.maintenance_organisation.AMaintenanceOrganisationRepository;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationReadableRepository;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationUpdateableRepository;
import com.appl_maint_mngt.repositories.maintenance_organisation.MaintenanceOrganisationRepository;
import com.appl_maint_mngt.repositories.maintenance_schedule.AMaintenanceScheduleRepository;
import com.appl_maint_mngt.repositories.maintenance_schedule.IMaintenanceScheduleReadableRepository;
import com.appl_maint_mngt.repositories.maintenance_schedule.IMaintenanceScheduleUpdateableRepository;
import com.appl_maint_mngt.repositories.maintenance_schedule.MaintenanceScheduleRepository;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.APendingMaintenanceSchedulingRepository;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.IPendingMaintenanceSchedulingReadableRepository;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.IPendingMaintenanceSchedulingUpdateableRepository;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.PendingMaintenanceSchedulingRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.APendingRepairReportRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportReadableRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.PendingRepairReportRepository;
import com.appl_maint_mngt.repositories.property.APropertyRepository;
import com.appl_maint_mngt.repositories.property.IPropertyReadableRepository;
import com.appl_maint_mngt.repositories.property.IPropertyUpdateableRepository;
import com.appl_maint_mngt.repositories.property.PropertyRepository;
import com.appl_maint_mngt.repositories.property_appliance.APropertyApplianceRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.property_appliance.PropertyApplianceRepository;
import com.appl_maint_mngt.repositories.property_manager.APropertyManagerRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerReadableRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerUpdateableRepository;
import com.appl_maint_mngt.repositories.property_manager.PropertyManagerRepository;
import com.appl_maint_mngt.repositories.property_tenant.APropertyTenantRepository;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantReadableRepository;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantUpdateableRepository;
import com.appl_maint_mngt.repositories.property_tenant.PropertyTenantRepository;
import com.appl_maint_mngt.repositories.repair_report.ARepairReportRepository;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportReadableRepository;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportUpdateableRepository;
import com.appl_maint_mngt.repositories.repair_report.RepairReportRepository;

import java.util.Observer;

/**
 * Created by Kyle on 15/03/2017.
 */
public class RepositoryFactory implements IReadableRepositoryFactory, IUpdateableRepositoryFactory, IObserveRepositoriesHandler {
    private static RepositoryFactory ourInstance = new RepositoryFactory();

    private AAccountRepository accountRepository;
    private APropertyRepository propertyRepository;
    private APropertyManagerRepository propertyManagerRepository;
    private APropertyApplianceRepository propertyApplianceRepository;
    private AApplianceStatusRepository applianceStatusRepository;
    private AApplianceRepository applianceRepository;
    private ADiagnosticReportRepository diagnosticReportRepository;
    private AMaintenanceOrganisationRepository maintenanceOrganisationRepository;
    private APropertyTenantRepository propertyTenantRepository;
    private AMaintenanceEngineerRepository maintenanceEngineerRepository;
    private ADiagnosticRequestRepository diagnosticRequestRepository;
    private ARepairReportRepository repairReportRepository;
    private APendingRepairReportRepository pendingRepairReportRepository;
    private APendingMaintenanceSchedulingRepository pendingMaintenanceSchedulingRepository;
    private AMaintenanceScheduleRepository maintenanceScheduleRepository;

    public static RepositoryFactory getInstance() {
        return ourInstance;
    }

    private RepositoryFactory() {
    }

    @Override
    public IAccountReadableRepository getReadableAccountRepository() {
        if(accountRepository == null)accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public IPropertyManagerReadableRepository getReadablePropertyManagerRepository() {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        return propertyManagerRepository;
    }

    @Override
    public IPropertyReadableRepository getReadablePropertyRepository() {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        return propertyRepository;
    }

    @Override
    public IPropertyApplianceReadableRepository getReadablePropertyApplianceRepository() {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        return propertyApplianceRepository;
    }

    @Override
    public IApplianceStatusReadableRepository getReadableApplianceStatusRepository() {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        return applianceStatusRepository;
    }

    @Override
    public IApplianceReadableRepository getReadableApplianceRepository() {
        if(applianceRepository == null) applianceRepository = new ApplianceRepository();
        return applianceRepository;
    }

    @Override
    public IDiagnosticReportReadableRepository getReadableDiagnosticReportRepository() {
        if(diagnosticReportRepository == null) diagnosticReportRepository = new DiagnosticReportRepository();
        return diagnosticReportRepository;
    }

    @Override
    public IMaintenanceOrganisationReadableRepository getReadableMaintenanceOrganisationRepository() {
        if(maintenanceOrganisationRepository == null) maintenanceOrganisationRepository = new MaintenanceOrganisationRepository();
        return maintenanceOrganisationRepository;
    }

    @Override
    public IPropertyTenantReadableRepository getReadablePropertyTenantRepository() {
        if(propertyTenantRepository == null) propertyTenantRepository = new PropertyTenantRepository();
        return propertyTenantRepository;
    }

    @Override
    public IMaintenanceEngineerReadableRepository getReadableMaintenanceEngineerRepository() {
        if(maintenanceEngineerRepository == null) maintenanceEngineerRepository = new MaintenanceEngineerRepository();
        return maintenanceEngineerRepository;
    }

    @Override
    public IDiagnosticRequestReadableRepository getReadableDiagnosticRequestRepository() {
        if(diagnosticRequestRepository == null) diagnosticRequestRepository = new DiagnosticRequestRepository();
        return diagnosticRequestRepository;
    }

    @Override
    public IRepairReportReadableRepository getReadableRepairReportRepository() {
        if(repairReportRepository == null) repairReportRepository = new RepairReportRepository();
        return repairReportRepository;
    }

    @Override
    public IPendingRepairReportReadableRepository getReadablePendingRepairReportRepository() {
        if(pendingRepairReportRepository == null) pendingRepairReportRepository = new PendingRepairReportRepository();
        return pendingRepairReportRepository;
    }

    @Override
    public IPendingMaintenanceSchedulingReadableRepository getReadablePendingMaintenanceSchedulingRepository() {
        if(pendingMaintenanceSchedulingRepository == null) pendingMaintenanceSchedulingRepository = new PendingMaintenanceSchedulingRepository();
        return pendingMaintenanceSchedulingRepository;
    }

    @Override
    public IMaintenanceScheduleReadableRepository getReadableMaintenanceScheduleRepository() {
        if(maintenanceScheduleRepository == null) maintenanceScheduleRepository = new MaintenanceScheduleRepository();
        return maintenanceScheduleRepository;
    }

    @Override
    public IAccountUpdateableRepository getUpdateableAccountRepository() {
        if(accountRepository == null)accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public IPropertyManagerUpdateableRepository getUpdateablePropertyManagerRepository() {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        return propertyManagerRepository;
    }

    @Override
    public IPropertyUpdateableRepository getUpdateablePropertyRepository() {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        return propertyRepository;
    }

    @Override
    public IPropertyApplianceUpdateableRepository getUpdateablePropertyApplianceRepository() {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        return propertyApplianceRepository;
    }

    @Override
    public IApplianceStatusUpdateableRepository getUpdateableApplianceStatusRepository() {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        return applianceStatusRepository;
    }

    @Override
    public IApplianceUpdateableRepository getUpdateableApplianceRepository() {
        if(applianceRepository == null) applianceRepository = new ApplianceRepository();
        return applianceRepository;
    }

    @Override
    public IDiagnosticReportUpdateableRepository getUpdateableDiagnosticReportRepository() {
        if(diagnosticReportRepository == null) diagnosticReportRepository = new DiagnosticReportRepository();
        return diagnosticReportRepository;
    }

    @Override
    public IMaintenanceOrganisationUpdateableRepository getUpdateableMaintenanceOrganisationRepository() {
        if(maintenanceOrganisationRepository == null) maintenanceOrganisationRepository = new MaintenanceOrganisationRepository();
        return maintenanceOrganisationRepository;
    }

    @Override
    public IPropertyTenantUpdateableRepository getUpdateablePropertyTenantRepository() {
        if(propertyTenantRepository == null) propertyTenantRepository = new PropertyTenantRepository();
        return propertyTenantRepository;
    }

    @Override
    public IMaintenanceEngineerUpdateableRepository getUpdateableMaintenanceEngineerRepository() {
        if(maintenanceEngineerRepository == null) maintenanceEngineerRepository = new MaintenanceEngineerRepository();
        return maintenanceEngineerRepository;
    }

    @Override
    public IDiagnosticRequestUpdateableRepository getUpdateableDiagnosticRequestRepository() {
        if(diagnosticRequestRepository == null) diagnosticRequestRepository = new DiagnosticRequestRepository();
        return diagnosticRequestRepository;
    }

    @Override
    public IRepairReportUpdateableRepository getUpdateableRepairReportRepository() {
        if(repairReportRepository == null) repairReportRepository = new RepairReportRepository();
        return repairReportRepository;
    }

    @Override
    public IPendingRepairReportUpdateableRepository getUpdateablePendingRepairReportRepository() {
        if(pendingRepairReportRepository == null) pendingRepairReportRepository = new PendingRepairReportRepository();
        return pendingRepairReportRepository;
    }

    @Override
    public IPendingMaintenanceSchedulingUpdateableRepository getUpdateablePendingMaintenanceSchedulingRepository() {
        if(pendingMaintenanceSchedulingRepository == null) pendingMaintenanceSchedulingRepository = new PendingMaintenanceSchedulingRepository();
        return pendingMaintenanceSchedulingRepository;
    }

    @Override
    public IMaintenanceScheduleUpdateableRepository getUpdateableMaintenanceScheduleRepository() {
        if(maintenanceScheduleRepository == null) maintenanceScheduleRepository = new MaintenanceScheduleRepository();
        return maintenanceScheduleRepository;
    }

    @Override
    public void observeAccountRepository(Observer obs) {
        if(accountRepository == null)accountRepository = new AccountRepository();
        accountRepository.addObserver(obs);
    }

    @Override
    public void observePropertyManagerRepository(Observer obs) {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        propertyManagerRepository.addObserver(obs);
    }

    @Override
    public void observePropertyRepository(Observer obs) {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        propertyRepository.addObserver(obs);
    }

    @Override
    public void observePropertyApplianceRepository(Observer obs) {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        propertyApplianceRepository.addObserver(obs);
    }

    @Override
    public void observeApplianceStatusRepository(Observer obs) {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        applianceStatusRepository.addObserver(obs);
    }

    @Override
    public void observeApplianceRepository(Observer obs) {
        if(applianceRepository == null) applianceRepository = new ApplianceRepository();
        applianceRepository.addObserver(obs);
    }

    @Override
    public void observeDiagnosticReportRepository(Observer obs) {
        if(diagnosticReportRepository == null) diagnosticReportRepository = new DiagnosticReportRepository();
        diagnosticReportRepository.addObserver(obs);
    }

    @Override
    public void observerMaintenanceOrganisationRepository(Observer obs) {
        if(maintenanceOrganisationRepository == null) maintenanceOrganisationRepository = new MaintenanceOrganisationRepository();
        maintenanceOrganisationRepository.addObserver(obs);
    }

    @Override
    public void observerPropertyTenantRepository(Observer obs) {
        if(propertyTenantRepository == null) propertyTenantRepository = new PropertyTenantRepository();
        propertyTenantRepository.addObserver(obs);
    }

    @Override
    public void observerMaintenanceEngineerRepository(Observer obs) {
        if(maintenanceEngineerRepository == null) maintenanceEngineerRepository = new MaintenanceEngineerRepository();
        maintenanceEngineerRepository.addObserver(obs);
    }

    @Override
    public void observeDiagnosticRequestRepository(Observer obs) {
        if(diagnosticRequestRepository == null) diagnosticRequestRepository = new DiagnosticRequestRepository();
        diagnosticRequestRepository.addObserver(obs);
    }

    @Override
    public void observeRepairReportRepository(Observer obs) {
        if(repairReportRepository == null) repairReportRepository = new RepairReportRepository();
        repairReportRepository.addObserver(obs);
    }

    @Override
    public void observePendingRepairReportRepository(Observer obs) {
        if(pendingRepairReportRepository == null) pendingRepairReportRepository = new PendingRepairReportRepository();
        pendingRepairReportRepository.addObserver(obs);
    }

    @Override
    public void observePendingMaintenanceSchedulingRepository(Observer obs) {
        if(pendingMaintenanceSchedulingRepository == null) pendingMaintenanceSchedulingRepository = new PendingMaintenanceSchedulingRepository();
        pendingMaintenanceSchedulingRepository.addObserver(obs);
    }

    @Override
    public void observeMaintenanceScheduleRepository(Observer obs) {
        if(maintenanceScheduleRepository == null) maintenanceScheduleRepository = new MaintenanceScheduleRepository();
        maintenanceScheduleRepository.addObserver(obs);
    }

    public void clear() {
        accountRepository = null;
        propertyRepository = null;
        propertyManagerRepository = null;
        propertyApplianceRepository = null;
        applianceStatusRepository = null;
        applianceRepository = null;
        diagnosticReportRepository = null;
        maintenanceOrganisationRepository = null;
        propertyTenantRepository = null;
        maintenanceEngineerRepository = null;
        diagnosticRequestRepository = null;
        repairReportRepository = null;
        pendingRepairReportRepository = null;
        pendingMaintenanceSchedulingRepository = null;
    }
}
