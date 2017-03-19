package com.appl_maint_mngt.services.common;

import com.appl_maint_mngt.services.account.IUserProfileService;
import com.appl_maint_mngt.services.account.UserAuthService;
import com.appl_maint_mngt.services.account.IUserAuthService;
import com.appl_maint_mngt.services.account.UserProfileService;
import com.appl_maint_mngt.services.appliance.ApplianceService;
import com.appl_maint_mngt.services.appliance.IApplianceService;
import com.appl_maint_mngt.services.appliance_status.ApplianceStatusService;
import com.appl_maint_mngt.services.appliance_status.IApplianceStatusService;
import com.appl_maint_mngt.services.diagnostic_report.DiagnosticReportService;
import com.appl_maint_mngt.services.diagnostic_report.IDiagnosticReportService;
import com.appl_maint_mngt.services.maintenance_engineer.IMaintenanceEngineerService;
import com.appl_maint_mngt.services.maintenance_engineer.MaintenanceEngineerService;
import com.appl_maint_mngt.services.maintenance_organisation.IMaintenanceOrganisationService;
import com.appl_maint_mngt.services.maintenance_organisation.MaintenanceOrganisationService;
import com.appl_maint_mngt.services.property.IPropertyService;
import com.appl_maint_mngt.services.property.PropertyService;
import com.appl_maint_mngt.services.property_appliance.IPropertyApplianceService;
import com.appl_maint_mngt.services.property_appliance.PropertyApplianceService;
import com.appl_maint_mngt.services.property_manager.IPropertyManagerService;
import com.appl_maint_mngt.services.property_manager.PropertyManagerService;
import com.appl_maint_mngt.services.property_tenant.IPropertyTenantService;
import com.appl_maint_mngt.services.property_tenant.PropertyTenantService;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ServiceFactory implements IServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();

    private IUserAuthService userAuthervice;
    private IUserProfileService profileService;
    private IPropertyManagerService propertyManagerService;
    private IPropertyService propertyService;
    private IPropertyApplianceService propertyApplianceService;
    private IApplianceStatusService applianceStatusService;
    private IApplianceService applianceService;
    private IDiagnosticReportService diagnosticReportService;
    private IMaintenanceOrganisationService maintenanceOrganisationService;
    private IPropertyTenantService propertyTenantService;
    private IMaintenanceEngineerService maintenanceEngineerService;

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }

    @Override
    public IUserAuthService getUserAuthService() {
        if(userAuthervice == null) userAuthervice = new UserAuthService();
        return userAuthervice;
    }

    @Override
    public IUserProfileService getUserProfileService() {
        if(profileService == null) profileService = new UserProfileService();
        return profileService;
    }

    @Override
    public IPropertyManagerService getPropertyManagerService() {
        if(propertyManagerService == null) propertyManagerService = new PropertyManagerService();
        return propertyManagerService;
    }

    @Override
    public IPropertyService getPropertyService() {
        if(propertyService == null) propertyService = new PropertyService();
        return propertyService;
    }

    @Override
    public IPropertyApplianceService getPropertyApplianceService() {
        if(propertyApplianceService == null) propertyApplianceService = new PropertyApplianceService();
        return propertyApplianceService;
    }

    @Override
    public IApplianceStatusService getApplianceStatusService() {
        if(applianceStatusService == null) applianceStatusService = new ApplianceStatusService();
        return applianceStatusService;
    }

    @Override
    public IApplianceService getApplianceService() {
        if(applianceService == null) applianceService = new ApplianceService();
        return applianceService;
    }

    @Override
    public IDiagnosticReportService getDiagnosticReportService() {
        if(diagnosticReportService == null) diagnosticReportService = new DiagnosticReportService();
        return diagnosticReportService;
    }

    @Override
    public IMaintenanceOrganisationService getMaintenanceOrganisationService() {
        if(maintenanceOrganisationService == null) maintenanceOrganisationService = new MaintenanceOrganisationService();
        return maintenanceOrganisationService;
    }

    @Override
    public IPropertyTenantService getPropertyTenantService() {
        if(propertyTenantService == null) propertyTenantService = new PropertyTenantService();
        return propertyTenantService;
    }

    @Override
    public IMaintenanceEngineerService getMaintenanceEngineerService() {
        if(maintenanceEngineerService == null) maintenanceEngineerService = new MaintenanceEngineerService();
        return maintenanceEngineerService;
    }
}
