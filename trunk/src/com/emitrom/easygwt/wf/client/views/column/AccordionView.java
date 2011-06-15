/*******************************************************************************
 * This file is part of EasyGWT 
 * 
 * Copyright (c) 2010 Emitrom LLC
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
package com.emitrom.easygwt.wf.client.views.column;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

/**
 * Abstract implementation of the AccordionViewInterface.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public abstract class AccordionView extends LayoutContainer implements AccordionViewInterface {

	private static final long serialVersionUID = 2464515645533609874L;
	private String heading;
	
	@Override
	public void prepareToHideView() {}

	@Override
	public void prepareToShowView() {}
	
	public String getHeading() {
		return heading;
	}
	
	public void setHeading(String heading) {
		this.heading = heading;
	}
	
}
