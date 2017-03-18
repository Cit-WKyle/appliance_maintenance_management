package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.AAccountRepository;
import com.appl_maint_mngt.repositories.account.AccountRepository;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;
import com.appl_maint_mngt.repositories.appliance_status.AApplianceStatusRepository;
import com.appl_maint_mngt.repositories.appliance_status.ApplianceStatusRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.repositories.property.APropertyRepository;
import com.appl_maint_mngt.repositories.property.IPropertyReadableRepository;
import com.appl_maint_mngt.repositories.property.IPropertyUpdateableRepository;
import com.appl_maint_mngt.repositories.property.PropertyRepository;
import com.appl_maint_mngt.repositories.property_appliance.APropertyApplianceRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.repositories.property_appliance.PropertyApplianceRepository;
import com.appl_maint_mngt.repositories.property_manager.APropertyManagerRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerReadableRepository;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerUpdateableRepository;
import com.appl_maint_mngt.repositories.property_manager.PropertyManagerRepository;

import java.util.Observer;

/**
 * Created by Kyle on 15/03/2017.
 */
public class RepositoryFactory implements IReadableRepositoryFactory, IUpdateableRepositoryFactory, IObserveRepositoriesHandler {
    private static RepositoryFactory ourInstance = new RepositoryFactory();

    private AAccountRepository accountRepository;
    private APropertyRepository propertyRepository;
    private APropertyManagerRepository propertyManagerRepository;
    private APropertyApplianceRepository propertyApplianceRepository;
    private AApplianceStatusRepository applianceStatusRepository;

    public static RepositoryFactory getInstance() {
        return ourInstance;
    }

    private RepositoryFactory() {
    }

    @Override
    public IAccountReadableRepository getReadableAccountRepository() {
        if(accountRepository == null)accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public IPropertyManagerReadableRepository getReadablePropertyManagerRepository() {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        return propertyManagerRepository;
    }

    @Override
    public IPropertyReadableRepository getReadablePropertyRepository() {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        return propertyRepository;
    }

    @Override
    public IPropertyApplianceReadableRepository getReadablePropertyApplianceRepository() {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        return propertyApplianceRepository;
    }

    @Override
    public IApplianceStatusReadableRepository getReadableApplianceStatusRepository() {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        return applianceStatusRepository;
    }

    @Override
    public IAccountUpdateableRepository getUpdateableAccountRepository() {
        if(accountRepository == null)accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public IPropertyManagerUpdateableRepository getUpdateablePropertyManagerRepository() {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        return propertyManagerRepository;
    }

    @Override
    public IPropertyUpdateableRepository getUpdateablePropertyRepository() {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        return propertyRepository;
    }

    @Override
    public IPropertyApplianceUpdateableRepository getUpdateablePropertyApplianceRepository() {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        return propertyApplianceRepository;
    }

    @Override
    public IApplianceStatusUpdateableRepository getUpdateableApplianceStatusRepository() {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        return applianceStatusRepository;
    }

    @Override
    public void observeAccountRepository(Observer obs) {
        if(accountRepository == null)accountRepository = new AccountRepository();
        accountRepository.addObserver(obs);
    }

    @Override
    public void observePropertyManagerRepository(Observer obs) {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        propertyManagerRepository.addObserver(obs);
    }

    @Override
    public void observePropertyRepository(Observer obs) {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        propertyRepository.addObserver(obs);
    }

    @Override
    public void observePropertyApplianceRepository(Observer obs) {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        propertyApplianceRepository.addObserver(obs);
    }

    @Override
    public void observeApplianceStatusRepository(Observer obs) {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        applianceStatusRepository.addObserver(obs);
    }
}
