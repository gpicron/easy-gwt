package com.emitrom.easygwt.client.resources.I18N;

import com.google.gwt.i18n.client.Constants;

public interface SampleConstants extends Constants {

	@DefaultStringValue("Parent One")
	String parentOne();
	
	@DefaultStringValue("Parent Two")
	String parentTwo();
	
	@DefaultStringValue("Parent One - Child One")
	String parentOneChildOne();

	@DefaultStringValue("Parent One - Child Two")
	String parentOneChildTwo();

	@DefaultStringValue("Parent Two - Child One")
	String parentTwoChildOne();

	@DefaultStringValue("Parent Two - Child Two")
	String parentTwoChildTwo();
	
	@DefaultStringValue("Parent Two - Child Three")
    String parentTwoChildThree();

	@DefaultStringValue("Sample Constant String")
	String sampleConstantString();

}
