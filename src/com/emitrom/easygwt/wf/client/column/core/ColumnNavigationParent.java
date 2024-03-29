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

import com.emitrom.easygwt.wf.client.column.events.ColumnCenterPanelSelectViewEvent;
import com.emitrom.easygwt.wf.client.events.EventsBus;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;

/**
 * Navigation Parent.
 * 
 * Takes Navigation children that will be added as part of a ListView implementation.
 * 
 * See {@link ColumnNavigationChild}
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class ColumnNavigationParent extends ContentPanel 
											implements ColumnNavigationItemInterface {

	private ListView<ColumnNavigationChild> navigationItemListView = new ListView<ColumnNavigationChild>();
	private ListStore<ColumnNavigationChild> navigationItemStore = new ListStore<ColumnNavigationChild>();
	private ColumnNavigationChild navigationDefaultSelectedItem;
	
	public ColumnNavigationParent() {
		
		super();
		setFrame(true);
		setBorders(false);
		setBodyBorder(false);
		
		createNavigationItemListView();
		
	}
	
	/**
	 * Adds a Navigation Child to the parent navigation panel.
	 * 
	 * @param navigationChild
	 */
	public void addNavigationChild(ColumnNavigationChild navigationChild) {
		
		/**
		 * We use the class name from the ApplicationView
		 * contained in ApplicationNavigationChild to determine the views
		 * shown to the user in a dynamic fashion.
		 */
		navigationItemStore.add(navigationChild);
		
		/**
		 * We default to always select the first item from the ListView
		 */
		if (navigationDefaultSelectedItem == null) {
			navigationDefaultSelectedItem = navigationChild;
			navigationItemListView.getSelectionModel().select(navigationChild, false);
		}

	}

	public ListView<ColumnNavigationChild> getApplicationNavigationItemListView() {
		return navigationItemListView;
	}

	@SuppressWarnings("rawtypes")
	private void createNavigationItemListView() {
		
		navigationItemListView.setStore(navigationItemStore);
		navigationItemListView.setStyleName("wf-navigation-item");
		navigationItemListView.setDisplayProperty("name");
		navigationItemListView.setBorders(false);
		navigationItemListView.setStyleAttribute("padding", "0 0 0 5px");
		
		/**
		 * Select Event
		 */
		navigationItemListView.addListener(Events.Select, new Listener<ListViewEvent>() {

			@Override
			public void handleEvent(ListViewEvent event) {
				
				/**
				 * Fire CenterPanelSelectViewEvent
				 */
				ColumnNavigationChild applicationNavigationClickedItem = (ColumnNavigationChild) event.getModel();

				EventsBus.getEventBus().fireEvent(
					new ColumnCenterPanelSelectViewEvent(((ColumnView) applicationNavigationClickedItem.getNavigationView()))
				);
				
			}
			
		});
		
		/**
		 * Content Panel Event (Accordion Panels)
		 */
		this.addListener(Events.Expand, new Listener<ComponentEvent>() {

			@Override
			public void handleEvent(ComponentEvent event) {
				
				/**
				 * Fire CenterPanelSelectViewEvent if there is a selected item. This will
				 * make the view visible in the Center Panel. 
				 */
				if (navigationItemListView.getSelectionModel().getSelectedItem() != null) {
					
					ColumnNavigationChild applicationNavigationSelectedItem = 
						(ColumnNavigationChild) navigationItemListView.getSelectionModel().getSelectedItem();
					
					EventsBus.getEventBus().fireEvent(
						new ColumnCenterPanelSelectViewEvent(applicationNavigationSelectedItem.getNavigationView())
					);
				
				/**
				 * If no item is selected we always default to the first one of the ListView, firing
				 * an event to have the CenterPanel display the associated view.
				 */
				} else {
					
					navigationItemListView.getSelectionModel().select(0, false);
					
					ColumnNavigationChild applicationNavigationFirstItem = 						
						(ColumnNavigationChild) navigationItemListView.getStore().getAt(0);
					
					EventsBus.getEventBus().fireEvent(
						new ColumnCenterPanelSelectViewEvent(applicationNavigationFirstItem.getNavigationView())
					);

				}
				
			}
			
		});
		
		add(navigationItemListView);
		
	}
	
}