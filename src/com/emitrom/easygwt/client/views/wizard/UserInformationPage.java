package com.emitrom.easygwt.client.views.wizard;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.google.inject.Inject;

public class UserInformationPage extends WizardPage {
	
	private SampleIcons icons;

	@Inject
	public UserInformationPage(SampleIcons icons) {
		this.icons = icons;
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveModel() {
		// TODO Auto-generated method stub

	}

}
