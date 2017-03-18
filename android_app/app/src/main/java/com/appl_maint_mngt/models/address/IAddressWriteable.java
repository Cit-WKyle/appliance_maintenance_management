package com.appl_maint_mngt.models.address;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IAddressWriteable {

    public void setAddressLine1(String addressLine1);
    public void setAddressLine2(String addressLine2);
    public void setCity(String city);
    public void setState(String state);
    public void setCountry(String country);
    public void setPostalCode(String postalCode);
    public void setLongitude(float longitude);
    public void setLatitude(float latitude);
}
