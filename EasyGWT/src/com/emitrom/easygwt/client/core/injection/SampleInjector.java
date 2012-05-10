package com.emitrom.easygwt.client.core.injection;

import com.emitrom.easygwt.client.views.UsersView;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({SampleBinder.class})
public interface SampleInjector extends Ginjector {

	UsersView getUsersView();
	
}
