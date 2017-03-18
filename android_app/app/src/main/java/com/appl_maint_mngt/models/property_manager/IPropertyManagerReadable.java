package com.appl_maint_mngt.models.property_manager;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyManagerReadable {
    List<Long> getCurrentPropertyIds();

    List<Long> getPreviousPropertyIds();

}
