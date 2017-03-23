package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.appliance.IApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerUpdateableRepository;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationUpdateableRepository;
import com.appl_maint_mngt.repositories.pending_maintenance_scheduling.IPendingMaintenanceSchedulingUpdateableRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.repositories.property.IPropertyUpdateableRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerUpdateableRepository;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantUpdateableRepository;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportUpdateableRepository;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IUpdateableRepositoryFactory {

    IAccountUpdateableRepository getUpdateableAccountRepository();

    IPropertyManagerUpdateableRepository getUpdateablePropertyManagerRepository();

    IPropertyUpdateableRepository getUpdateablePropertyRepository();

    IPropertyApplianceUpdateableRepository getUpdateablePropertyApplianceRepository();

    IApplianceStatusUpdateableRepository getUpdateableApplianceStatusRepository();

    IApplianceUpdateableRepository getUpdateableApplianceRepository();

    IDiagnosticReportUpdateableRepository getUpdateableDiagnosticReportRepository();

    IMaintenanceOrganisationUpdateableRepository getUpdateableMaintenanceOrganisationRepository();

    IPropertyTenantUpdateableRepository getUpdateablePropertyTenantRepository();

    IMaintenanceEngineerUpdateableRepository getUpdateableMaintenanceEngineerRepository();

    IDiagnosticRequestUpdateableRepository getUpdateableDiagnosticRequestRepository();

    IRepairReportUpdateableRepository getUpdateableRepairReportRepository();

    IPendingRepairReportUpdateableRepository getUpdateablePendingRepairReportRepository();

    IPendingMaintenanceSchedulingUpdateableRepository getUpdateablePendingMaintenanceSchedulingRepository();
}
