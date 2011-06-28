package com.emitrom.easygwt.client.views;

import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.column.core.ColumnView;
import com.emitrom.easygwt.wf.client.widgets.dialogs.ErrorDialog;
import com.google.inject.Inject;

public class MyErrorDialogView extends ColumnView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;

	@Inject
	public MyErrorDialogView(SampleConstants constants) {
		this.constants = constants;
	}

	@Override
	public void onRender() {
		System.out.println("onRender " + constants.sampleConstantString());
		
		ErrorDialog errorDialog = 
		    new ErrorDialog(
		        "Fatal Error", 
		        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
		        new RuntimeException("Yo Gabba Gabba"));
		
		errorDialog.show();
	}

}
