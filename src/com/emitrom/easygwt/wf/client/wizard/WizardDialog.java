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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.emitrom.easygwt.wf.client.resources.i18n.I18Constants;
import com.emitrom.easygwt.wf.client.utils.Util;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
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
    protected WizardModelInterface model;
    protected int currentPageIndex;

    // Wizard heading
    
    private Set<WizardPage> renderedPages;
    
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
        this.currentPageIndex = 0;
                
        initSteps();
        addButtons();
        initPagesStack();
        
        addListeners();
                
    }
    
    /**
	 * @return the previousButton
	 */
	public Button getPreviousButton() {
		return previousButton;
	}

	/**
	 * @return the nextButton
	 */
	public Button getNextButton() {
		return nextButton;
	}

	/**
	 * @return the finishButton
	 */
	public Button getFinishButton() {
		return finishButton;
	}

	/**
	 * @return the cancelButton
	 */
	public Button getCancelButton() {
		return cancelButton;
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
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				listView.disableEvents(true);
			}
		});
		listView.setBorders(true);
        
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
    	
    	renderedPages = new HashSet<WizardPage>();
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
            setActivePage(currentPageIndex);         
        }
        
        super.show();
    }
    
    /**
     * Adds a page to this wizard.
     * 
     * @param page the page to add.
     */
    public void addPage(final WizardPage page) {
    	
        BaseModelData stepData = new BaseModelData();
        stepData.set("step", page.getStepDescription());
        stepsStore.add(stepData);        
        pagesStack.add(page);
        page.setModel(model);
        page.setWizardDialog(this);
        
    }
    
    /**
     * Adds a list of pages to this wizard.
     * 
     * @param pageList a List of WizardPage objects.
     */
    public void addPages(List<WizardPage> pageList) {
    	for (WizardPage page : pageList) {
    		addPage(page);
    	}
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
    	
        final WizardPage activePage = (WizardPage) pagesStack.getItem(index);        
        
        // highlight the current step
        listView.getSelectionModel().select(index, false);
        
		if (!renderedPages.contains(activePage)) {
			activePage.renderPage();
			renderedPages.add(activePage);
		}

        if (renderedPages.contains(activePage)) {
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
    			@Override
    			public void execute() {
    		        activePage.prepareToShow();	
    			}
    		});
        }
        
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
        currentPage.prepareToHide();

        if (currentPage.isValid()) {
            setActivePage(++currentPageIndex);
        }
        
    }
    
    /**
     * Goes back one page.
     */
    private void previous() {
    	getCurrentPage().prepareToHide();
        setActivePage(--currentPageIndex);
    }
    
    /**
     * Cancels this wizard.
     */
    private void cancel() {
        this.hide();
    }
    
    /**
     * Adds listeners to the widgets in this class.
     */
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
        
    }
    
    /**
     * Let the implementation of what to do when the wizard is finished to the user.
     */
    public abstract void finish();
    
}
