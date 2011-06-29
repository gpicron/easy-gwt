package com.emitrom.easygwt.client.core.injection;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.SampleImages;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.client.views.UsersView;
import com.emitrom.easygwt.client.views.wizard.UserInformationPage;
import com.emitrom.easygwt.client.views.wizard.WelcomePage;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class SampleBinder extends AbstractGinModule {

	@Override
	protected void configure() {
		
		/**
		 * Resources
		 */
		bind(SampleConstants.class).in(Singleton.class);
		bind(SampleIcons.class).in(Singleton.class);
		bind(SampleImages.class).in(Singleton.class);
		
		/**
		 * Views
		 */
		bind(UsersView.class);
		
		/**
		 * Wizard Pages
		 */
		bind(WelcomePage.class);
		bind(UserInformationPage.class);
		
	}

}
