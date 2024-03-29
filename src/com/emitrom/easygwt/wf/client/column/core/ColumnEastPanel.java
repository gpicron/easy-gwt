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

import com.extjs.gxt.ui.client.widget.ContentPanel;

/**
 * South Panel for EasyGWT.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class ColumnEastPanel extends ContentPanel implements ColumnPanelInterface {

	public ColumnEastPanel() {
		super();
		setBodyBorder(false);
		setFrame(true);
		setVisible(false);
	}
	
}
