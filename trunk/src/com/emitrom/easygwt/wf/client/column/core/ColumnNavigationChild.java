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
package com.emitrom.easygwt.wf.client.column.core;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * Navigation child.
 * 
 * Create instances of this class to be added to a Navigation Parent
 * in the Navigation Panel.
 * 
 * See {@link ColumnNavigationParent}
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class ColumnNavigationChild extends BaseModelData 
										implements ColumnNavigationItemInterface { 
	
	private static final long serialVersionUID = 3397212364839524276L;
	private ColumnViewInterface navigationView;
	private ImageResource icon;
	private String heading;

	public ColumnNavigationChild(ColumnViewInterface navigationView,
		String heading) {
		
		super();
		set("name", heading);
		((ColumnView) navigationView).setHeading(heading);
		this.navigationView = navigationView;
		this.heading = heading;
		
	}

	public ColumnViewInterface getNavigationView() {
		return navigationView;
	}
	
	/**
	 * @return the icon
	 */
	public ImageResource getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageResource icon) {
		set("name", AbstractImagePrototype.create(icon).getHTML() + heading);
		((ColumnView) navigationView).setHeading(AbstractImagePrototype.create(icon).getHTML() + heading);
		this.icon = icon;
	}

	/**
	 * @return the heading
	 */
	public String getHeading() {
		if (icon != null) {
			return AbstractImagePrototype.create(icon).getHTML() + heading;
		}
		return heading;
	}

	/**
	 * @param heading the heading to set
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

}

