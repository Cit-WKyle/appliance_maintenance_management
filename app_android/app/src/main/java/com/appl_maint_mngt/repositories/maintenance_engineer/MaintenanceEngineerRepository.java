package com.appl_maint_mngt.repositories.maintenance_engineer;

import com.appl_maint_mngt.models.maintenance_engineer.AMaintenanceEngineer;
import com.appl_maint_mngt.models.maintenance_engineer.IMaintenanceEngineerReadable;
import com.appl_maint_mngt.models.maintenance_engineer.MaintenanceEngineer;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationObserverUpdateTypes;

/**
 * Created by Kyle on 19/03/2017.
 */

public class MaintenanceEngineerRepository extends AMaintenanceEngineerRepository {

    private AMaintenanceEngineer engineer;

    public MaintenanceEngineerRepository() {
        this.engineer = new MaintenanceEngineer();
    }

    @Override
    public void update(AMaintenanceEngineer engineer) {
        this.engineer = engineer;
        updateObservers(IMaintenanceOrganisationObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IMaintenanceEngineerReadable get() {
        return engineer;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
