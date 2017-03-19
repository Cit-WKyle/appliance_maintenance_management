package com.appl_maint_mngt.repositories.property_appliance;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.property_appliance.APropertyAppliance;
import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;
import com.appl_maint_mngt.models.property_appliance.PropertyAppliance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyApplianceRepository extends APropertyApplianceRepository {

    private LongSparseArray<APropertyAppliance> propertyAppliances;

    public PropertyApplianceRepository() {
        propertyAppliances = new LongSparseArray<>();
    }

    @Override
    public void addItems(List<PropertyAppliance> propertyAppliances) {
        for(PropertyAppliance pAppl: propertyAppliances) {
            this.propertyAppliances.put(pAppl.getId(), pAppl);
        }
        updateObservers(IPropertyApplianceObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IPropertyApplianceReadable getForId(Long id) {
        return propertyAppliances.get(id);
    }

    @Override
    public List<IPropertyApplianceReadable> getAll() {
        List<IPropertyApplianceReadable> readablesList = new ArrayList<>();
        for(int i = 0; i < this.propertyAppliances.size(); i++) {
            readablesList.add(propertyAppliances.valueAt(i));
        }
        return readablesList;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
