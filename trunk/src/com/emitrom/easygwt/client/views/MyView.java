package com.emitrom.easygwt.client.views;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.wf.client.column.core.ColumnView;
import com.emitrom.easygwt.wf.client.wizard.WizardDialog;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;

public class MyView extends ColumnView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;
	private SampleIcons icons;

	@Inject
	public MyView(SampleConstants constants, SampleIcons icons) {
		this.constants = constants;
		this.icons = icons;
	}

	@Override
	public void onRender() {

		WizardDialog wizard = new WizardDialog() {
			
			@Override
			public void finish() {
				System.out.println("FINISH IS HERE");
			}
		};
		wizard.setIcon(AbstractImagePrototype.create(icons.house()));
		wizard.setHeading("New SnapShot Wizard");
		
		wizard.addPage(new WelcomePage());
		
		wizard.show();
		
	}
	
	private class WelcomePage extends WizardPage {

	    public WelcomePage() {
	        setLayout(new CenterLayout());
	    }
	    
		@Override
		public boolean isValid() {
			return true;
		}

		@Override
		public String getPageDescription() {
			return "Welcome Page";
		}

		@Override
		public String getStepDescription() {
			return "Welcome";
		}

		@Override
		public void renderPage() {
			TextField<String> field = new TextField<String>();
			field.setFieldLabel("FIELD");
			add(field);
			
			// Do not forget to set this value, unless you want your page rendered
			// every single time it is entered.
			super.pageRendered = true;
		}

		@Override
		public void saveModel() {
			
		}
		
	}

}
