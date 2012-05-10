/*******************************************************************************
 * WizardModel.java is part of EasyGWT 
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

import java.util.HashMap;

/**
 * Stores wizard attributes.
 * 
 * @author <a href=mailto:david@emitrom.com>David La Motta</a>
 *
 */
public class WizardModel implements WizardModelInterface {
	
    private HashMap<String, Object> properties;
    
    /**
     * Constructor.
     */
    public WizardModel() {
        properties = new HashMap<String, Object>();
    }
    
    /**
     * Sets a property
     * 
     * @param key
     * @param value
     */
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }
    
    /**
     * Gets a property
     * 
     * @return Object
     */
    public Object getProperty(String key) {
        return properties.get(key);
    }
}
