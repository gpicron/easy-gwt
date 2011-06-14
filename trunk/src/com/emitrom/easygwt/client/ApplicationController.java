package com.emitrom.easygwt.client;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.easygwt.client.core.gwtrpc.SampleService;
import com.emitrom.easygwt.client.core.gwtrpc.SampleServiceAsync;
import com.emitrom.easygwt.client.core.injection.SampleInjector;
import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.views.accordion.AccordionNavigationChild;
import com.emitrom.easygwt.wf.client.views.accordion.AccordionNavigationItemInterface;
import com.emitrom.easygwt.wf.client.views.accordion.AccordionNavigationParent;
import com.emitrom.easygwt.wf.client.views.accordion.AccordionViewPort;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ApplicationController implements EntryPoint {
	
	private static SampleIcons icons = GWT.create(SampleIcons.class);
	private static SampleConstants constants = GWT.create(SampleConstants.class);
	private static SampleServiceAsync service = GWT.create(SampleService.class);
	private static SampleInjector injector = GWT.create(SampleInjector.class);
	
	public void onModuleLoad() {
		
		/**
		 * Sample RPC Call
		 */
		service.sampleRPC("HELLO", new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println("Call succeded, received: " + result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
			}
		});
		
		/**
		 * Accordion View Sample
		 */
		AccordionViewPort accordionView = AccordionViewPort.getInstance();
		
		List<AccordionNavigationItemInterface> navigationItems = new ArrayList<AccordionNavigationItemInterface>();
		
		AccordionNavigationParent parent1 = new AccordionNavigationParent();
		parent1.setHeading(constants.parentOne());
		parent1.setIcon(AbstractImagePrototype.create(icons.chartBar()));
		
		AccordionNavigationChild parent1Child1 = new AccordionNavigationChild(injector.getMyView(), 
				constants.parentOneChildOne());
		parent1Child1.setIcon(icons.chartBar());
		parent1.addNavigationChild(parent1Child1);
		
		AccordionNavigationChild parent1Child2 = new AccordionNavigationChild(injector.getMyView(), 
				constants.parentOneChildTwo());
		parent1Child2.setIcon(icons.chartBar());
		parent1.addNavigationChild(parent1Child2);

		AccordionNavigationParent parent2 = new AccordionNavigationParent();
		parent2.setHeading(constants.parentTwo());
		parent2.setIcon(AbstractImagePrototype.create(icons.chartBar()));
		
		AccordionNavigationChild parent2Child1 = new AccordionNavigationChild(injector.getMyView(), 
				constants.parentTwoChildOne());
		parent2Child1.setIcon(icons.chartBar());
		parent2.addNavigationChild(parent2Child1);
		
		AccordionNavigationChild parent2Child2 = new AccordionNavigationChild(injector.getMyView(), 
				constants.parentTwoChildTwo());
		parent2Child2.setIcon(icons.chartBar());
		parent2.addNavigationChild(parent2Child2);

		navigationItems.add(parent1);
		navigationItems.add(parent2);

		accordionView.addNavigationItems(navigationItems);
		
	}
	
}
