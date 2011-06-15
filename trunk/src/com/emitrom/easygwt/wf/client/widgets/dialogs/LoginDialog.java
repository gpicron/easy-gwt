/*******************************************************************************
 * This file is part of EasyGWT 
 * 
 * Copyright (c) 2010 Emitrom LLC
 * All rights reserved. 
 * 
 * EasyGWT is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU   General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 ******************************************************************************/
package com.emitrom.easygwt.wf.client.widgets.dialogs;

import com.emitrom.easygwt.wf.client.authentication.Authenticator;
import com.emitrom.easygwt.wf.client.utils.Util;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.state.StateManager;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.AdapterField;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;

/**
 *
 * Login Dialog provided so the user doesn't have to write one.
 * It takes an instance of an Authenticator and when the enter key is
 * pressed as well as the OK button it then invokes the login method of the
 * Authenticator instance that was passed in.
 * A number of Authenticators can be passed in allowing for instance an SSH Authenticator,
 * An LDAP Authenticator implementation, etc...
 * 
 * A signup button as well as a forgot password buttons are provided but not implemented.
 * It's up to the user to add a Listener to the buttons and act accordingly if the user clicks on it.
 * 
 * @See {@link Authenticator}
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public class LoginDialog extends Dialog {

	private static final String LOGIN_BANNER_IMAGE = "resources/easygwt/images/login_banner.png";
	private Authenticator authenticator;
	private VerticalPanel loginBannerVerticalPanel;
	private FormPanel loginFormPanel;
	private ToolBar loginDialogToolBar;
	
	private LabelField loginFailureMessageLabelField;
	private TextField<String> usernameTextField;
	private TextField<String> passwordTextField;

	private ButtonBar loginButtonBar;
	private Button loginButton;
	private Button resetButton;
	
	private Button signupButton;
	private Button forgotPasswordButton;
	
	/**
	 * GWF Login Dialog
	 * 
	 * @param Authenticator instance
	 */
	public LoginDialog(Authenticator authenticator) {
		
		super();
		
    	this.authenticator = authenticator;
		setLayout(new FitLayout());
		setButtonAlign(HorizontalAlignment.CENTER);
		setIcon(AbstractImagePrototype.create(Util.getIcons().lock()));
		setClosable(false);
		setSize(420, 255);
		setButtons("");
		setResizable(false);
		setBottomComponent(getLoginDialogToolBar());

	    addLoginComponents();

	    show();
		center();
		
	}
	
	/**
	 * Get a Login Dialog ToolBar containing the signup and the forgot password buttons
	 * 
	 * @return A ToolBar
	 */
	public ToolBar getLoginDialogToolBar() {

		loginDialogToolBar = new ToolBar();
		
		signupButton = new Button(Util.getConstants().signupButtonHeading());
		signupButton.setIcon(AbstractImagePrototype.create(Util.getIcons().user()));
	    loginDialogToolBar.add(signupButton);
	    
	    loginDialogToolBar.add(new FillToolItem());
	    
	    forgotPasswordButton = new Button(Util.getConstants().forgotPasswordButtonHeading());
	    forgotPasswordButton.setIcon(AbstractImagePrototype.create(Util.getIcons().messaging()));
	    
	    loginDialogToolBar.add(forgotPasswordButton);
	    
	    return loginDialogToolBar;
	    
	}
	
	/**
	 * Gets the Login FormPanel
	 * 
	 * @return A Login FormPanel
	 */
	public void addLoginComponents () {
		
		loginFormPanel = new FormPanel();
		loginFormPanel.setFrame(false);
		loginFormPanel.setHeaderVisible(false);
		loginFormPanel.setBodyBorder(false);
		loginFormPanel.setLabelAlign(FormPanel.LabelAlign.LEFT);
		loginFormPanel.setFieldWidth(250);
		loginFormPanel.setBodyBorder(false);
		loginFormPanel.setStyleAttribute("padding", "10 0 0 0");
		loginFormPanel.setWidth(380);
		
	    loginButtonBar = new ButtonBar();
	    loginButtonBar.setSpacing(5);
	    loginButtonBar.setAlignment(HorizontalAlignment.RIGHT);
	    loginButtonBar.setBorders(false);
	    loginButton = new Button(Util.getConstants().loginButtonHeading());
	    loginButton.setIcon(AbstractImagePrototype.create(Util.getIcons().key()));
	    
	    loginButtonBar.add(loginButton);
	    
	    resetButton = new Button(Util.getConstants().resetButtonHeading());
	    resetButton.setIcon(AbstractImagePrototype.create(Util.getIcons().pageRefresh()));
	    resetButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				loginFormPanel.reset();
			}
			
		});
	    
	    loginButtonBar.add(resetButton);
	    AdapterField adapterField = new AdapterField(loginButtonBar);
	    adapterField.setLabelSeparator("");
	
	    loginFailureMessageLabelField = new LabelField(Util.getConstants().loginFailureMessageLabelField());
	    loginFailureMessageLabelField.setStyleAttribute("color", "red");
	    loginFailureMessageLabelField.setVisible(false);
	    
		usernameTextField = new TextField<String>();
		usernameTextField.setAllowBlank(false);
		usernameTextField.setFieldLabel(Util.getConstants().usernameTextFieldFieldLabel());
		usernameTextField.setValidator(new Validator() {
			
			@Override
			public String validate(Field<?> field, String value) {
				
				/**
				 * Very simple email address validation. Probably not the most accurate but close
				 * to what we need for now.
				 */
				if ( !value.matches(".*@.*\\..{2,3}") ) {
					return Util.getConstants().usernameTextFieldValidationMessage(); 
				}
				
				return null;
				
			}
			
		});
		
		final String usernameRememberMe = (String) StateManager.get().get("remember_me");
		
		if ( usernameRememberMe != null && !usernameRememberMe.isEmpty() ) {
			
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				
				@Override
				public void execute() {
					usernameTextField.setValue(usernameRememberMe);
					passwordTextField.focus();
					
				}
			});
			
		} else {
			usernameTextField.focus();
		}
		
		passwordTextField = new TextField<String>();
		passwordTextField.setFieldLabel(Util.getConstants().passwordTextFieldFieldLabel());
		passwordTextField.setPassword(true);
		passwordTextField.setAllowBlank(false);
		passwordTextField.addKeyListener(new KeyListener() {
			
            public void componentKeyPress(ComponentEvent event) {
            	
                if (event.getKeyCode() == KeyCodes.KEY_ENTER) {
                	authenticator.login();
                }
                
            }
            
        });
		
		loginFormPanel.add(loginFailureMessageLabelField);
		loginFormPanel.add(usernameTextField);
		loginFormPanel.add(passwordTextField);
	    loginFormPanel.add(adapterField);
	    
	    FormButtonBinding formButtonBinding = new FormButtonBinding(loginFormPanel);
	    formButtonBinding.addButton(loginButton);
	    formButtonBinding.addButton(resetButton);
	    
	
	    VerticalPanel fieldsAndButtonPanel = new VerticalPanel();
	    fieldsAndButtonPanel.add(loginFormPanel);
	
	    loginBannerVerticalPanel = new VerticalPanel();
	    loginBannerVerticalPanel.setTableWidth("100%");
	    loginBannerVerticalPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
	    loginBannerVerticalPanel.add(new Image(
	    		GWT.getHostPageBaseURL() + LOGIN_BANNER_IMAGE));
	    loginBannerVerticalPanel.add(fieldsAndButtonPanel);
	    
	    add(loginBannerVerticalPanel);
	    
	    loginButton.addListener(Events.Select, new Listener<ButtonEvent>() {
	
			@Override
			public void handleEvent(ButtonEvent be) {
				authenticator.login();
			}
	    	
		});
	    
	}

	/**
	 * @return the authenticator
	 */
	public Authenticator getAuthenticator() {
		return authenticator;
	}

	/**
	 * @param authenticator the authenticator to set
	 */
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	/**
	 * @return the loginBannerVerticalPanel
	 */
	public VerticalPanel getLoginBannerVerticalPanel() {
		return loginBannerVerticalPanel;
	}

	/**
	 * @param loginBannerVerticalPanel the loginBannerVerticalPanel to set
	 */
	public void setLoginBannerVerticalPanel(VerticalPanel loginBannerVerticalPanel) {
		this.loginBannerVerticalPanel = loginBannerVerticalPanel;
	}

	/**
	 * @return the loginFormPanel
	 */
	public FormPanel getLoginFormPanel() {
		return loginFormPanel;
	}

	/**
	 * @param loginFormPanel the loginFormPanel to set
	 */
	public void setLoginFormPanel(FormPanel loginFormPanel) {
		this.loginFormPanel = loginFormPanel;
	}

	/**
	 * @return the loginFailureMessageLabelField
	 */
	public LabelField getLoginFailureMessageLabelField() {
		return loginFailureMessageLabelField;
	}

	/**
	 * @param loginFailureMessageLabelField the loginFailureMessageLabelField to set
	 */
	public void setLoginFailureMessageLabelField(
			LabelField loginFailureMessageLabelField) {
		this.loginFailureMessageLabelField = loginFailureMessageLabelField;
	}

	/**
	 * @return the usernameTextField
	 */
	public TextField<String> getUsernameTextField() {
		return usernameTextField;
	}

	/**
	 * @param usernameTextField the usernameTextField to set
	 */
	public void setUsernameTextField(TextField<String> usernameTextField) {
		this.usernameTextField = usernameTextField;
	}

	/**
	 * @return the passwordTextField
	 */
	public TextField<String> getPasswordTextField() {
		return passwordTextField;
	}

	/**
	 * @param passwordTextField the passwordTextField to set
	 */
	public void setPasswordTextField(TextField<String> passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	/**
	 * @return the loginButtonBar
	 */
	public ButtonBar getLoginButtonBar() {
		return loginButtonBar;
	}

	/**
	 * @param loginButtonBar the loginButtonBar to set
	 */
	public void setLoginButtonBar(ButtonBar loginButtonBar) {
		this.loginButtonBar = loginButtonBar;
	}

	/**
	 * @return the loginButton
	 */
	public Button getLoginButton() {
		return loginButton;
	}

	/**
	 * @param loginButton the loginButton to set
	 */
	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}

	/**
	 * @return the resetButton
	 */
	public Button getResetButton() {
		return resetButton;
	}

	/**
	 * @param resetButton the resetButton to set
	 */
	public void setResetButton(Button resetButton) {
		this.resetButton = resetButton;
	}

	/**
	 * @return the signupButton
	 */
	public Button getSignupButton() {
		return signupButton;
	}

	/**
	 * @param signupButton the signupButton to set
	 */
	public void setSignupButton(Button signupButton) {
		this.signupButton = signupButton;
	}

	/**
	 * @return the forgotPasswordButton
	 */
	public Button getForgotPasswordButton() {
		return forgotPasswordButton;
	}

	/**
	 * @param forgotPasswordButton the forgotPasswordButton to set
	 */
	public void setForgotPasswordButton(Button forgotPasswordButton) {
		this.forgotPasswordButton = forgotPasswordButton;
	}

	/**
	 * @param loginDialogToolBar the loginDialogToolBar to set
	 */
	public void setLoginDialogToolBar(ToolBar loginDialogToolBar) {
		this.loginDialogToolBar = loginDialogToolBar;
	}
	
}
