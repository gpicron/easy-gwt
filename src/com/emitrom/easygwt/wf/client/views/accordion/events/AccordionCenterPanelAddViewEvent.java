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
package com.emitrom.easygwt.wf.client.views.accordion.events;

import com.emitrom.easygwt.wf.client.events.EventsHandler;
import com.emitrom.easygwt.wf.client.events.EventsType;
import com.emitrom.easygwt.wf.client.views.column.AccordionViewInterface;
import com.google.gwt.event.shared.EventHandler;

/**
 * Center Panel Add Event.
 * 
 * The Center Panel handler will handle this event and add the view to the card layout for the Center Panel.
 * 
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class AccordionCenterPanelAddViewEvent extends EventsHandler<AccordionCenterPanelAddViewEvent.Handler,
												AccordionCenterPanelAddViewEvent.Type> {

	private AccordionViewInterface viewItem;
	
	public interface Handler extends EventHandler {
		void onAddViewEvent(AccordionCenterPanelAddViewEvent event);
	}

	public static Type TYPE = new Type();
	
	public AccordionCenterPanelAddViewEvent(AccordionViewInterface viewItem) {
		super(TYPE);
		this.viewItem = viewItem;
	}

	public AccordionViewInterface getViewItem() {
		return viewItem;
	}

	protected static class Type extends EventsType<AccordionCenterPanelAddViewEvent.Handler> {}

	@Override
	protected void dispatch(AccordionCenterPanelAddViewEvent.Handler handler) {
		handler.onAddViewEvent(this);
	}
	
}
