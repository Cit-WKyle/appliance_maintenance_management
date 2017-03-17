package com.appl_maint_mngt.repositories.account;

import com.appl_maint_mngt.models.account.AAccount;
import com.appl_maint_mngt.models.account.Account;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.web.models.account.IAuthDetails;

/**
 * Created by Kyle on 15/03/2017.
 */
public class AccountRepository extends AAccountRepository {

    private AAccount accountModel;

    public AccountRepository() {
        accountModel = new Account();
    }

    @Override
    public IAccountReadable get() {
        return accountModel;
    }

    @Override
    public void update(AAccount account) {
        accountModel = account;
    }

    @Override
    public void updateAuth(IAuthDetails authDetails) {
        accountModel.setEmail(authDetails.getEmail());
        accountModel.setUserType(authDetails.getUserType());
        accountModel.setId(authDetails.getId());
        updateObservers(IAccountObserverUpdateTypes.AUTH_UPDATE);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
