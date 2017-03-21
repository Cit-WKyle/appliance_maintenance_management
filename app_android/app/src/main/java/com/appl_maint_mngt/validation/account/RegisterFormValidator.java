package com.appl_maint_mngt.validation.account;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.forms.account.RegisterForm;
import com.appl_maint_mngt.models.account.constants.UserType;
import com.appl_maint_mngt.validation.common.GenericValidatorResponse;
import com.appl_maint_mngt.validation.common.IValidatorResponse;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */
public class RegisterFormValidator implements IRegisterFormValidator {

    private Context cntxt;

    public RegisterFormValidator() {
        cntxt = MainActivity.getInstance();
    }

    @Override
    public IValidatorResponse validate(RegisterForm form) {
        List<String> errors = new ArrayList<>();

        if(form.getUserType() == null) errors.add(cntxt.getString(R.string.validation_account_reg_type_null));
        else if(!validateUserType(form.getUserType())) errors.add(cntxt.getString(R.string.validation_account_reg_type_invalid));

        if(form.getDateOfBirth() == null) errors.add(cntxt.getString(R.string.validation_account_reg_dob_null));
        else if(!validateDateOfBirth(form.getDateOfBirth())) errors.add(cntxt.getString(R.string.validation_account_reg_dob_valid));

        if(form.getPassword().length() < IAccountValidationConstants.PASSWORD_MIN) errors.add(cntxt.getString(R.string.validation_account_password_min, IAccountValidationConstants.PASSWORD_MIN));
        if(form.getPassword().length() > IAccountValidationConstants.PASSWORD_MAX) errors.add(cntxt.getString(R.string.validation_account_password_max, IAccountValidationConstants.PASSWORD_MAX));
        if(!form.getEmail().matches(IAccountValidationConstants.EMAIL_REGEX)) errors.add(cntxt.getString(R.string.validation_account_email_valid));
        if(form.getFirstName().length() < IAccountValidationConstants.FIRSTNAME_MIN) errors.add(cntxt.getString(R.string.validation_account_reg_firstname_min,IAccountValidationConstants.FIRSTNAME_MIN));
        if(form.getFirstName().length() > IAccountValidationConstants.FIRSTNAME_MAX) errors.add(cntxt.getString(R.string.validation_account_reg_firstname_max,IAccountValidationConstants.FIRSTNAME_MAX));
        if(form.getSurname().length() < IAccountValidationConstants.SURNAME_MIN) errors.add(cntxt.getString(R.string.validation_account_reg_surname_min,IAccountValidationConstants.SURNAME_MIN));
        if(form.getSurname().length() > IAccountValidationConstants.SURNAME_MAX) errors.add(cntxt.getString(R.string.validation_account_reg_surname_max,IAccountValidationConstants.SURNAME_MAX));

        return new GenericValidatorResponse(errors.size() == 0, errors);
    }

    private boolean validateDateOfBirth(Timestamp value) {
        DateTime min = new DateTime().withYear(IAccountValidationConstants.MIN_YEAR);
        DateTime max = new DateTime().withYear(IAccountValidationConstants.MAX_YEAR);
        long minMillis = min.getMillis();
        long maxMillis = max.getMillis();
        long dobMillis = new DateTime(value).getMillis();
        return (dobMillis >= minMillis && dobMillis <= maxMillis);
    }

    private boolean validateUserType(String userType) {
        try {
            UserType.valueOf(userType);
            return true;
        } catch(IllegalArgumentException iAE ) {
            return false;
        }
    }
}
