package com.emitrom.easygwt.client;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.easygwt.client.core.authentication.SampleLoginAuthenticator;
import com.emitrom.easygwt.client.core.injection.SampleInjector;
import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.column.core.ColumnNavigationChild;
import com.emitrom.easygwt.wf.client.column.core.ColumnNavigationParent;
import com.emitrom.easygwt.wf.client.column.core.ColumnViewPort;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ApplicationController implements EntryPoint {
	
	private static SampleIcons icons = GWT.create(SampleIcons.class);
	private static SampleConstants constants = GWT.create(SampleConstants.class);
	private static SampleInjector injector = GWT.create(SampleInjector.class);
	
	public void onModuleLoad() {
		
		/**
		 * Removing the loading gif.
		 */
		DOM.getElementById("loading").removeFromParent();
		
		/**
		 * Authenticate
		 */
//		new SampleLoginAuthenticator() {
//
//			@Override
//			public void onSuccess() {
				/**
				 * Column View Sample
				 */
				ColumnViewPort columnView = ColumnViewPort.getInstance();
				
				columnView.getNorthPanel().getNorthPanelToolBar().add(new FillToolItem());
				
				LabelField loggedInUser = new LabelField();
//				loggedInUser.setValue("<b>" + loginDialog.getUsernameTextField().getValue() + "<b/>"); 
				
				Button logoutButton = new Button();
				logoutButton.setIcon(AbstractImagePrototype.create(icons.doorOut()));
				logoutButton.setToolTip("Logout");
				logoutButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
//						logout();
					}
				});
				
				columnView.getNorthPanel().getNorthPanelToolBar().add(loggedInUser);
				columnView.getNorthPanel().getNorthPanelToolBar().add(new SeparatorToolItem());
				columnView.getNorthPanel().getNorthPanelToolBar().add(logoutButton);
				
				List<ColumnNavigationParent> navigationParentsList = new ArrayList<ColumnNavigationParent>();
				
				ColumnNavigationParent parent1 = new ColumnNavigationParent();
				parent1.setHeading(constants.usersViewHeading());
				parent1.setIcon(AbstractImagePrototype.create(icons.chartBar()));
				
				ColumnNavigationChild parent1Child1 = new ColumnNavigationChild(injector.getUsersView(), 
						constants.usersViewHeading());
				parent1Child1.setIcon(icons.house());
				parent1.addNavigationChild(parent1Child1);
				
				navigationParentsList.add(parent1);

				columnView.addNavigationItems(navigationParentsList);

//			}
//			
//		};
		
	}
	
}
