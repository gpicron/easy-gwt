package com.emitrom.easygwt.wf.client.widgets.effects.fisheye;

import com.google.gwt.user.client.ui.Widget;


public class FishEyeImage extends Widget {
	
	private String imageName;
	private int lowSize;
	private int highSize;
	private String extension;
	private FishEyeClickHandler clickHandler;
	
	public FishEyeImage() {
		
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}


	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	/**
	 * @return the lowSize
	 */
	public int getLowSize() {
		return lowSize;
	}


	/**
	 * @param lowSize the lowSize to set
	 */
	public void setLowSize(int lowSize) {
		this.lowSize = lowSize;
	}


	/**
	 * @return the highSize
	 */
	public int getHighSize() {
		return highSize;
	}


	/**
	 * @param highSize the highSize to set
	 */
	public void setHighSize(int highSize) {
		this.highSize = highSize;
	}


	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}


	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the clickHandler
	 */
	public FishEyeClickHandler getClickHandler() {
		return clickHandler;
	}

	/**
	 * @param clickHandler the clickHandler to set
	 */
	public void setClickHandler(FishEyeClickHandler clickHandler) {
		this.clickHandler = clickHandler;
	}

}
