package com.appl_maint_mngt.controllers.common;

import com.appl_maint_mngt.controllers.account.IAccountController;
import com.appl_maint_mngt.controllers.appliance.IApplianceController;
import com.appl_maint_mngt.controllers.appliance_status.IApplianceStatusController;
import com.appl_maint_mngt.controllers.diagnostic_report.IDiagnosticReportController;
import com.appl_maint_mngt.controllers.maintenance_organisation.IMaintenanceOrganisationController;
import com.appl_maint_mngt.controllers.property.IPropertyController;
import com.appl_maint_mngt.controllers.property_appliance.IPropertyApplianceController;
import com.appl_maint_mngt.controllers.property_manager.IPropertyManagerController;
import com.appl_maint_mngt.services.appliance_status.IApplianceStatusService;

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
}
