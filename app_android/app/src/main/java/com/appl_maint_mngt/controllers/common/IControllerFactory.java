package com.appl_maint_mngt.controllers.common;

import com.appl_maint_mngt.controllers.account.IAccountController;
import com.appl_maint_mngt.controllers.appliance.IApplianceController;
import com.appl_maint_mngt.controllers.appliance_status.IApplianceStatusController;
import com.appl_maint_mngt.controllers.diagnostic_report.IDiagnosticReportController;
import com.appl_maint_mngt.controllers.diagnostic_request.IDiagnosticRequestController;
import com.appl_maint_mngt.controllers.maintenance_engineer.IMaintenanceEngineerController;
import com.appl_maint_mngt.controllers.maintenance_organisation.IMaintenanceOrganisationController;
import com.appl_maint_mngt.controllers.maintenance_schedule.IMaintenanceScheduleController;
import com.appl_maint_mngt.controllers.pending_maintenance_scheduling.IPendingMaintenanceSchedulingController;
import com.appl_maint_mngt.controllers.pending_repair_report.IPendingRepairReportController;
import com.appl_maint_mngt.controllers.property.IPropertyController;
import com.appl_maint_mngt.controllers.property_appliance.IPropertyApplianceController;
import com.appl_maint_mngt.controllers.property_appliance_status_update.IPropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.controllers.property_manager.IPropertyManagerController;
import com.appl_maint_mngt.controllers.property_tenant.IPropertyTenantController;
import com.appl_maint_mngt.controllers.repair_report.IRepairReportController;
import com.appl_maint_mngt.services.property_appliance_status_update.IPropertyApplianceStatusUpdateService;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IControllerFactory {
    IAccountController getAccountController();
    IPropertyManagerController getPropertyManagerController();
    IPropertyController getPropertyController();
    IPropertyApplianceController getPropertyApplianceController();
    IApplianceStatusController getApplianceStatusController();
    IApplianceController getApplianceController();
    IDiagnosticReportController getDiagnosticReportController();
    IMaintenanceOrganisationController getMaintenanceOrganisationController();
    IPropertyTenantController getPropertyTenantController();
    IMaintenanceEngineerController getMaintenanceEngineerController();
    IPropertyApplianceStatusUpdateController getPropertyApplianceStatusUpdateController();
    IDiagnosticRequestController getDiagnosticRequestController();
    IRepairReportController getRepairReportController();
    IPendingRepairReportController getPendingRepairReportController();
    IPendingMaintenanceSchedulingController getPendingMaintenanceSchedulingController();
    IMaintenanceScheduleController getMaintenanceScheduleController();
}
