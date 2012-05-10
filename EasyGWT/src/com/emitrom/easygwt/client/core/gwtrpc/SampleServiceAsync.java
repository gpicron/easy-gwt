package com.emitrom.easygwt.client.core.gwtrpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SampleServiceAsync {

	public void sampleRPC(String string, AsyncCallback<String> asyncCallback);
	
}
