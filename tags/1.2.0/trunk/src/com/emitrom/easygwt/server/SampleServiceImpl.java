package com.emitrom.easygwt.server;

import com.emitrom.easygwt.client.core.gwtrpc.SampleService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SampleServiceImpl extends RemoteServiceServlet implements
		SampleService {

	public String sampleRPC(String input) throws IllegalArgumentException {
		return input;
	}

}
