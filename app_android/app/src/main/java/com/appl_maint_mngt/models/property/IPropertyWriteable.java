package com.appl_maint_mngt.models.property;

import com.appl_maint_mngt.models.address.Address;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyWriteable {
    void setId(Long id);
    void setAddress(Address address);
    void setAge(int age);
    void setBedCount(int bedCount);
    void setBathroomCount(int bathroomCount);
}
