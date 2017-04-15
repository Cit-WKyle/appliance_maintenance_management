package com.appl_maint_mngt.appliance.repositories;

import com.appl_maint_mngt.appliance.models.AAppliance;
import com.appl_maint_mngt.appliance.models.Appliance;
import com.appl_maint_mngt.appliance.models.interfaces.IApplianceReadable;
import com.appl_maint_mngt.appliance.repositories.constants.IApplianceObserverUpdateTypes;

import java.util.HashMap;

/**
 * Created by Kyle on 18/03/2017.
 */

public class ApplianceRepository extends AApplianceRepository {

    private HashMap<String, AAppliance> appliances;

    public ApplianceRepository() {
        appliances = new HashMap<>();
    }

    @Override
    public void addItem(Appliance appliance) {
        appliances.put(appliance.getId(), appliance);
        updateObservers(IApplianceObserverUpdateTypes.MODEL_UPDATE );
    }

    @Override
    public IApplianceReadable get(String id) {
        return appliances.get(id);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
