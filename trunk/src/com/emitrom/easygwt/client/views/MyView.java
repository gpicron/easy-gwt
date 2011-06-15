package com.emitrom.easygwt.client.views;

import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.column.core.AccordionView;
import com.google.inject.Inject;

public class MyView extends AccordionView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;
	
	@Inject
	public MyView(SampleConstants constants) {
		this.constants = constants;
	}

	@Override
	public void onRender() {
		System.out.println("onRender " + constants.sampleConstantString());
	}
	
}