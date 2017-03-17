package com.appl_maint_mngt.repositories.common;

import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IReadableRepositoryFactory {

    IAccountReadableRepository getReadableAccountRepository();
}
