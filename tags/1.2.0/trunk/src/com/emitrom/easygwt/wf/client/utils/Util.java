/*******************************************************************************
 * This file is part of EasyGWT 
 * 
 * Copyright (c) 2011 Emitrom LLC
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
package com.emitrom.easygwt.wf.client.utils;

import com.emitrom.easygwt.wf.client.resources.CSS;
import com.emitrom.easygwt.wf.client.resources.Icons;
import com.emitrom.easygwt.wf.client.resources.Images;
import com.emitrom.easygwt.wf.client.resources.i18n.I18Constants;
import com.google.gwt.core.client.GWT;

/**
 * 
 * Helper Class used to gain access to EasyGWT resources.
 * The resources can also be injected if needed.
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class Util {
	
	private static I18Constants constants = GWT.create(I18Constants.class);
	private static Icons icons = GWT.create(Icons.class);
	private static Images images = GWT.create(Images.class);
	private static CSS css = GWT.create(CSS.class);

	private Util() {}
	
	/**
	 * Get access to the Framework I18N Constants
	 * 
	 * @return I18N Constants 
	 */
	public static I18Constants getConstants() {
		return constants;
	}

	/**
	 * Get the framework icon ImageResource.
	 * 
	 * @return ImageResource
	 */
	public static Icons getIcons() {
		return icons;
	}

	/**
	 * Get the framework images
	 * 
	 * @return ImageResource
	 */
	public static Images getImages() {
		return images;
	}

	/**
	 * @return the css
	 */
	public static CSS getCss() {
		return css;
	}
	
	/**
	 * Gets the contents of the given Throwable's stack strace as a String.
	 * 
	 * @param throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable throwable) {
	    StringBuilder sb = new StringBuilder();
	    
	    // Get the stack trace
	    StackTraceElement stack[] = throwable.getStackTrace();

	    // stack[0] contains the method that created the exception.
	    // stack[stack.length-1] contains the oldest method call.
	    
	    sb.append(throwable.getClass().getName()).append("\n");
	    
	    for (int i = 0; i < stack.length; i++) {
	        String filename = stack[i].getFileName();
	        
	        if (filename != null) {
	            filename = "(" + filename + ":" + stack[i].getLineNumber() + ")"; 
	        } else {
	            filename = "";
	        }
	        	        
	        sb.append("\t");
	        sb.append("at ");
	        sb.append(stack[i].getMethodName());
	        sb.append(filename);
	        sb.append("\n");	       
	    }
	    
	    return sb.toString();
	}
}
