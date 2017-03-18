package com.appl_maint_mngt.services.common;

import com.appl_maint_mngt.services.account.IUserAuthService;
import com.appl_maint_mngt.services.account.IUserProfileService;
import com.appl_maint_mngt.services.appliance.IApplianceService;
import com.appl_maint_mngt.services.appliance_status.IApplianceStatusService;
import com.appl_maint_mngt.services.diagnostic_report.IDiagnosticReportService;
import com.appl_maint_mngt.services.maintenance_organisation.IMaintenanceOrganisationService;
import com.appl_maint_mngt.services.property.IPropertyService;
import com.appl_maint_mngt.services.property_appliance.IPropertyApplianceService;
import com.appl_maint_mngt.services.property_manager.IPropertyManagerService;

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
}
