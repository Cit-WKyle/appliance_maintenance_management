package com.appl_maint_mngt.models.account.constants;

import com.google.gson.annotations.SerializedName;

public enum UserType {
    @SerializedName("PROPERTY_MANAGER")
    PROPERTY_MANAGER,

    @SerializedName("PROPERTY_TENANT")
    PROPERTY_TENANT,

    @SerializedName("MAINTENANCE_ENGINEER")
    MAINTENANCE_ENGINEER
}