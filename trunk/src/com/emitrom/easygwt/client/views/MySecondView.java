package com.emitrom.easygwt.client.views;

import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.column.core.ColumnView;
import com.google.inject.Inject;

public class MySecondView extends ColumnView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;

	@Inject
	public MySecondView(SampleConstants constants) {
		this.constants = constants;
	}

	@Override
	public void onRender() {
		System.out.println("onRender " + constants.sampleConstantString());
	}

}
