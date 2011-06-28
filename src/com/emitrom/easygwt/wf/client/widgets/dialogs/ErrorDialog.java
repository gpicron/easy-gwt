/*******************************************************************************
 * ErrorDialog.java is part of EasyGWT 
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
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 ******************************************************************************/
package com.emitrom.easygwt.wf.client.widgets.dialogs;

import com.emitrom.easygwt.wf.client.utils.Util;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * Error dialog that shows an error icon, a descriptive message, and has the ability
 * to hide or show details.  These details can be more text if specified by the user,
 * or it can be the contents of a stack trace.
 * 
 * @author <a href=mailto:david@emitrom.com>David La Motta</a>
 */
public class ErrorDialog extends Dialog implements IsWidget {
    private Button detailsButton;
    private LayoutContainer detailsContainer;
    
    /**
     * The simplest of the constructors, takes a title for the window and the message
     * to display in the dialog.
     * 
     * @param title
     * @param message
     */
    public ErrorDialog(String title, String message) {
        this(title, message, null, null);
    }
    
    /**
     * Builds an error dialog with a title, the message to display in the dialog, and
     * the details to be shown in the details section.
     * 
     * @param title
     * @param message
     * @param details
     */
    public ErrorDialog(String title, String message, String details) {
        this(title, message, details, null);
    }
    
    /**
     * Build an error dialog with a title, the message to display in the dialog, and
     * a Throwable, which will be used to retrieve the stack trace to display in the details
     * sections.
     * 
     * @param title
     * @param message
     * @param throwable
     */
    public ErrorDialog(String title, String message, Throwable throwable) {
        this(title, message, null, throwable);
    }
    
    /**
     * Build an error dialog with a title, the message to display in the dialog,
     * a detailed message that will be displayed above the stack trace retrieved from
     * the provided Throwable object.
     * 
     * @param title
     * @param message
     * @param details
     * @param throwable
     */
    public ErrorDialog(String title, String message, String details, Throwable throwable) {        
        detailsButton = new Button(Util.getConstants().detailsButtonHeading());
                
        addButton(detailsButton);
        detailsButton.setEnabled(false);
        
        setHeading(title);
        setModal(true);        
        setBodyBorder(false);               
        setHideOnButtonClick(true);        
        setLayout(new BorderLayout());               
        setWidth(350);
        setHeight(200);
               
        LayoutContainer imageContainer = new LayoutContainer();        
        Image image = AbstractImagePrototype.create(Util.getImages().errorImage()).createImage();
        imageContainer.add(image);
        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 42);
        westData.setMargins(new Margins(10, 0, 0, 10));
        add(imageContainer, westData);
        
        LayoutContainer labelContainer = new LayoutContainer();
        labelContainer.setLayout(new FitLayout());        
        Label messageLabel = new Label(message);        
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(10, 10, 0, 0));
        labelContainer.add(messageLabel);
        add(labelContainer, centerData);               
                
        TextArea detailsArea = new TextArea();
        String detailsMessage = "";
        boolean displayDetails = false;
        
        if (details != null) {
            detailsMessage += details + "\n\n";
            displayDetails = true;
        }
        
        if (throwable != null) {
            detailsMessage += Util.getStackTrace(throwable);
            displayDetails = true;
        }
        
        if (displayDetails) {
            // we'll double in height
            setHeight(400);
            
            detailsButton.setEnabled(true);
            
            // we need a container here for the bottom component
            detailsContainer = new LayoutContainer();
            detailsContainer.setLayout(new FitLayout());
            detailsContainer.add(detailsArea);
            
            detailsArea.setValue(detailsMessage);
            setBottomComponent(detailsContainer);
            getBottomComponent().setHeight("200px");
            
            detailsContainer.setVisible(true);
        }
        
        addListeners();
    }
    
    /**
     * Initializes component listeners
     */
    private void addListeners() {
        detailsButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        detailsContainer.setVisible(!detailsContainer.isVisible());
                        ErrorDialog.this.repaint();
                    }
                });
            }
        });
    }
}
