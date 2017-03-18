package com.appl_maint_mngt.models.appliance;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceReadable {

    String getId();
    String getProductNo();
    String getName();
    String getBrand();
    ApplianceType getType();
}
