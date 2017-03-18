package com.appl_maint_mngt.web.models.account;

/**
 * Created by Kyle on 17/03/2017.
 */

public class JwtToken implements IJwtToken {

    private String token;

    @Override
    public String getToken() {
        return token;
    }
}
