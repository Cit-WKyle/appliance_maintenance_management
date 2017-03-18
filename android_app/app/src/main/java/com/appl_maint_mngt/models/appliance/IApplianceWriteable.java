package com.appl_maint_mngt.models.appliance;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceWriteable {

    void setId(String id);
    void setProductNo(String productNo);
    void setName(String name);
    void setBrand(String brand);
    void setType(ApplianceType type);
}
