package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.repositories.property.IPropertyReadableRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerReadableRepository;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IReadableRepositoryFactory {

    IAccountReadableRepository getReadableAccountRepository();

    IPropertyManagerReadableRepository getReadablePropertyManagerRepository();

    IPropertyReadableRepository getReadablePropertyRepository();

    IPropertyApplianceReadableRepository getReadablePropertyApplianceRepository();

    IApplianceStatusReadableRepository getReadableApplianceStatusRepository();
}
