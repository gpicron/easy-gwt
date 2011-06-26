package com.emitrom.easygwt.client.core.authentication;

import com.emitrom.easygwt.wf.client.authentication.Authenticator;
import com.emitrom.easygwt.wf.client.widgets.dialogs.LoginDialog;
import com.extjs.gxt.ui.client.state.StateManager;

public abstract class SampleLoginAuthenticator implements Authenticator {

	protected LoginDialog loginDialog;
	
	public SampleLoginAuthenticator() {
		loginDialog = new LoginDialog(this);
	}
	
	@Override
	public void login() {
		
		String userName = loginDialog.getUsernameTextField().getValue();
		String passWord = loginDialog.getPasswordTextField().getValue();
		
		if (userName.equals("demo@easy-gwt.com") && passWord.equals("demo")) {
			StateManager.get().set("remember_me", userName);
			onSuccess();
		} else {
			loginDialog.getLoginFailureMessageLabelField().setVisible(true);
		}
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	}

}
