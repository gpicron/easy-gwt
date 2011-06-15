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

import com.emitrom.easygwt.wf.client.column.events.ColumnCenterPanelAddViewEvent;
import com.emitrom.easygwt.wf.client.column.events.ColumnCenterPanelSelectViewEvent;
import com.emitrom.easygwt.wf.client.events.EventsBus;
import com.emitrom.easygwt.wf.client.utils.Util;
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
public class ColumnViewPort extends Viewport {

	private static ColumnViewPort columnViewPort;

	private ColumnNorthPanel northPanel;
	private static int northSize = 100;
	private ColumnEastPanel eastPanel;
	private static int eastSize = 200;
	private ColumnNavigationPanel westPanel;
	private static int westSize = 200;
	private ColumnCenterPanel centerPanel;
	private ColumnSouthPanel southPanel;
	private static int southSize = 30;
	private List<ColumnNavigationParent> navigationParents;

	public static ColumnViewPort getInstance(int nSize, int eSize, int sSize,
												int wSize) {
		northSize = nSize;
		eastSize = eSize;
		southSize = sSize;
		westSize = wSize;
		
		return getInstance();
		
	}
	
	public static ColumnViewPort getInstance() {
		
		if (columnViewPort == null) {
			columnViewPort = new ColumnViewPort();
		}
		
		return columnViewPort;
		
	}
	
	private ColumnViewPort() {
		super();
		northPanel = new ColumnNorthPanel();
		eastPanel = new ColumnEastPanel();
		southPanel = new ColumnSouthPanel();
		westPanel = new ColumnNavigationPanel();
		centerPanel = new ColumnCenterPanel();

		RootPanel.get().add(this);
		Util.getCss().columnViewCss().ensureInjected();
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		
		super.onRender(parent, index);
	
		BorderLayout borderLayout = new BorderLayout();  
	    setLayout(borderLayout); 
		
	    BorderLayoutData northPanelData = new BorderLayoutData(LayoutRegion.NORTH, northSize);
	    northPanelData.setCollapsible(true);  
	    northPanelData.setFloatable(true);  
	    northPanelData.setHideCollapseTool(true);
	    northPanelData.setMargins(new Margins(0, 0, 0, 0)); 
	    northPanelData.setCollapsible(true);

	    BorderLayoutData eastPanelData = new BorderLayoutData(LayoutRegion.EAST, eastSize);  
	    eastPanelData.setSplit(true);  
	    eastPanelData.setCollapsible(true);  
	    eastPanelData.setMargins(new Margins(0, 0, 5, 0));  

	    BorderLayoutData southPanelData = new BorderLayoutData(LayoutRegion.SOUTH, southSize);  
	    southPanelData.setMargins(new Margins(0, 0, 0, 0));

	    BorderLayoutData westPanelData = new BorderLayoutData(LayoutRegion.WEST, westSize);  
	    westPanelData.setSplit(true);  
	    westPanelData.setCollapsible(true);  
	    westPanelData.setMargins(new Margins(0, 0, 5, 0));  
	  
	    BorderLayoutData centerPanelData = new BorderLayoutData(LayoutRegion.CENTER);  
	    centerPanelData.setMargins(new Margins(5, 5, 5, 5));  
	  
	    add(northPanel, northPanelData);
	    add(eastPanel, eastPanelData);
	    add(southPanel, southPanelData);
	    add(westPanel, westPanelData);  
	    add(centerPanel, centerPanelData);  
	    
	}

	/**
	 * @return the westPanel
	 */
	public ColumnNavigationPanel getWestPanel() {
		return westPanel;
	}

	/**
	 * @return the centerPanel
	 */
	public ColumnCenterPanel getCenterPanel() {
		return centerPanel;
	}

	/**
	 * @return the northPanel
	 */
	public ColumnNorthPanel getNorthPanel() {
		return northPanel;
	}

	/**
	 * @return the eastPanel
	 */
	public ColumnEastPanel getEastPanel() {
		return eastPanel;
	}

	/**
	 * @return the southPanel
	 */
	public ColumnSouthPanel getSouthPanel() {
		return southPanel;
	}

	/**
	 * @return the navigationParents
	 */
	public List<ColumnNavigationParent> getNavigationParents() {
		return navigationParents;
	}

	/**
	 * <li> Adds a list of Navigation Parents to the West Panel (Accordion Layout) <br/>
	 * 
	 * <li> Adds all Navigation children's view to the CenterPanel (Card Layout)
	 * 
	 * @param navigationParents
	 * 
	 */
	public void addNavigationItems(List<ColumnNavigationParent> navigationParents) {
		
		this.navigationParents = navigationParents;
		
		for (ColumnNavigationParent navigationParent : navigationParents) {
			
			westPanel.add((ContentPanel) navigationParent);
			
			/**
			 * Add all Navigation Children to the Center Panel Card Layout
			 */
			ListView<ColumnNavigationChild> navigationChildren = 
				navigationParent.getApplicationNavigationItemListView();
			
			for (ColumnNavigationChild navigationChild : navigationChildren.getStore().getModels()) {
				EventsBus.getEventBus().fireEvent(
					new ColumnCenterPanelAddViewEvent(navigationChild.getNavigationView())
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

		for (final ColumnNavigationItemInterface appplicationNavigationParent : navigationParents) {
			
			final ColumnNavigationParent appplicationNavigation =  (ColumnNavigationParent) appplicationNavigationParent;
			
			List<ColumnNavigationChild> appNavChildModelList = appplicationNavigation.
																				getApplicationNavigationItemListView().
																				getStore().
																				getModels();
			
			for (final ColumnNavigationChild applicationNavigationChild : appNavChildModelList) {
				
				String navigationView = applicationNavigationChild.getNavigationView().getClass().getName();
				
				if (navigationView.equals(view)) {
					
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {
						
						@Override
						public void execute() {
							
							appplicationNavigation.expand();
							
							ListStore<ColumnNavigationChild> applicationNavigationItemStore = 
								((ColumnNavigationParent) appplicationNavigationParent).
																getApplicationNavigationItemListView().
																getStore();
							
							int indexOfListView = applicationNavigationItemStore.indexOf(applicationNavigationChild);
							
							appplicationNavigation.getApplicationNavigationItemListView().getSelectionModel().select(indexOfListView, false);
							EventsBus.getEventBus().fireEvent(
								new ColumnCenterPanelSelectViewEvent(applicationNavigationChild.getNavigationView()));

							
						}

					});
					
					break;

				}
				
			}
			
		}

	}
	
}
