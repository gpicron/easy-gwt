package com.emitrom.easygwt.client.core.injection;

import com.emitrom.easygwt.client.views.MySecondView;
import com.emitrom.easygwt.client.views.MyView;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({SampleBinder.class})
public interface SampleInjector extends Ginjector {

	MyView getMyView();
	MySecondView getMySecondView();
	
}
