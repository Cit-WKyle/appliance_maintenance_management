package com.appl_maint_mngt.validation.account;

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
    @Override
    public IValidatorResponse validate(RegisterForm form) {
        List<String> errors = new ArrayList<>();

        if(form.getUserType() == null) errors.add("");
        else if(!validateUserType(form.getUserType())) errors.add("");

        if(form.getDateOfBirth() == null) errors.add("");
        else if(!validateDateOfBirth(form.getDateOfBirth())) errors.add("");

        if(form.getPassword().length() < IAccountValidationConstants.PASSWORD_MIN) errors.add("");
        if(form.getPassword().length() > IAccountValidationConstants.PASSWORD_MAX) errors.add("");
        if(form.getEmail().matches(IAccountValidationConstants.EMAIL_REGEX)) errors.add("");
        if(form.getFirstName().length() < IAccountValidationConstants.FIRSTNAME_MIN) errors.add("");
        if(form.getFirstName().length() > IAccountValidationConstants.FIRSTNAME_MAX) errors.add("");
        if(form.getSurname().length() < IAccountValidationConstants.SURNAME_MIN) errors.add("");
        if(form.getSurname().length() > IAccountValidationConstants.SURNAME_MAX) errors.add("");

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
