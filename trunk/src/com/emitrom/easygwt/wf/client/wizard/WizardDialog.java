/*******************************************************************************
 * WizardDialog.java is part of EasyGWT 
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
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU   General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 ******************************************************************************/
package com.emitrom.easygwt.wf.client.wizard;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.easygwt.wf.client.resources.i18n.I18Constants;
import com.emitrom.easygwt.wf.client.utils.Util;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

/**
 * WizardDialog is the parent class of all wizards.  It provides access to the
 * model, and the basic navigation functionality.
 * 
 * @author <a href=mailto:david@emitrom.com>David La Motta</a>
 * 
 */
public abstract class WizardDialog extends Dialog {
	
    protected I18Constants constants = Util.getConstants();
    protected WizardModel model;
    protected int currentPageIndex;
    
    // Wizard heading
    
    // main page content
    private LayoutContainer pagesStack;
    private CardLayout pagesLayout;
    
    // steps    
    private ListView<BaseModelData> listView;
    private ListStore<BaseModelData> stepsStore;
    
    // navigation buttons
    private Button previousButton;
    private Button nextButton;
    private Button finishButton;
    private Button cancelButton;

    /**
     * Constructor.
     * 
     * @param model the wizard model
     */
    public WizardDialog() {
        this(new WizardModel());
    }
    
    /**
     * Constructor.
     * 
     * @param model the wizard model
     */
    public WizardDialog(WizardModel model) {

        setLayout(new BorderLayout());
    	setButtons("");
    	setSize(600, 465);
    	setResizable(false);
    	
        this.model = model;        
                
        initSteps();
        addButtons();
        initPagesStack();
        
        addListeners();
                
    }
    
    /**
     * Initializes the steps panel.  This can only be done after all the pages
     * have been added to the wizard, so this method is called when the wizard
     * is shown.
     */
    private void initSteps() {
    	
        listView = new ListView<BaseModelData>();
        listView.setStyleName("wf-navigation-wizard");
        listView.setDisplayProperty("step");
        listView.disableEvents(true);
        
        stepsStore = new ListStore<BaseModelData>();
        listView.setStore(stepsStore);
        
        add(listView, new BorderLayoutData(LayoutRegion.WEST, 165));
        
    }
    
    /**
     * Initializes the navigations buttons.
     */
    private void addButtons() {
    	
        previousButton = new Button(constants.previousButtonHeading());
        nextButton = new Button(constants.nextButtonHeading());
        finishButton = new Button(constants.finishButtonHeading());
        cancelButton = new Button(constants.cancelButtonHeading());
        
        addButton(previousButton);
        addButton(nextButton);
        addButton(finishButton);
        addButton(cancelButton);
        
    }
    
    /**
     * Initializes the card layout where all the wizard pages are kept
     */
    private void initPagesStack() {
    	
        pagesLayout = new CardLayout();
        pagesStack = new LayoutContainer();
        pagesStack.setLayout(pagesLayout);        
        
        add(pagesStack, new BorderLayoutData(LayoutRegion.CENTER));
        
    }
    
    /**
     * Launches the wizard.
     */
    public void show() {        
        if (getPageCount() > 0) {
            setActivePage(0);         
        }
        
        super.show();
    }
    
    /**
     * Adds a page to this wizard.
     * 
     * @param page the page to add.
     */
    public void addPage(WizardPage page) {
    	
        BaseModelData stepData = new BaseModelData();
        stepData.set("step", page.getStepDescription());
        stepsStore.add(stepData);        
        pagesStack.add(page);
        
    }
    
    /**
     * Gets the number of pages this wizard has
     * 
     * @return int number of pages in this wizard
     */
    public int getPageCount() {
        return pagesStack.getItemCount();
    }
    
    /**
     * Updates the state of the navigation buttons.  The state depends what
     * page the user is currently visiting, and if that page is valid in order
     * to make progress.
     */
    private void updateNavigationState() {
        boolean valid = getCurrentPage().isValid();
        
        previousButton.setEnabled(currentPageIndex > 0);
        nextButton.setEnabled(currentPageIndex < pagesStack.getItemCount() - 1 && valid);
        finishButton.setEnabled(currentPageIndex == pagesStack.getItemCount() - 1 && valid);
    }
    
    /**
     * Gets the current page from the wizard.
     * 
     * @return WizardPage
     */
    public WizardPage getCurrentPage() {
        return (WizardPage) pagesStack.getItem(currentPageIndex);
    }
    
    /**
     * Displays the page at the given index.
     * 
     * @param index
     */
    private void setActivePage(int index) {
    	
        WizardPage activePage = (WizardPage) pagesStack.getItem(index);        
        
        // highlight the current step
        listView.getSelectionModel().select(index, false);
        
        activePage.renderPage();
        
        activePage.prepareToShow();
        
        // switch to the page
        pagesLayout.setActiveItem(activePage);
        
        // ...and update the buttons status
        updateNavigationState();
        
    }
    
    /**
     * Goes forward one page, as long as the page is allowed to make progress.
     */
    private void next() {
    	
        WizardPage currentPage = getCurrentPage();
        
        if (currentPage.isValid()) {
            currentPage.prepareToHide();
            setActivePage(++currentPageIndex);
        }
    }
    
    /**
     * Goes back one page.
     */
    private void previous() {
        setActivePage(--currentPageIndex);
    }
    
    /**
     * Cancels this wizard.
     */
    private void cancel() {
        this.hide();
    }
    
    private void addListeners() {
    	
        previousButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        previous();
                    }
                });
            }
        });

        nextButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        next();
                    }
                });
            }
        });

        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        cancel();
                    }
                });
            }
        });
        
        finishButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                    	WizardDialog.this.hide();
                        finish();
                    }
                });
            }
        });
        
        listView.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			@Override
			public void handleEvent(ComponentEvent be) {
				@SuppressWarnings("unchecked")
				BaseModelData foo = ((ListView<BaseModelData>) be.getComponent()).getSelectionModel().getSelectedItem();
				int index = listView.getStore().indexOf(foo);
				currentPageIndex = index;
				setActivePage(index);
			}
		});
        
    }
    
    /**
     * Let the implementation of what to do when the wizard is finished to the user.
     */
    public abstract void finish();
    
}
