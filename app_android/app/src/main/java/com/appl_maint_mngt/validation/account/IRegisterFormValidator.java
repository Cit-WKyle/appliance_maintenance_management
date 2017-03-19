package com.appl_maint_mngt.validation.account;

import com.appl_maint_mngt.forms.account.RegisterForm;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IRegisterFormValidator {

    IValidatorResponse validate(RegisterForm form);
}
