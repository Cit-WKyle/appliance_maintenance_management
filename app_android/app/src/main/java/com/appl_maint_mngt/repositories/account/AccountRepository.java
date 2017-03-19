package com.appl_maint_mngt.repositories.account;

import com.appl_maint_mngt.models.account.AAccount;
import com.appl_maint_mngt.models.account.Account;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.web.models.account.IAuthDetails;
import com.appl_maint_mngt.web.models.account.IJwtToken;
import com.appl_maint_mngt.web.models.account.IUserProfileReadable;

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
    public void updateToken(IJwtToken token) {
        accountModel.setToken(token.getToken());
        updateObservers(IAccountObserverUpdateTypes.TOKEN_UPDATE);
    }

    @Override
    public void updateAuth(IAuthDetails authDetails) {
        accountModel.setEmail(authDetails.getEmail());
        accountModel.setUserType(authDetails.getUserType());
        accountModel.setId(authDetails.getId());
        updateObservers(IAccountObserverUpdateTypes.AUTH_UPDATE);
    }

    @Override
    public void updateProfile(IUserProfileReadable userProfile) {
        accountModel.setFirstName(userProfile.getFirstName());
        accountModel.setSurname(userProfile.getSurname());
        accountModel.setDateOfBirth(userProfile.getDateOfBirth());
        updateObservers(IAccountObserverUpdateTypes.PROFILE_UPDATE);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
