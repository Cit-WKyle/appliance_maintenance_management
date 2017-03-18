package com.appl_maint_mngt.repositories.account;

import com.appl_maint_mngt.models.account.AAccount;
import com.appl_maint_mngt.web.models.account.IAuthDetails;
import com.appl_maint_mngt.web.models.account.IJwtToken;
import com.appl_maint_mngt.web.models.account.IUserProfileReadable;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IAccountUpdateableRepository {
    void update(AAccount account);

    void updateToken(IJwtToken token);

    void updateAuth(IAuthDetails authDetails);

    void updateProfile(IUserProfileReadable userProfile);

}
