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
package com.emitrom.easygwt.wf.client.events;

import com.google.gwt.event.shared.SimpleEventBus;

/**
 * Main Event Bus to be used by the user interface.
 * 
 * Provides a single instance that can be used by the user interface implemenation
 * to fire and handle events.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class EventsBus extends SimpleEventBus {

	private static SimpleEventBus eventBus;
	
	public static SimpleEventBus getEventBus() {
		
		if (eventBus == null) {
			eventBus = new SimpleEventBus();
		}
		
		return eventBus;
		
	}

}
