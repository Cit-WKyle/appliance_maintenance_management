package com.appl_maint_mngt.models.property;

import com.appl_maint_mngt.models.address.IAddressReadable;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyReadable {
    Long getId();
    public IAddressReadable getAddress();
    public int getAge();
    public int getBedCount();
    public int getBathroomCount();
}
