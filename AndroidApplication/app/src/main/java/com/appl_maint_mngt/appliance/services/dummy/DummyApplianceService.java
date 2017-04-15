package com.appl_maint_mngt.appliance.services.dummy;

import com.appl_maint_mngt.appliance.models.Appliance;
import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceUpdateableRepository;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;

/**
 * Created by Kyle on 08/04/2017.
 */

public class DummyApplianceService implements IApplianceService {
    @Override
    public void get(String id, IErrorCallback errorCallback) {
        IApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getApplianceRepository();

        Appliance appliance = new Appliance();
        appliance.setName("Brand");
        appliance.setName("Name");
        appliance.setId(id);
        appliance.setProductNo("ProductNo");
        appliance.setType(ApplianceType.DISH_WASHER);
        repository.addItem(appliance);
    }
}
