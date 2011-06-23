/*******************************************************************************
 * WizardPage.java is part of EasyGWT 
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

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

/**
 * WizardPage represents an individual page in the wizard, and it contains methods
 * for checking if it is valid or not.  A valid page means the wizard can advance.
 * 
 * @author <a href=mailto:david@emitrom.com>David La Motta</a>
 *
 */
public abstract class WizardPage extends LayoutContainer implements BeanModelTag {
    protected boolean pageRendered = false;
    
    /** 
     * Is this page valid so the wizard can advance.
     * 
     * @return boolean true if progress can be made; false otherwise
     */
    public abstract boolean isValid();
    
    /**
     * Gets this page's description.
     * 
     * @return String descriptive text that is displayed in the wizard's header.
     */
    public abstract String getPageDescription();
    
    /**
     * Gets this page's step text.  This text is displayed on the left hand side of
     * the wizard, indicating the step the wizard is currently in.
     * 
     * @return String short text that goes in the steps panel of the wizard. 
     */
    public abstract String getStepDescription();
    
    /**
     * The wizard always renders the pages it contains upon entering to that page, but
     * it will only do so if the page has not been previously rendered.
     * 
     * @return boolean true if this page has been rendered; false otherwise.
     */
    public boolean isPageRendered() {
        return pageRendered;
    }
    
    /**
     * Renders this page.
     */
    public abstract void renderPage();
    
    /**
     * Saves this page's state to the wizard model.
     */
    public abstract void saveModel();
    
    /**
     * This method performs any prerequisite activities before the page is entered, but after
     * it has been rendered. This is particularly useful if the page must fill in values or
     * check constraints using previously values from earlier pages in the wizard.
     */
    public void prepareToShow() {        
    }
    
    /**
     * This method performs any activities before the page is exited, specifically saves the
     * model.  The user has the ability to do other tasks here as well.
     */
    public void prepareToHide() {
        saveModel();
    }
}
