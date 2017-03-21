package com.appl_maint_mngt.validation.account;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.forms.account.LoginForm;
import com.appl_maint_mngt.validation.common.GenericValidatorResponse;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 15/03/2017.
 */
public class LoginFormValidator implements ILoginFormValidator {

    private Context cntxt;

    public LoginFormValidator() {
        cntxt = MainActivity.getInstance();
    }


    @Override
    public IValidatorResponse validate(LoginForm form) {
        List<String> errors = new ArrayList<>();

        if(!form.getEmail().matches(IAccountValidationConstants.EMAIL_REGEX)) errors.add(cntxt.getString(R.string.validation_account_email_valid));
        if(form.getPassword().length() < IAccountValidationConstants.PASSWORD_MIN) errors.add(cntxt.getString(R.string.validation_account_password_min, IAccountValidationConstants.PASSWORD_MIN));
        if(form.getPassword().length() > IAccountValidationConstants.PASSWORD_MAX) errors.add(cntxt.getString(R.string.validation_account_password_max, IAccountValidationConstants.PASSWORD_MAX));

        return new GenericValidatorResponse(errors.size() == 0, errors);
    }


}
