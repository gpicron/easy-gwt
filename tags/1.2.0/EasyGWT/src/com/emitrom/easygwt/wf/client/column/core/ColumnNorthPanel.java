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

import com.emitrom.easygwt.wf.client.utils.Util;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.state.StateManager;
import com.extjs.gxt.ui.client.util.CSS;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * This is the North Panel for EasyGWT.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class ColumnNorthPanel extends ContentPanel implements ColumnPanelInterface {

	private ToolBar northPanelToolBar;
	private Button settingsButton;
	private Menu settingsMenu;
	private CheckMenuItem blueStyle;
	private CheckMenuItem grayStyle;
	private CheckMenuItem slateStyle;
	private CheckMenuItem accessStyle;
	
	private static final String STYLE = "style";
	private static final String GXT_GRAY_CSS_ID = "gxt-gray";
	private static final String GXT_GRAY_CSS_PATH = "resources/gxt/css/gxt-gray.css";
	private static final String GXT_SLATE_CSS_PATH = "resources/gxt/themes/slate/css/xtheme-slate.css";
	private static final String GXT_SLATE_CSS_ID = "gxt-slate";
	private static final String GXT_ACCESS_CSS_PATH = "resources/gxt/themes/access/css/xtheme-access.css";
	private static final String GXT_ACCESS_CSS_ID = "gxt-access";
	
	public ColumnNorthPanel() {
		
		super();
		setHeaderVisible(false);
		setBodyStyle("background-color: black;");
		setBodyBorder(false);
		northPanelToolBar = new ToolBar();
		
		settingsButton = new Button();
		settingsButton.setIcon(AbstractImagePrototype.create(Util.getIcons().settings()));
		
		settingsMenu = new Menu();
		
		MenuItem styleMenuItem = new MenuItem(Util.getConstants().styleMenuItemHeading());
		settingsMenu.add(styleMenuItem);
		
		Menu styleMenu = new Menu();
		
		blueStyle = new CheckMenuItem(Util.getConstants().styleBlueHeader());
		blueStyle.setHideOnClick(false);
		blueStyle.setGroup(STYLE);
		
		grayStyle = new CheckMenuItem(Util.getConstants().styleGrayHeader());
		grayStyle.setHideOnClick(false);
		grayStyle.setGroup(STYLE);
		
		slateStyle = new CheckMenuItem(Util.getConstants().styleSlateHeader());
		slateStyle.setHideOnClick(false);
		slateStyle.setGroup(STYLE);

		accessStyle = new CheckMenuItem(Util.getConstants().styleAccessHeader());
		accessStyle.setHideOnClick(false);
		accessStyle.setGroup(STYLE);
		
		/**
		 * Style the application
		 */
		if (StateManager.get().get(GXT_GRAY_CSS_ID) != null && 
				(Boolean) StateManager.get().get(GXT_GRAY_CSS_ID)) {
			CSS.addStyleSheet(GXT_GRAY_CSS_ID, GXT_GRAY_CSS_PATH);
			grayStyle.setChecked(true);
		} else if (StateManager.get().get(GXT_ACCESS_CSS_ID) != null && 
				(Boolean) StateManager.get().get(GXT_ACCESS_CSS_ID)) {
			CSS.addStyleSheet(GXT_ACCESS_CSS_ID, GXT_ACCESS_CSS_PATH);
			accessStyle.setChecked(true);
		} else if (StateManager.get().get(GXT_SLATE_CSS_ID) != null && 
				(Boolean) StateManager.get().get(GXT_SLATE_CSS_ID)) {
			CSS.addStyleSheet(GXT_SLATE_CSS_ID, GXT_SLATE_CSS_PATH);
			slateStyle.setChecked(true);
		} else {
			blueStyle.setChecked(true);
		}

		styleMenu.add(blueStyle);
		styleMenu.add(grayStyle);
		styleMenu.add(slateStyle);
		styleMenu.add(accessStyle);
		
		styleMenuItem.setSubMenu(styleMenu);
		
		settingsButton.setMenu(settingsMenu);
		
		northPanelToolBar.add(settingsButton);
		
		setTopComponent(northPanelToolBar);
		
		addListeners();
		
	}

	/**
	 * @return the northPanelToolBar
	 */
	public ToolBar getNorthPanelToolBar() {
		return northPanelToolBar;
	}

	/**
	 * @return the settingsButton
	 */
	public Button getSettingsButton() {
		return settingsButton;
	}

	/**
	 * @return the settingsMenu
	 */
	public Menu getSettingsMenu() {
		return settingsMenu;
	}

	/**
	 * @return the blueStyle
	 */
	public CheckMenuItem getBlueStyle() {
		return blueStyle;
	}

	/**
	 * @return the grayStyle
	 */
	public CheckMenuItem getGrayStyle() {
		return grayStyle;
	}

	/**
	 * Adding listeners
	 */
	private void addListeners() {
		
		blueStyle.addListener(Events.CheckChange, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent menuEvent) {
				if (menuEvent.isChecked()) {
					CSS.removeStyleSheet(GXT_GRAY_CSS_ID);
					StateManager.get().set(GXT_GRAY_CSS_ID, Boolean.FALSE);
					CSS.removeStyleSheet(GXT_ACCESS_CSS_ID);
					StateManager.get().set(GXT_ACCESS_CSS_ID, Boolean.FALSE);
					CSS.removeStyleSheet(GXT_SLATE_CSS_ID);
					StateManager.get().set(GXT_SLATE_CSS_ID, Boolean.FALSE);
				}
			}
			
		});
		
		grayStyle.addListener(Events.CheckChange, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent menuEvent) {
				if (menuEvent.isChecked()) {
					CSS.removeStyleSheet(GXT_ACCESS_CSS_ID);
					StateManager.get().set(GXT_ACCESS_CSS_ID, Boolean.FALSE);
					CSS.removeStyleSheet(GXT_SLATE_CSS_ID);
					StateManager.get().set(GXT_SLATE_CSS_ID, Boolean.FALSE);
					CSS.addStyleSheet(GXT_GRAY_CSS_ID, GXT_GRAY_CSS_PATH);
					StateManager.get().set(GXT_GRAY_CSS_ID, Boolean.TRUE);
				}
			}
			
		});
		
		accessStyle.addListener(Events.CheckChange, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent menuEvent) {
				if (menuEvent.isChecked()) {
					CSS.removeStyleSheet(GXT_GRAY_CSS_ID);
					StateManager.get().set(GXT_GRAY_CSS_ID, Boolean.FALSE);
					CSS.removeStyleSheet(GXT_SLATE_CSS_ID);
					StateManager.get().set(GXT_SLATE_CSS_ID, Boolean.FALSE);
					CSS.addStyleSheet(GXT_ACCESS_CSS_ID, GXT_ACCESS_CSS_PATH);
					StateManager.get().set(GXT_ACCESS_CSS_ID, Boolean.TRUE);
				}
			}
			
		});
		
		slateStyle.addListener(Events.CheckChange, new Listener<MenuEvent>() {

			@Override
			public void handleEvent(MenuEvent menuEvent) {
				if (menuEvent.isChecked()) {
					CSS.removeStyleSheet(GXT_GRAY_CSS_ID);
					StateManager.get().set(GXT_GRAY_CSS_ID, Boolean.FALSE);
					CSS.removeStyleSheet(GXT_ACCESS_CSS_ID);
					StateManager.get().set(GXT_ACCESS_CSS_ID, Boolean.FALSE);
					CSS.addStyleSheet(GXT_SLATE_CSS_ID, GXT_SLATE_CSS_PATH);
					StateManager.get().set(GXT_SLATE_CSS_ID, Boolean.TRUE);
				}
			}
			
		});
		
	}
	
}
