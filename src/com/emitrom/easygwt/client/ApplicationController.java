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
		new SampleLoginAuthenticator() {

			@Override
			public void onSuccess() {
				/**
				 * Column View Sample
				 */
				ColumnViewPort columnView = ColumnViewPort.getInstance();
				
				columnView.getNorthPanel().getNorthPanelToolBar().add(new FillToolItem());
				
				LabelField loggedInUser = new LabelField();
				loggedInUser.setValue("<b>" + loginDialog.getUsernameTextField().getValue() + "<b/>"); 
				
				Button logoutButton = new Button();
				logoutButton.setIcon(AbstractImagePrototype.create(icons.doorOut()));
				logoutButton.setToolTip("Logout");
				logoutButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						logout();
					}
				});
				
				columnView.getNorthPanel().getNorthPanelToolBar().add(loggedInUser);
				columnView.getNorthPanel().getNorthPanelToolBar().add(new SeparatorToolItem());
				columnView.getNorthPanel().getNorthPanelToolBar().add(logoutButton);
				
				List<ColumnNavigationParent> navigationParentsList = new ArrayList<ColumnNavigationParent>();
				
				ColumnNavigationParent parent1 = new ColumnNavigationParent();
				parent1.setHeading(constants.parentOne());
				parent1.setIcon(AbstractImagePrototype.create(icons.chartBar()));
				
				ColumnNavigationChild parent1Child1 = new ColumnNavigationChild(injector.getMyView(), 
						constants.parentOneChildOne());
				parent1Child1.setIcon(icons.house());
				parent1.addNavigationChild(parent1Child1);
				
				ColumnNavigationChild parent1Child2 = new ColumnNavigationChild(injector.getMyView(), 
						constants.parentOneChildTwo());
				parent1Child2.setIcon(icons.coins());
				parent1.addNavigationChild(parent1Child2);

				ColumnNavigationParent parent2 = new ColumnNavigationParent();
				parent2.setHeading(constants.parentTwo());
				parent2.setIcon(AbstractImagePrototype.create(icons.group()));
				
				ColumnNavigationChild parent2Child1 = new ColumnNavigationChild(injector.getMySecondView(), 
						constants.parentTwoChildOne());
				parent2Child1.setIcon(icons.user());
				parent2.addNavigationChild(parent2Child1);
				
				ColumnNavigationChild parent2Child2 = new ColumnNavigationChild(injector.getMyView(), 
						constants.parentTwoChildTwo());
				parent2Child2.setIcon(icons.userComment());
				parent2.addNavigationChild(parent2Child2);
				
				ColumnNavigationChild parent2Child3 = new ColumnNavigationChild(injector.getMyErrorDialogView(),
				        constants.parentTwoChildThree());
				parent2Child3.setIcon(icons.coins());
				parent2.addNavigationChild(parent2Child3);

				navigationParentsList.add(parent1);
				navigationParentsList.add(parent2);

				columnView.addNavigationItems(navigationParentsList);

			}
			
		};
		
	}
	
}