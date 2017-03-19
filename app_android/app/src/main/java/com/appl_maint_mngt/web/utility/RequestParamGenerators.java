package com.appl_maint_mngt.web.utility;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public class RequestParamGenerators {

    private static final char EQUALS_CHAR = '=';
    private static final char AND_CHAR = '&';
    private static final char QMARK_CHAR = '?';

    public static String generateRequestParamsForIds(List<Long> ids, String arrayName) {
        StringBuilder builder = new StringBuilder();
        builder.append(QMARK_CHAR);
        for(Long id: ids) {
            builder.append(arrayName);
            builder.append(EQUALS_CHAR);
            builder.append(id);
            builder.append(AND_CHAR);
        }
        return builder.toString();
    }
}
