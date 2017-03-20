package com.appl_maint_mngt.controllers.appliance_status;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.appliance.ApplianceType;
import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;
import com.appl_maint_mngt.services.appliance_status.IApplianceStatusService;
import com.appl_maint_mngt.services.common.ServiceFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kyle on 17/03/2017.
 */
public class ApplianceStatusController implements IApplianceStatusController {

    private IApplianceStatusService applianceStatusService;

    public ApplianceStatusController() {
        this.applianceStatusService = ServiceFactory.getInstance().getApplianceStatusService();
    }

    @Override
    public void getForStatusId(Long id, IErrorCallback errorCallback) {
        applianceStatusService.get(id, errorCallback);
    }

    @Override
    public void getForStatusHistory(List<IStatusHistoryReadable> statusHistory, IErrorCallback errorCallback) {
        Set<Long> ids = new HashSet<>();
        for(IStatusHistoryReadable statHist : statusHistory) {
            ids.add(statHist.getStatusId());
        }
        applianceStatusService.findByIdIn(ids, errorCallback);
    }

    @Override
    public void getForApplianceType(ApplianceType type, IErrorCallback errorCallback) {
        Set<ApplianceType> typeAndCommon = new HashSet<>();
        typeAndCommon.add(type);
        typeAndCommon.add(ApplianceType.COMMON);
        applianceStatusService.findByApplianceTypeIn(typeAndCommon, errorCallback);
    }
}
