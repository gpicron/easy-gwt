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

import java.util.HashSet;
import java.util.Set;

import com.emitrom.easygwt.wf.client.column.events.ColumnCenterPanelAddViewEvent;
import com.emitrom.easygwt.wf.client.column.events.ColumnCenterPanelSelectViewEvent;
import com.emitrom.easygwt.wf.client.events.EventsBus;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

/**
 * This is the Center Panel that holds all the views.
 * It uses a card layout swapping views based on events.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class ColumnCenterPanel extends ContentPanel implements ColumnPanelInterface {

	private CardLayout centerCardLayout;
	private	ColumnViewInterface selectedViewItem;
	private boolean defaultViewRendered = false;
	private Set<ColumnViewInterface> renderedViews;
	
	public ColumnCenterPanel() {
		
		super();
		setScrollMode(Scroll.AUTOX);
		centerCardLayout = new CardLayout();
		setLayout(centerCardLayout);
		renderedViews = new HashSet<ColumnViewInterface>();
		setStyleName("wf-navigation-x-panel-header-text");
		
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		addHandlers();
	}
	
	private void addHandlers() {
	   
		/**
		 * CenterPanelAddViewEvent
		 */
		EventsBus.getEventBus().addHandler(ColumnCenterPanelAddViewEvent.TYPE, 
			   new ColumnCenterPanelAddViewEvent.Handler() {

			@Override
			public void onAddViewEvent(ColumnCenterPanelAddViewEvent event) {
				
				add((LayoutContainer) event.getViewItem());
				
				if (!defaultViewRendered) {
					setHeading(((ColumnView) event.getViewItem()).getHeading());
					renderedViews.add((ColumnViewInterface) event.getViewItem());
					((ColumnViewInterface) event.getViewItem()).onRender();
					defaultViewRendered = true;
				}
				
			}
		   
	   });
		
		/**
		 * CenterPanelSelectViewEvent
		 */
		EventsBus.getEventBus().addHandler(ColumnCenterPanelSelectViewEvent.TYPE, 
				new ColumnCenterPanelSelectViewEvent.Handler() {
					
			@Override
			public void onSelectViewEvent(ColumnCenterPanelSelectViewEvent event) {

				/**
				 * Call prepareToHideView
				 */
				if (selectedViewItem != null) {
					((ColumnViewInterface) selectedViewItem).prepareToHideView();
				}
				
				/**
				 * Call prepareToShowView
				 */
				((ColumnViewInterface) event.getViewItem()).prepareToShowView();
				
				/**
				 * Show the view and set the selected view item
				 */
				LayoutContainer viewItem = (LayoutContainer) event.getViewItem();
				centerCardLayout.setActiveItem(viewItem);
				setHeading(((ColumnView) event.getViewItem()).getHeading());
				selectedViewItem = (ColumnViewInterface) viewItem;
				
				/**
				 * Call onRender only once
				 */
				if (!renderedViews.contains(selectedViewItem)) {
					renderedViews.add((ColumnViewInterface) selectedViewItem);
					((ColumnViewInterface) selectedViewItem).onRender();
					viewItem.layout();
				}

			}
			
		});
		
   }
	
}
