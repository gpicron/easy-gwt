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
package com.emitrom.easygwt.wf.client.views.accordion;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

/**
 * This Panel holds all the Navigation Parents as well as the 
 * children associated with them.
 * 
 * It uses an Accordion Layout to render its elements.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class AccordionNavigationPanel extends ContentPanel implements AccordionPanelInterface {

	private AccordionLayout navigationPanelLayout;
	
	public AccordionNavigationPanel() {
		super();
		navigationPanelLayout = new AccordionLayout();
		setLayout(navigationPanelLayout);
	}

	public AccordionLayout getNavigationPanelLayout() {
		return navigationPanelLayout;
	}

	public void setNavigationPanelLayout(AccordionLayout navigationPanelLayout) {
		this.navigationPanelLayout = navigationPanelLayout;
	}
	
}