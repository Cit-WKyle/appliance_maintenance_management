package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.repositories.property.IPropertyUpdateableRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerUpdateableRepository;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IUpdateableRepositoryFactory {

    IAccountUpdateableRepository getUpdateableAccountRepository();

    IPropertyManagerUpdateableRepository getUpdateablePropertyManagerRepository();

    IPropertyUpdateableRepository getUpdateablePropertyRepository();

    IPropertyApplianceUpdateableRepository getUpdateablePropertyApplianceRepository();

    IApplianceStatusUpdateableRepository getUpdateableApplianceStatusRepository();
}
