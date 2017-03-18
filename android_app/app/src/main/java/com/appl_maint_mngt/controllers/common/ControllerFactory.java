package com.appl_maint_mngt.controllers.common;

import com.appl_maint_mngt.controllers.account.AccountController;
import com.appl_maint_mngt.controllers.account.IAccountController;
import com.appl_maint_mngt.controllers.appliance_status.ApplianceStatusController;
import com.appl_maint_mngt.controllers.appliance_status.IApplianceStatusController;
import com.appl_maint_mngt.controllers.property.IPropertyController;
import com.appl_maint_mngt.controllers.property.PropertyController;
import com.appl_maint_mngt.controllers.property_appliance.IPropertyApplianceController;
import com.appl_maint_mngt.controllers.property_appliance.PropertyApplianceController;
import com.appl_maint_mngt.controllers.property_manager.IPropertyManagerController;
import com.appl_maint_mngt.controllers.property_manager.PropertyManagerController;
import com.appl_maint_mngt.services.appliance_status.ApplianceStatusService;
import com.appl_maint_mngt.services.appliance_status.IApplianceStatusService;

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
}
