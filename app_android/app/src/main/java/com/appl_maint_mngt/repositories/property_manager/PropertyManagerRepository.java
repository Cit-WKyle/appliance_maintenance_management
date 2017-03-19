package com.appl_maint_mngt.repositories.property_manager;

import com.appl_maint_mngt.models.property_manager.APropertyManager;
import com.appl_maint_mngt.models.property_manager.IPropertyManagerReadable;
import com.appl_maint_mngt.models.property_manager.PropertyManager;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyManagerRepository extends APropertyManagerRepository {

    private APropertyManager propertyManager;

    public PropertyManagerRepository() {
        propertyManager = new PropertyManager();
    }

    @Override
    public void update(APropertyManager propertyManager) {
        this.propertyManager.setCurrentPropertyIds(propertyManager.getCurrentPropertyIds());
        this.propertyManager.setPreviousPropertyIds(propertyManager.getPreviousPropertyIds());
        updateObservers(IPropertyManagerObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IPropertyManagerReadable get() {
        return propertyManager;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
