package com.appl_maint_mngt.repositories.account;

import com.appl_maint_mngt.models.account.AAccount;
import com.appl_maint_mngt.models.account.IAccountWriteable;
import com.appl_maint_mngt.web.models.account.IAuthDetails;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountUpdateableRepository {
    void update(AAccount account);

    void updateAuth(IAuthDetails authDetails);

}
