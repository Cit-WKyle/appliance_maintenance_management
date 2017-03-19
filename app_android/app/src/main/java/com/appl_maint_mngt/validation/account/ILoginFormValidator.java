package com.appl_maint_mngt.validation.account;

import com.appl_maint_mngt.forms.account.LoginForm;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface ILoginFormValidator {
    
    IValidatorResponse validate(LoginForm form);
}
