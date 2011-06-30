package com.emitrom.easygwt.client.views.wizard;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.inject.Inject;

public class WelcomePage extends WizardPage {

	@SuppressWarnings("unused")
	private SampleIcons icons;
	private ContentPanel welcomeContentPanel;
	
	@Inject
	public WelcomePage(SampleIcons icons) {
		this.icons = icons;
		init();
	}
	
	private void init() {
		
		welcomeContentPanel = new ContentPanel();
		welcomeContentPanel.setHeaderVisible(false);
		welcomeContentPanel.setLayout(new FitLayout());
		welcomeContentPanel.setBodyBorder(false);
		welcomeContentPanel.setStyleAttribute("padding", "20px");
		welcomeContentPanel.setStyleAttribute("font-weight", "normal");
		
		StringBuilder welcomeMessage = new StringBuilder();
		welcomeMessage.append("<h1>Welcome to the New User Wizard<h1/><br/>");
		welcomeMessage.append("<br/>");
		welcomeMessage.append("<p>This wizard is being used to demonstrate one of the many ways in which ");
		welcomeMessage.append("wizards can be used. It is merely used as a sample to illustrate some of the functionality it offers.<p/>");

		LabelField labelField = new LabelField();
		labelField.setValue(welcomeMessage);

		welcomeContentPanel.add(labelField);

		add(welcomeContentPanel);

	}
	
	@Override
	public void renderPage() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				welcomeContentPanel.getBody().setStyleAttribute("backgroundColor", "transparent");				
			}
		});
	}

	@Override
	public void saveModel() {
		// TODO Auto-generated method stub

	}

}
