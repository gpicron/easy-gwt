package com.emitrom.easygwt.client.views;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.column.core.ColumnView;
import com.emitrom.easygwt.wf.client.wizard.WizardDialog;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;

public class MyView extends ColumnView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;
	private SampleIcons icons;

	@Inject
	public MyView(SampleConstants constants, SampleIcons icons) {
		this.constants = constants;
		this.icons = icons;
	}

	@Override
	public void onRender() {

		WizardDialog wizard = new WizardDialog() {
			
			@Override
			public void finish() {
				System.out.println("FINISH IS HERE");
			}
		};
		wizard.setIcon(AbstractImagePrototype.create(icons.house()));
		wizard.setHeading("New SnapShot Wizard");
		
		List<WizardPage> wizardPageList = new ArrayList<WizardPage>();
		wizardPageList.add(new WelcomePage("Welcome Page", "Welcome"));
		wizardPageList.add(new WelcomePage("First Page", "First Page"));
		wizardPageList.add(new WelcomePage("Second Page", "Second Page"));
		wizardPageList.add(new WelcomePage("Third Page", "Third Page"));
		
		wizard.addPages(wizardPageList);
		
		wizard.show();
		
	}
	
	private class WelcomePage extends WizardPage {
		
		public WelcomePage(String pageDescription, String stepDescription) {
			super(pageDescription, stepDescription);
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
			
		}
		
	}

}
