package com.appl_maint_mngt.repositories.maintenance_organisation;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.models.maintenance_organisation.MaintenanceOrganisation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public class MaintenanceOrganisationRepository extends AMaintenanceOrganisationRepository {

    private LongSparseArray<MaintenanceOrganisation> maintenanceOrganisations;

    public MaintenanceOrganisationRepository() {
        this.maintenanceOrganisations = new LongSparseArray<>();
    }

    @Override
    public void addItems(List<MaintenanceOrganisation> maintOrg) {
        for(MaintenanceOrganisation org: maintOrg) {
            maintenanceOrganisations.put(org.getId(), org);
        }
    }

    @Override
    public List<IMaintenanceOrganisationReadable> getAll() {
        List<IMaintenanceOrganisationReadable> list = new ArrayList<>();
        for(int i = 0; i < maintenanceOrganisations.size(); i++) {
            list.add(maintenanceOrganisations.valueAt(i));
        }
        return null;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
