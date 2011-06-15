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

import java.util.List;

import com.emitrom.easygwt.wf.client.column.events.AccordionCenterPanelAddViewEvent;
import com.emitrom.easygwt.wf.client.column.events.AccordionCenterPanelSelectViewEvent;
import com.emitrom.easygwt.wf.client.events.EventsBus;
import com.emitrom.easygwt.wf.utils.Util;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This is the ViewPort for EasyGWT.
 * 
 * An instance of this class gives you access to the entire framework behind EasyGWT.
 * 
 * Holds and adds all Panel elements to GWT's RootPanel.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class AccordionViewPort extends Viewport {

	private static AccordionViewPort accordionViewPort;
	private AccordionNavigationPanel westPanel;
	private AccordionCenterPanel centerPanel;
	private AccordionNorthPanel northPanel;
	private AccordionSouthPanel southPanel;
	private List<AccordionNavigationItemInterface> navigationItems;
	
	public static AccordionViewPort getInstance() {
		
		if (accordionViewPort == null) {
			accordionViewPort = new AccordionViewPort();
		}
		
		return accordionViewPort;
		
	}
	
	private AccordionViewPort() {
		super();
		northPanel = new AccordionNorthPanel();
		westPanel = new AccordionNavigationPanel();
		centerPanel = new AccordionCenterPanel();
		southPanel = new AccordionSouthPanel();

		RootPanel.get().add(this);
		Util.getCss().accordionViewCss().ensureInjected();
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		
		super.onRender(parent, index);
	
		BorderLayout borderLayout = new BorderLayout();  
	    setLayout(borderLayout); 
		
	    BorderLayoutData northPanelData = new BorderLayoutData(LayoutRegion.NORTH, 100);  
	    northPanelData.setCollapsible(true);  
	    northPanelData.setFloatable(true);  
	    northPanelData.setHideCollapseTool(true);
	    northPanelData.setMargins(new Margins(0, 0, 0, 0)); 
	    northPanelData.setCollapsible(true);
	  
	    BorderLayoutData westPanelData = new BorderLayoutData(LayoutRegion.WEST, 200);  
	    westPanelData.setSplit(true);  
	    westPanelData.setCollapsible(true);  
	    westPanelData.setMargins(new Margins(0, 0, 5, 0));  
	  
	    BorderLayoutData centerPanelData = new BorderLayoutData(LayoutRegion.CENTER);  
	    centerPanelData.setMargins(new Margins(5, 5, 5, 5));  
	  
	    BorderLayoutData southPanelData = new BorderLayoutData(LayoutRegion.SOUTH, 30);  
	    southPanelData.setMargins(new Margins(0, 0, 0, 0));
	    
	    add(northPanel, northPanelData);  
	    add(westPanel, westPanelData);  
	    add(centerPanel, centerPanelData);  
	    add(southPanel, southPanelData);
	    
	}

	/**
	 * @return the westPanel
	 */
	public AccordionNavigationPanel getWestPanel() {
		return westPanel;
	}

	/**
	 * @return the centerPanel
	 */
	public AccordionCenterPanel getCenterPanel() {
		return centerPanel;
	}

	/**
	 * @return the northPanel
	 */
	public AccordionNorthPanel getNorthPanel() {
		return northPanel;
	}

	/**
	 * @return the southPanel
	 */
	public AccordionSouthPanel getSouthPanel() {
		return southPanel;
	}

	/**
	 * @return the navigationItems
	 */
	public List<AccordionNavigationItemInterface> getNavigationItems() {
		return navigationItems;
	}

	/**
	 * <li> Adds a list of Navigation Items to the West Panel (Accordion Layout) <br/>
	 * 
	 * <li> Adds all Navigation children's view to the CenterPanel (Card Layout)
	 * 
	 * @param applicationNavigationItems
	 * 
	 */
	public void addNavigationItems(List<AccordionNavigationItemInterface> applicationNavigationItems) {
		
		this.navigationItems = applicationNavigationItems;
		
		for (AccordionNavigationItemInterface applicationNavigationItem : applicationNavigationItems) {
			
			westPanel.add((ContentPanel) applicationNavigationItem);
			
			/**
			 * Add all Navigation Children to the Center Panel Card Layout
			 */
			ListView<AccordionNavigationChild> navigationChildren = 
				((AccordionNavigationParent) applicationNavigationItem).getApplicationNavigationItemListView();
			
			for (AccordionNavigationChild navigationChild : navigationChildren.getStore().getModels()) {
				EventsBus.getEventBus().fireEvent(
					new AccordionCenterPanelAddViewEvent(navigationChild.getNavigationView())
				);
			}
			
		}
		
		layout();
		
	}
	
	/**
	 * Used to force a view to be the first one to be seen and not use the default
	 * in the card layout which is the first member of the first navigation parent.
	 * 
	 * @param view
	 */
	public void selectView(String view) {

		for (final AccordionNavigationItemInterface appplicationNavigationParent : navigationItems) {
			
			final AccordionNavigationParent appplicationNavigation =  (AccordionNavigationParent) appplicationNavigationParent;
			
			List<AccordionNavigationChild> appNavChildModelList = appplicationNavigation.
																				getApplicationNavigationItemListView().
																				getStore().
																				getModels();
			
			for (final AccordionNavigationChild applicationNavigationChild : appNavChildModelList) {
				
				String navigationView = applicationNavigationChild.getNavigationView().getClass().getName();
				
				if (navigationView.equals(view)) {
					
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {
						
						@Override
						public void execute() {
							
							appplicationNavigation.expand();
							
							ListStore<AccordionNavigationChild> applicationNavigationItemStore = 
								((AccordionNavigationParent) appplicationNavigationParent).
																getApplicationNavigationItemListView().
																getStore();
							
							int indexOfListView = applicationNavigationItemStore.indexOf(applicationNavigationChild);
							
							appplicationNavigation.getApplicationNavigationItemListView().getSelectionModel().select(indexOfListView, false);
							EventsBus.getEventBus().fireEvent(
								new AccordionCenterPanelSelectViewEvent(applicationNavigationChild.getNavigationView()));

							
						}

					});
					
					break;

				}
				
			}
			
		}

	}
	
}
