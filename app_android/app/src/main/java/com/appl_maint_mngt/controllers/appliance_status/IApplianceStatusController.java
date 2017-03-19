package com.appl_maint_mngt.controllers.appliance_status;

import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IApplianceStatusController {

    void getForStatusId(Long id, IErrorCallback errorCallback);

    void getForStatusHistory(List<IStatusHistoryReadable> statusHistory, IErrorCallback errorCallback);
}
