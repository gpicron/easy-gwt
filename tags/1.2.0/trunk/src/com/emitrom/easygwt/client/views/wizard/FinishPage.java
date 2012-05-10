package com.emitrom.easygwt.client.views.wizard;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.wizard.WizardModel;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.inject.Inject;

public class FinishPage extends WizardPage {

	@SuppressWarnings("unused")
	private SampleIcons icons;
	private SampleConstants constants;
	private ContentPanel finishContentPanel;

	
	@Inject
	public FinishPage(SampleIcons icons, SampleConstants constants) {
		this.constants = constants;
		this.icons = icons;
		init();
	}
	
	private void init() {
		
		finishContentPanel = new ContentPanel();
		finishContentPanel.setHeaderVisible(false);
		finishContentPanel.setLayout(new FitLayout());
		finishContentPanel.setBodyBorder(false);
		finishContentPanel.setStyleAttribute("padding", "20px");
		finishContentPanel.setStyleAttribute("font-weight", "normal");
		
		add(finishContentPanel);
		
	}
	
	@Override
	public void renderPage() {
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				finishContentPanel.getBody().setStyleAttribute("backgroundColor", "transparent");				
			}
		});

	}

	@Override
	public void prepareToShow() {
		
		finishContentPanel.removeAll();
		
		StringBuilder welcomeMessage = new StringBuilder();
		welcomeMessage.append("Summary Page<br/>");
		welcomeMessage.append("<br/>");
		welcomeMessage.append(constants.usersGridFirstNameColumnHeader() + ": ");
		welcomeMessage.append(((WizardModel) model).getProperty("firstName"));
		welcomeMessage.append("<br/>");
		
		welcomeMessage.append(constants.usersGridLastNameColumnHeader() + ": ");
		welcomeMessage.append(((WizardModel) model).getProperty("lastName"));
		welcomeMessage.append("<br/>");
		
		welcomeMessage.append(constants.usersGridUserNameColumnHeader() + ": ");
		welcomeMessage.append(((WizardModel) model).getProperty("userName"));
		welcomeMessage.append("<br/>");
		
		welcomeMessage.append(constants.usersGridEmailColumnHeader() + ": ");
		welcomeMessage.append(((WizardModel) model).getProperty("email"));

		LabelField labelField = new LabelField();
		labelField.setStyleAttribute("font", "12px tahoma,arial,helvetica,sans-serif");
		labelField.setValue(welcomeMessage);

		finishContentPanel.add(labelField);
		finishContentPanel.layout();

	}
	
	@Override
	public void saveModel() {}

}
