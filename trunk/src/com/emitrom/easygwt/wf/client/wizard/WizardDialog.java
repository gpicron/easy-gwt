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
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout.BoxLayoutPack;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
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
    protected List<WizardPage> pages;
    protected int currentPageIndex;
    
    // main page content
    private LayoutContainer pagesStack;
    private CardLayout pagesLayout;
    
    // header panel
    private ContentPanel headerPanel;
    private Label pageDescription = new Label();
    
    // steps    
    private ContentPanel stepsPanel;
    private ListStore<BeanModel> stepsStore;
    private Grid<BeanModel> stepsGrid;
    
    // navigation buttons
    private ContentPanel buttonsPanel;
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
        this.model = model;
        pages = new ArrayList<WizardPage>();
                
        initHeader();
        initSteps();
        initNavigation();
        initPagesStack();
                
        setLayout(new BorderLayout());
    }
    
    /**
     * Gets the header panel.
     * 
     * @return ContentPanel the wizard header panel
     */
    public ContentPanel getHeaderPanel() {
        return headerPanel;
    }
    
    /**
     * Gets the buttons panel.
     * 
     * @return ContentPanel the buttons panel
     */
    public ContentPanel getButtonsPanel() {
        return buttonsPanel;
    }
    
    /**
     * Gets the steps panel.
     * 
     * @return ContentPanel the steps panel
     */
    public ContentPanel getStepsPanel() {
        return stepsPanel;
    }
    
    /**
     * Initializes the header
     */
    private void initHeader() {
        headerPanel = new ContentPanel();
        headerPanel.setHeaderVisible(false);
        headerPanel.setBodyBorder(false);
        
        HBoxLayout hBoxLayout = new HBoxLayout();
        hBoxLayout.setPadding(new Padding(5));
        hBoxLayout.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        hBoxLayout.setPack(BoxLayoutPack.START);
        
        HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 0, 0, 5));
        headerPanel.add(pageDescription, layoutData);
        
        add(headerPanel, new BorderLayoutData(LayoutRegion.NORTH));
    }
    
    /**
     * Initializes the steps panel.  This can only be done after all the pages
     * have been added to the wizard, so this method is called when the wizard
     * is shown.
     */
    private void initSteps() {
        // we do a content panel here so the user can set a background image
        stepsPanel = new ContentPanel();
        stepsPanel.setHeaderVisible(false);
        stepsPanel.setBodyBorder(false);
        
        ColumnConfig stepsConfig = new ColumnConfig();
        stepsConfig.setFixed(true);
        stepsConfig.setId("step");
        stepsConfig.setHeader(null);
        
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(stepsConfig);
        
        ColumnModel cm = new ColumnModel(columns);
        
        stepsStore = new ListStore<BeanModel>();
        stepsGrid = new Grid<BeanModel>(stepsStore, cm);
        
        VBoxLayout vBoxLayout = new VBoxLayout();
        vBoxLayout.setPadding(new Padding(5));
        vBoxLayout.setPack(BoxLayoutPack.START);
        vBoxLayout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
        
        stepsPanel.setLayout(vBoxLayout);        
        stepsPanel.add(stepsGrid);
    }
    
    /**
     * Initializes the navigations buttons.
     */
    private void initNavigation() {
        buttonsPanel = new ContentPanel();
        buttonsPanel.setHeaderVisible(false);
        buttonsPanel.setBodyBorder(false);
        
        HBoxLayout hBoxLayout = new HBoxLayout();
        hBoxLayout.setPadding(new Padding(5));
        hBoxLayout.setPack(BoxLayoutPack.END);
        hBoxLayout.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
                  
        buttonsPanel.setLayout(new HBoxLayout());
        
        previousButton = new Button(constants.previousButtonHeading());
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
        
        nextButton = new Button(constants.nextButtonHeading());
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
        
        finishButton = new Button(constants.finishButtonHeading());
        finishButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        finish();
                    }
                });
            }
        });
        
        cancelButton = new Button(constants.cancelButtonHeading());
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
        
        HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 0, 0, 5));  
        buttonsPanel.add(previousButton, layoutData);
        buttonsPanel.add(nextButton, layoutData);  
        buttonsPanel.add(finishButton, layoutData);
        buttonsPanel.add(cancelButton, layoutData);
        
        add(buttonsPanel, new BorderLayoutData(LayoutRegion.SOUTH));
    }
    
    /**
     * Initializes the card layout where all the wizard pages are kept
     */
    private void initPagesStack() {
        pagesLayout = new CardLayout();
        pagesStack = new LayoutContainer();
        pagesStack.setLayout(pagesLayout);        
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
        pages.add(page);
    }
    
    /**
     * Gets the number of pages this wizard has
     * 
     * @return int number of pages in this wizard
     */
    public int getPageCount() {
        return pages.size();
    }
    
    /**
     * Updates the state of the navigation buttons.  The state depends what
     * page the user is currently visiting, and if that page is valid in order
     * to make progress.
     */
    private void updateNavigationState() {
        boolean valid = getCurrentPage().isValid();
        
        previousButton.setEnabled(currentPageIndex > 0);
        nextButton.setEnabled(currentPageIndex < pages.size() && valid);
        finishButton.setEnabled(currentPageIndex == pages.size() - 1 && valid);
    }
    
    /**
     * Gets the current page from the wizard.
     * 
     * @return WizardPage
     */
    public WizardPage getCurrentPage() {
        return pages.get(currentPageIndex);
    }
    
    /**
     * Displays the page at the given index.
     * 
     * @param index
     */
    private void setActivePage(int index) {
        WizardPage activePage = pages.get(index);        
        
        // highlight the current step
        stepsGrid.getSelectionModel().select(index, false);
        
        // change the wizard's description
        pageDescription.setText(activePage.getPageDescription());
        
        if (!activePage.isPageRendered()) {
            activePage.renderPage();
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
        
        if (currentPage.isValid()) {
            currentPage.saveModel();
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
    
    /**
     * Let the implementation of what to do when the wizard is finished to the user.
     */
    public abstract void finish();
}
