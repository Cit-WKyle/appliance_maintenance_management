package com.appl_maint_mngt.services.common;

import com.appl_maint_mngt.services.account.IUserAuthService;
import com.appl_maint_mngt.services.account.IUserProfileService;
import com.appl_maint_mngt.services.appliance.IApplianceService;
import com.appl_maint_mngt.services.appliance_status.IApplianceStatusService;
import com.appl_maint_mngt.services.diagnostic_report.IDiagnosticReportService;
import com.appl_maint_mngt.services.diagnostic_request.IDiagnosticRequestService;
import com.appl_maint_mngt.services.maintenance_engineer.IMaintenanceEngineerService;
import com.appl_maint_mngt.services.maintenance_organisation.IMaintenanceOrganisationService;
import com.appl_maint_mngt.services.maintenance_schedule.IMaintenanceScheduleService;
import com.appl_maint_mngt.services.pending_maintenance_scheduling.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.services.pending_repair_report.IPendingRepairReportService;
import com.appl_maint_mngt.services.property.IPropertyService;
import com.appl_maint_mngt.services.property_appliance.IPropertyApplianceService;
import com.appl_maint_mngt.services.property_appliance_status_update.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.services.property_manager.IPropertyManagerService;
import com.appl_maint_mngt.services.property_tenant.IPropertyTenantService;
import com.appl_maint_mngt.services.repair_report.IRepairReportService;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IServiceFactory {

    IUserAuthService getUserAuthService();

    IUserProfileService getUserProfileService();

    IPropertyManagerService getPropertyManagerService();

    IPropertyService getPropertyService();

    IPropertyApplianceService getPropertyApplianceService();

    IApplianceStatusService getApplianceStatusService();

    IApplianceService getApplianceService();

    IDiagnosticReportService getDiagnosticReportService();

    IMaintenanceOrganisationService getMaintenanceOrganisationService();

    IPropertyTenantService getPropertyTenantService();

    IMaintenanceEngineerService getMaintenanceEngineerService();

    IPropertyApplianceStatusUpdateService getPropertyApplianceUpdateService();

    IDiagnosticRequestService getDiagnosticRequestService();

    IRepairReportService getRepairReportService();

    IPendingRepairReportService getPendingRepairReportService();

    IPendingMaintenanceSchedulingService getPendingMaintenanceSchedulingService();

    IMaintenanceScheduleService getMaintenanceScheduleService();
}
