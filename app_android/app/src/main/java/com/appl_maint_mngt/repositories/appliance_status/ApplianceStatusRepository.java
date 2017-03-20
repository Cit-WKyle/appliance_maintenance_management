package com.appl_maint_mngt.repositories.appliance_status;

import android.util.LongSparseArray;

import com.appl_maint_mngt.models.appliance.ApplianceType;
import com.appl_maint_mngt.models.appliance_status.ApplianceStatus;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public class ApplianceStatusRepository extends AApplianceStatusRepository {

    private LongSparseArray<ApplianceStatus> applianceStatuses;

    public ApplianceStatusRepository() {
        applianceStatuses = new LongSparseArray<>();
    }

    @Override
    public void addItem(ApplianceStatus status) {
        applianceStatuses.put(status.getId(), status);
        updateObservers(IApplianceStatusObserverUpdateTypes.CURRENT_STATUS_UPDATE);
    }

    @Override
    public void addItems(List<ApplianceStatus> statuses) {
        for(ApplianceStatus status: statuses) {
            applianceStatuses.put(status.getId(), status);
        }
        updateObservers(IApplianceStatusObserverUpdateTypes.STATUS_HISTORY_UPDATE);
    }

    @Override
    public void addItems(List<ApplianceStatus> statuses, String updateType) {
        for(ApplianceStatus status: statuses) {
            applianceStatuses.put(status.getId(), status);
        }
        updateObservers(updateType);
    }

    @Override
    public IApplianceStatusReadable getForId(Long id) {
        return applianceStatuses.get(id);
    }

    @Override
    public List<IApplianceStatusReadable> getForApplianceType(ApplianceType type) {
        List<IApplianceStatusReadable> list = new ArrayList();
        for(int i = 0; i< applianceStatuses.size(); i++) {
            IApplianceStatusReadable stat = applianceStatuses.valueAt(i);
            if(stat.getApplianceType().equals(type) || stat.getApplianceType().equals(ApplianceType.COMMON)) list.add(stat);
        }
        return list;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
