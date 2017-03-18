package com.appl_maint_mngt.models.status_history;

import java.sql.Timestamp;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IStatusHistoryReadable {

    Long getStatusId();
    Timestamp getLoggedTime();
}
