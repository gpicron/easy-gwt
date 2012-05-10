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
package com.emitrom.easygwt.wf.client.authentication;

import com.emitrom.easygwt.wf.client.widgets.dialogs.LoginDialog;

/**
 * 
 * Interface used by any implementations used
 * in conjunction with LoginDialog
 *
 *  An implementation of Authenticator can use the LoginDialog and upon
 *  success login then call the onSuccess method of the Authenticator's implementation.
 *   
 * @See {@link LoginDialog}
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public interface Authenticator {

	public void login();
	public void logout();
	public void onSuccess();
	
}
