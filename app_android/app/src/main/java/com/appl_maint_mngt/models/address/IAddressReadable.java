package com.appl_maint_mngt.models.address;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IAddressReadable {
    public String getAddressLine1();
    public String getAddressLine2();
    public String getCity();
    public String getState();
    public String getCountry();
    public String getPostalCode();
    public float getLongitude();
    public float getLatitude();
}
