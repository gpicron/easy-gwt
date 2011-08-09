package com.emitrom.easygwt.wf.client.widgets.effects.fisheye;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;

/**
 * @author Alfredo Quiroga-Villamil
 *
 */
public class ImageDetails extends JavaScriptObject {
	
	protected ImageDetails() {
		
	}
	
	public static ImageDetails getInstance() {
		
		ImageDetails imageDetails = ImageDetails.createInstance();
		
		return imageDetails;
		
	}
	
	private static native ImageDetails createInstance() /*-{
		return {};
	}-*/;
	
	public final native void setName(String name) /*-{
		this.name = name;
	}-*/;
	
	public final native String getName() /*-{
		return this.name;
	}-*/;
	
	public final native void setExtension(String extension) /*-{
		this.extension = extension;
	}-*/;

	public final native String getExtension() /*-{
		return this.extension;
	}-*/;
	
	public final native void setSizes(JsArrayNumber sizes) /*-{
		this.sizes = sizes;
	}-*/;
	
	public final native JsArrayNumber getSizes() /*-{
		return this.sizes;
	}-*/;
	
	public final native void addClickHandler(FishEyeClickHandler handler) /*-{
		this.onclick = function() {
			handler.@com.emitrom.easygwt.wf.client.widgets.effects.fisheye.FishEyeClickHandler::onClick()();
		}
	}-*/;

}
