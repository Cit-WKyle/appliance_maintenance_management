package com.appl_maint_mngt.common.callbacks.error;

import java.util.List;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ErrorPayload {

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
