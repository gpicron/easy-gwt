package com.emitrom.easygwt.client.views.wizard;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;

public class WelcomePage extends WizardPage {

	private SampleIcons icons;
	
	@Inject
	public WelcomePage(SampleIcons icons) {
		this.icons = icons;
	}
	
	@Override
	public void renderPage() {
		
		FormPanel formPanel = new FormPanel();
		formPanel.setHeading(pageDescription);
		formPanel.setIcon(AbstractImagePrototype.create(icons.house()));
		formPanel.setBodyBorder(false);
		TextField<String> field = new TextField<String>();
		field.setFieldLabel("FIELD");
		formPanel.add(field);
		add(formPanel);

	}

	@Override
	public void saveModel() {
		// TODO Auto-generated method stub

	}

}
