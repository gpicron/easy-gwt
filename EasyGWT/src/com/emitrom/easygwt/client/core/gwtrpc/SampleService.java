package com.emitrom.easygwt.client.core.gwtrpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("rpc")
public interface SampleService extends RemoteService {
	String sampleRPC(String name) throws IllegalArgumentException;
}
