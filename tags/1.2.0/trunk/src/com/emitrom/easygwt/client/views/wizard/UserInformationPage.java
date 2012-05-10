package com.emitrom.easygwt.client.views.wizard;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.wizard.WizardModel;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;

public class UserInformationPage extends WizardPage {
	
	private SampleConstants constants;
	private SampleIcons icons;
	
	private TextField<String> firstNameTextField;
	private TextField<String> lastNameTextField;
	private TextField<String> userNameTextField;
	private TextField<String> emailTextField;
	
	private FormButtonBinding formButtonBinding;

	@Inject
	public UserInformationPage(SampleConstants constants, SampleIcons icons) {
		this.icons = icons;
		this.constants = constants;
	}

	@Override
	public void renderPage() {
		
		FormPanel formPanel = new FormPanel();
		formPanel.setHeading(pageDescription);
		formPanel.setIcon(AbstractImagePrototype.create(icons.user()));
		formPanel.setBodyBorder(false);
		formPanel.setFieldWidth(300);
		
		firstNameTextField = new TextField<String>();
		firstNameTextField.setFieldLabel(constants.usersGridFirstNameColumnHeader());
		firstNameTextField.setAllowBlank(false);
		
		lastNameTextField = new TextField<String>();
		lastNameTextField.setFieldLabel(constants.usersGridLastNameColumnHeader());
		lastNameTextField.setAllowBlank(false);
		
		userNameTextField = new TextField<String>();
		userNameTextField.setFieldLabel(constants.usersGridUserNameColumnHeader());
		userNameTextField.setAllowBlank(false);
		
		emailTextField = new TextField<String>();
		emailTextField.setFieldLabel(constants.usersGridEmailColumnHeader());
		emailTextField.setAllowBlank(false);
		
		formPanel.add(firstNameTextField);
		formPanel.add(lastNameTextField);
		formPanel.add(userNameTextField);
		formPanel.add(emailTextField);
		
		formButtonBinding = new FormButtonBinding(formPanel);
		formButtonBinding.addButton(getWizardDialog().getNextButton());
		
		add(formPanel);

	}

	@Override
	public void saveModel() {
		WizardModel wizardModel = (WizardModel) model;
		wizardModel.setProperty("firstName", firstNameTextField.getValue());
		wizardModel.setProperty("lastName", lastNameTextField.getValue());
		wizardModel.setProperty("userName", userNameTextField.getValue());
		wizardModel.setProperty("email", emailTextField.getValue());
	}
	
	@Override
	public void prepareToShow() {
		formButtonBinding.startMonitoring();
	}
	
	@Override
	public void prepareToHide() {
		super.prepareToHide();
		formButtonBinding.stopMonitoring();
	}
	
}
