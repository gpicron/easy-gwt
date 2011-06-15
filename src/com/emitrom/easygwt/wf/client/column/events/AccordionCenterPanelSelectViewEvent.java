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
package com.emitrom.easygwt.wf.client.column.events;

import com.emitrom.easygwt.wf.client.column.views.AccordionViewInterface;
import com.emitrom.easygwt.wf.client.events.EventsHandler;
import com.emitrom.easygwt.wf.client.events.EventsType;
import com.google.gwt.event.shared.EventHandler;

/**
 * Center Panel Select Event.
 * 
 * Triggers a selection of the view in the Card Layout for the Center Panel.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class AccordionCenterPanelSelectViewEvent extends EventsHandler<AccordionCenterPanelSelectViewEvent.Handler,
													AccordionCenterPanelSelectViewEvent.Type> {

	private AccordionViewInterface viewItem;
	
	public interface Handler extends EventHandler {
		void onSelectViewEvent(AccordionCenterPanelSelectViewEvent event);
	}

	public static Type TYPE = new Type();
	
	public AccordionCenterPanelSelectViewEvent(AccordionViewInterface viewItem) {
		super(TYPE);
		this.viewItem = viewItem;
	}

	public AccordionViewInterface getViewItem() {
		return viewItem;
	}

	protected static class Type extends EventsType<AccordionCenterPanelSelectViewEvent.Handler> {}

	@Override
	protected void dispatch(AccordionCenterPanelSelectViewEvent.Handler handler) {
		handler.onSelectViewEvent(this);
	}

}
