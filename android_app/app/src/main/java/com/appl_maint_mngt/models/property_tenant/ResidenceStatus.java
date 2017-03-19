package com.appl_maint_mngt.models.property_tenant;

import com.google.gson.annotations.SerializedName;

public enum ResidenceStatus {

	@SerializedName("OCCUPANT")
	OCCUPANT,

	@SerializedName("PENDING")
	PENDING,

	@SerializedName("NO_RESIDENCE")
	NO_RESIDENCE
}
