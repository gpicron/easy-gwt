package com.emitrom.easygwt.client.resources.I18N;

import com.google.gwt.i18n.client.Constants;

public interface SampleConstants extends Constants {

	@DefaultStringValue("Users View")
	String usersViewHeading();
	
	@DefaultStringValue("First Name")
	String usersGridFirstNameColumnHeader();

	@DefaultStringValue("Last Name")
	String usersGridLastNameColumnHeader();

	@DefaultStringValue("Email")
	String usersGridEmailColumnHeader();

	@DefaultStringValue("Add")
	String usersGridAddButtonToolTip();

	@DefaultStringValue("Delete")
	String usersGridDeleteButtonToolTip();

	@DefaultStringValue("Username")
	String usersGridUserNameColumnHeader();

}
