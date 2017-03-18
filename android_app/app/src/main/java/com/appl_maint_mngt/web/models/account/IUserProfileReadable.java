package com.appl_maint_mngt.web.models.account;

import java.sql.Timestamp;

public interface IUserProfileReadable {

	Long getAccountId();

    String getFirstName();
	String getSurname();
	Timestamp getDateOfBirth();
}
