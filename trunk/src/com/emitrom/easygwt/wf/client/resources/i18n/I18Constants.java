/*******************************************************************************
 * This file is part of EasyGWT 
 * 
 * Copyright (c) 2011 Emitrom LLC
 * All rights reserved. 
 * 
 * EasyGWT is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU   General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 ******************************************************************************/
package com.emitrom.easygwt.wf.client.resources.i18n;

import com.google.gwt.i18n.client.Constants;

/**
 * 
 * Contains all the i18n definitions
 * for the entire framework.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public interface I18Constants extends Constants {

    @DefaultStringValue("Administration")
	String administrationNavigationHeading();

    @DefaultStringValue("Sign Up")
	String signupButtonHeading();

    @DefaultStringValue("Forgot Password")
	String forgotPasswordButtonHeading();
    
	@DefaultStringValue("Login")
	String loginDialogHeading();

	@DefaultStringValue("Logout")
	String logoutButtonToolTip();
	
	@DefaultStringValue("Login")
	String loginButtonHeading();

	@DefaultStringValue("Reset")
	String resetButtonHeading();
	
	@DefaultStringValue("<b>Invalid email address or password!</b>")
	String loginFailureMessageLabelField();

	@DefaultStringValue("Login")
	String loginProgressBarTitle();

	@DefaultStringValue("Validating Credentials...")
	String loginProgressBarProgressText();
	
	@DefaultStringValue("Email")
	String usernameTextFieldFieldLabel();
	
	@DefaultStringValue("Username must be an email address")
	String usernameTextFieldValidationMessage();
	
	@DefaultStringValue("Password")
	String passwordTextFieldFieldLabel();

	@DefaultStringValue("Style")
	String styleMenuItemHeading();

	@DefaultStringValue("Blue")
	String styleBlueHeader();
	
	@DefaultStringValue("Gray")
	String styleGrayHeader();

	@DefaultStringValue("Slate")
	String styleSlateHeader();

	@DefaultStringValue("Access")
	String styleAccessHeader();
	
	@DefaultStringValue("< Back")
	String previousButtonHeading();
	
	@DefaultStringValue("Next >")
	String nextButtonHeading();
	
	@DefaultStringValue("Finish")
	String finishButtonHeading();
	
	@DefaultStringValue("Cancel")
	String cancelButtonHeading();
}
