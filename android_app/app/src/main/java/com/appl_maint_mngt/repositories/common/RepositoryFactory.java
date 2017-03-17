package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.AAccountRepository;
import com.appl_maint_mngt.repositories.account.AccountRepository;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.account.IAccountUpdateableRepository;

import java.util.Observer;

/**
 * Created by Kyle on 15/03/2017.
 */
public class RepositoryFactory implements IReadableRepositoryFactory, IUpdateableRepositoryFactory, IObserveRepositoriesHandler {
    private static RepositoryFactory ourInstance = new RepositoryFactory();

    private AAccountRepository accountRepository;

    public static RepositoryFactory getInstance() {
        return ourInstance;
    }

    private RepositoryFactory() {
    }

    @Override
    public IAccountReadableRepository getReadableAccountRepository() {
        if(accountRepository == null)accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public IAccountUpdateableRepository getUpdateableAccountRepository() {
        if(accountRepository == null)accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public void observeAccountRepository(Observer obs) {
        if(accountRepository == null)accountRepository = new AccountRepository();
        accountRepository.addObserver(obs);
    }
}
