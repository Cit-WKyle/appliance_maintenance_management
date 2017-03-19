package com.appl_maint_mngt.repositories.property_tenant;

import com.appl_maint_mngt.models.property_tenant.APropertyTenant;
import com.appl_maint_mngt.models.property_tenant.IPropertyTenantReadable;
import com.appl_maint_mngt.models.property_tenant.PropertyTenant;

/**
 * Created by Kyle on 19/03/2017.
 */

public class PropertyTenantRepository extends APropertyTenantRepository {

    private APropertyTenant tenant;

    public PropertyTenantRepository() {
        tenant = new PropertyTenant();
    }

    @Override
    public void update(APropertyTenant tenant) {
        this.tenant = tenant;
        updateObservers(IPropertyTenantObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IPropertyTenantReadable get() {
        return tenant;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
