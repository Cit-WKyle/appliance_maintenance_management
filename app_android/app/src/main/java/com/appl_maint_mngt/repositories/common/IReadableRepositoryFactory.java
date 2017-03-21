package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.appliance.IApplianceReadableRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportReadableRepository;
import com.appl_maint_mngt.repositories.diagnostic_request.IDiagnosticRequestReadableRepository;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerReadableRepository;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationReadableRepository;
import com.appl_maint_mngt.repositories.pending_repair_report.IPendingRepairReportReadableRepository;
import com.appl_maint_mngt.repositories.property.IPropertyReadableRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerReadableRepository;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantReadableRepository;
import com.appl_maint_mngt.repositories.repair_report.IRepairReportReadableRepository;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IReadableRepositoryFactory {

    IAccountReadableRepository getReadableAccountRepository();

    IPropertyManagerReadableRepository getReadablePropertyManagerRepository();

    IPropertyReadableRepository getReadablePropertyRepository();

    IPropertyApplianceReadableRepository getReadablePropertyApplianceRepository();

    IApplianceStatusReadableRepository getReadableApplianceStatusRepository();

    IApplianceReadableRepository getReadableApplianceRepository();

    IDiagnosticReportReadableRepository getReadableDiagnosticReportRepository();

    IMaintenanceOrganisationReadableRepository getReadableMaintenanceOrganisationRepository();

    IPropertyTenantReadableRepository getReadablePropertyTenantRepository();

    IMaintenanceEngineerReadableRepository getReadableMaintenanceEngineerRepository();

    IDiagnosticRequestReadableRepository getReadableDiagnosticRequestRepository();

    IRepairReportReadableRepository getReadableRepairReportRepository();

    IPendingRepairReportReadableRepository getReadablePendingRepairReportRepository();

}
