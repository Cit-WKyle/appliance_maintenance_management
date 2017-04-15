package com.appl_maint_mngt.common.services;

import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.common.services.interfaces.IServiceFactory;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;
import com.appl_maint_mngt.property.services.IPropertyService;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

/**
 * Created by Kyle on 07/04/2017.
 */

public class ServiceFactory implements IServiceFactory {

    private IUserAuthService userAuthService;
    private IUserProfileService userProfileService;
    private IMaintenanceEngineerService maintenanceEngineerService;
    private IPropertyManagerService propertyManagerService;
    private IPropertyTenantService propertyTenantService;
    private IPropertyService propertyService;
    private IPropertyApplianceService propertyApplianceService;
    private IApplianceStatusService applianceStatusService;
    private IApplianceService applianceService;
    private IPropertyApplianceStatusUpdateService propertyApplianceStatusUpdateService;
    private IDiagnosticReportService diagnosticReportService;
    private IMaintenanceOrganisationService maintenanceOrganisationService;
    private IDiagnosticRequestService diagnosticRequestService;
    private IRepairReportService repairReportService;
    private IPendingRepairReportService pendingRepairReportService;
    private IMaintenanceScheduleService maintenanceScheduleService;
    private IPendingMaintenanceSchedulingService pendingMaintenanceSchedulingService;

    @Override
    public IUserAuthService createUserAuthService() {
        if(userAuthService == null) userAuthService = null;
        return userAuthService;
    }

    @Override
    public IUserProfileService createUserProfileService() {
        if(userProfileService == null) userProfileService = null;
        return userProfileService;
    }

    @Override
    public IMaintenanceEngineerService createMaintenanceEngineerService() {
        return null;
    }

    @Override
    public IPropertyManagerService createPropertyManagerService() {
        return null;
    }

    @Override
    public IPropertyTenantService createPropertyTenantService() {
        return null;
    }

    @Override
    public IPropertyService createPropertyService() {
        return null;
    }

    @Override
    public IPropertyApplianceService createPropertyApplianceService() {
        return null;
    }

    @Override
    public IApplianceStatusService createApplianceStatusService() {
        return null;
    }

    @Override
    public IApplianceService createApplianceService() {
        return null;
    }

    @Override
    public IPropertyApplianceStatusUpdateService createPropertyApplianceStatusUpdateService() {
        return null;
    }

    @Override
    public IDiagnosticReportService createDiagnosticReportService() {
        return null;
    }

    @Override
    public IMaintenanceOrganisationService createMaintenanceOrganisationService() {
        return null;
    }

    @Override
    public IDiagnosticRequestService createDiagnosticRequestService() {
        return null;
    }

    @Override
    public IRepairReportService createRepairReportService() {
        return null;
    }

    @Override
    public IPendingRepairReportService createPendingRepairReportService() {
        return null;
    }

    @Override
    public IMaintenanceScheduleService createMaintenanceScheduleService() {
        return null;
    }

    @Override
    public IPendingMaintenanceSchedulingService createPendingMaintenanceSchedulingService() {
        return null;
    }
}
