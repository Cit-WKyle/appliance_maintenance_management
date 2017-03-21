package com.appl_maint_mngt.controllers.common;

import com.appl_maint_mngt.controllers.account.AccountController;
import com.appl_maint_mngt.controllers.account.IAccountController;
import com.appl_maint_mngt.controllers.appliance.ApplianceController;
import com.appl_maint_mngt.controllers.appliance.IApplianceController;
import com.appl_maint_mngt.controllers.appliance_status.ApplianceStatusController;
import com.appl_maint_mngt.controllers.appliance_status.IApplianceStatusController;
import com.appl_maint_mngt.controllers.diagnostic_report.DiagnosticReportController;
import com.appl_maint_mngt.controllers.diagnostic_report.IDiagnosticReportController;
import com.appl_maint_mngt.controllers.diagnostic_request.DiagnosticRequestController;
import com.appl_maint_mngt.controllers.diagnostic_request.IDiagnosticRequestController;
import com.appl_maint_mngt.controllers.maintenance_engineer.IMaintenanceEngineerController;
import com.appl_maint_mngt.controllers.maintenance_engineer.MaintenanceEngineerController;
import com.appl_maint_mngt.controllers.maintenance_organisation.IMaintenanceOrganisationController;
import com.appl_maint_mngt.controllers.maintenance_organisation.MaintenanceOrganisationController;
import com.appl_maint_mngt.controllers.property.IPropertyController;
import com.appl_maint_mngt.controllers.property.PropertyController;
import com.appl_maint_mngt.controllers.property_appliance.IPropertyApplianceController;
import com.appl_maint_mngt.controllers.property_appliance.PropertyApplianceController;
import com.appl_maint_mngt.controllers.property_appliance_status_update.IPropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.controllers.property_appliance_status_update.PropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.controllers.property_manager.IPropertyManagerController;
import com.appl_maint_mngt.controllers.property_manager.PropertyManagerController;
import com.appl_maint_mngt.controllers.property_tenant.IPropertyTenantController;
import com.appl_maint_mngt.controllers.property_tenant.PropertyTenantController;
import com.appl_maint_mngt.controllers.repair_report.IRepairReportController;
import com.appl_maint_mngt.controllers.repair_report.RepairReportController;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ControllerFactory implements IControllerFactory {

    private static ControllerFactory ourInstance = new ControllerFactory();

    private IAccountController accountController;
    private IPropertyManagerController propertyManagerController;
    private IPropertyController propertyController;
    private IPropertyApplianceController propertyApplianceController;
    private IApplianceStatusController applianceStatusController;
    private IApplianceController applianceController;
    private IDiagnosticReportController diagnosticReportController;
    private IMaintenanceOrganisationController maintenanceOrganisationController;
    private IPropertyTenantController propertyTenantController;
    private IMaintenanceEngineerController maintenanceEngineerController;
    private IPropertyApplianceStatusUpdateController propertyApplianceStatusUpdateController;
    private IDiagnosticRequestController diagnosticRequestController;
    private IRepairReportController repairReportController;

    public static ControllerFactory getInstance() {
        return ourInstance;
    }

    private ControllerFactory() {
    }

    @Override
    public IAccountController getAccountController() {
        if(accountController == null) accountController = new AccountController();
        return accountController;
    }

    @Override
    public IPropertyManagerController getPropertyManagerController() {
        if(propertyManagerController == null) propertyManagerController = new PropertyManagerController();
        return propertyManagerController;
    }

    @Override
    public IPropertyController getPropertyController() {
        if(propertyController == null) propertyController = new PropertyController();
        return propertyController;
    }

    @Override
    public IPropertyApplianceController getPropertyApplianceController() {
        if(propertyApplianceController == null) propertyApplianceController = new PropertyApplianceController();
        return propertyApplianceController;
    }

    @Override
    public IApplianceStatusController getApplianceStatusController() {
        if(applianceStatusController == null) applianceStatusController = new ApplianceStatusController();
        return applianceStatusController;
    }

    @Override
    public IApplianceController getApplianceController() {
        if(applianceController == null) applianceController = new ApplianceController();
        return applianceController;
    }

    @Override
    public IDiagnosticReportController getDiagnosticReportController() {
        if(diagnosticReportController == null) diagnosticReportController = new DiagnosticReportController();
        return diagnosticReportController;
    }

    @Override
    public IMaintenanceOrganisationController getMaintenanceOrganisationController() {
        if(maintenanceOrganisationController == null) maintenanceOrganisationController = new MaintenanceOrganisationController();
        return maintenanceOrganisationController;
    }

    @Override
    public IPropertyTenantController getPropertyTenantController() {
        if(propertyTenantController == null) propertyTenantController = new PropertyTenantController();
        return propertyTenantController;
    }

    @Override
    public IMaintenanceEngineerController getMaintenanceEngineerController() {
        if(maintenanceEngineerController == null) maintenanceEngineerController = new MaintenanceEngineerController();
        return maintenanceEngineerController;
    }

    @Override
    public IPropertyApplianceStatusUpdateController getPropertyApplianceStatusUpdateController() {
        if(propertyApplianceStatusUpdateController == null) propertyApplianceStatusUpdateController = new PropertyApplianceStatusUpdateController();
        return propertyApplianceStatusUpdateController;
    }

    @Override
    public IDiagnosticRequestController getDiagnosticRequestController() {
        if(diagnosticRequestController == null) diagnosticRequestController = new DiagnosticRequestController();
        return diagnosticRequestController;
    }

    @Override
    public IRepairReportController getRepairReportController() {
        if(repairReportController == null) repairReportController = new RepairReportController();
        return repairReportController;
    }

}
