package com.emitrom.easygwt.client.views;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.easygwt.client.resources.SampleIcons;
import com.emitrom.easygwt.client.resources.I18N.SampleConstants;
import com.emitrom.easygwt.client.views.wizard.FinishPage;
import com.emitrom.easygwt.client.views.wizard.UserInformationPage;
import com.emitrom.easygwt.client.views.wizard.WelcomePage;
import com.emitrom.easygwt.wf.client.column.core.ColumnView;
import com.emitrom.easygwt.wf.client.widgets.dialogs.ErrorDialog;
import com.emitrom.easygwt.wf.client.wizard.WizardDialog;
import com.emitrom.easygwt.wf.client.wizard.WizardModel;
import com.emitrom.easygwt.wf.client.wizard.WizardPage;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;

public class UsersView extends ColumnView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;
	private SampleIcons icons;
	private Button addButton;
	private Button deleteButton;
	
	private Grid<BeanModel> usersGrid;
	private ListStore<BeanModel> usersGridListStore;
	
	private WelcomePage welcomePage;
	private UserInformationPage userInformationPage;
	private FinishPage finishPage;

	@Inject
	public UsersView(SampleConstants constants, SampleIcons icons,
			ListStore<BeanModel> usersGridListStore) {
		this.constants = constants;
		this.icons = icons;
		this.usersGridListStore = usersGridListStore;
		setLayout(new FitLayout());
	}

	@Override
	public void onRender() {

		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		contentPanel.setLayout(new FitLayout());
		
		addButton = new Button();
		addButton.setIcon(AbstractImagePrototype.create(icons.add()));
		addButton.setToolTip(constants.usersGridAddButtonToolTip());
		
		deleteButton = new Button();
		deleteButton.setIcon(AbstractImagePrototype.create(icons.delete()));
		deleteButton.setToolTip(constants.usersGridDeleteButtonToolTip());
		deleteButton.setEnabled(false);
		
		ToolBar toolBar = new ToolBar();
		toolBar.add(addButton);
		toolBar.add(new FillToolItem());	
		toolBar.add(deleteButton);
		
		contentPanel.setTopComponent(toolBar);
		
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		
		ColumnConfig columnConfig = new ColumnConfig();
		columnConfig.setId("firstName");
		columnConfig.setHeader(constants.usersGridFirstNameColumnHeader());
		columns.add(columnConfig);
		
		columnConfig = new ColumnConfig();
		columnConfig.setId("lastName");
		columnConfig.setHeader(constants.usersGridLastNameColumnHeader());
		columns.add(columnConfig);

		columnConfig = new ColumnConfig();
		columnConfig.setId("userName");
		columnConfig.setHeader(constants.usersGridUserNameColumnHeader());
		columns.add(columnConfig);

		columnConfig = new ColumnConfig();
		columnConfig.setId("email");
		columnConfig.setHeader(constants.usersGridEmailColumnHeader());
		columns.add(columnConfig);

		ColumnModel columnModel = new ColumnModel(columns);
		
		usersGrid = new Grid<BeanModel>(usersGridListStore, columnModel);
		usersGrid.setStripeRows(true);
		usersGrid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		usersGrid.getView().setAutoFill(true);
		usersGrid.setAutoExpandColumn("email");
		
		contentPanel.add(usersGrid);
		add(contentPanel);
		
		usersGridListStore.add(BeanModelLookup.get().getFactory(Users.class).createModel(getUsersList()));

		addListeners();
		
	}
	
	private void addListeners() {
		
		addButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				
				WizardDialog wizard = new WizardDialog() {
					
					@Override
					public void finish() {
						
						Users user = new Users();
						WizardModel wizardModel = (WizardModel) model;
						user.setFirstName((String) wizardModel.getProperty("firstName"));
						user.setLastName((String) wizardModel.getProperty("lastName"));
						user.setUserName((String) wizardModel.getProperty("userName"));
						user.setEmail((String) wizardModel.getProperty("email"));
						
						usersGridListStore.add(BeanModelLookup.get().getFactory(Users.class).createModel(user));
						
					}
					
				};
				wizard.setIcon(AbstractImagePrototype.create(icons.house()));
				wizard.setHeading("New Users Wizard");
				
				List<WizardPage> wizardPageList = new ArrayList<WizardPage>();
				
				welcomePage = new WelcomePage(icons);
				welcomePage.setPageDescription("Welcome");
				welcomePage.setStepDescription("Welcome");
				
				userInformationPage = new UserInformationPage(constants, icons);
				userInformationPage.setPageDescription("User Information");
				userInformationPage.setStepDescription("User Information");
				
				finishPage = new FinishPage(icons, constants);
				finishPage.setPageDescription("Summary");
				finishPage.setStepDescription("Summary");
				
				wizardPageList.add(welcomePage);
				wizardPageList.add(userInformationPage);
				wizardPageList.add(finishPage);
				wizard.addPages(wizardPageList);
				
				wizard.show();

			}
			
		});
		
		deleteButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				
				BeanModel beanModel = usersGrid.getSelectionModel().getSelectedItem();
				
				ErrorDialog errorDialog = new ErrorDialog("Fatal Error", "Illustrating how to display an error using the" +
						"ErrorDialog Widget", new RuntimeException("Sample ErrorDialog Exception"));
				errorDialog.show();
				
				if (beanModel != null) {
					usersGridListStore.remove(beanModel);
				}
				
				deleteButton.setEnabled(false);
				
			}
		});
		
		usersGrid.addListener(Events.RowClick, new Listener<GridEvent<BeanModel>>() {

			@Override
			public void handleEvent(GridEvent<BeanModel> be) {
				
				BeanModel beanModel = usersGrid.getSelectionModel().getSelectedItem();
				
				if (beanModel != null) {
					deleteButton.setEnabled(true);
				} else {
					deleteButton.setEnabled(false);
				}
				
			}
			
		});
		
	}
	
	public class Users implements BeanModelTag {
		
		private String firstName;
		private String lastName;
		private String userName;
		private String email;
		
		public Users() {}

		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName the firstName to set
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastName the lastName to set
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @return the userName
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * @param userName the userName to set
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		
	}
	
	private List<Users> getUsersList() {
		
		List<Users> usersList = new ArrayList<Users>();
		
		for (int i=0; i<=10; i++) {
			
			Users user = new Users();
			user.setFirstName("Easy " + i);
			user.setLastName("GWT");
			user.setUserName("easy-gwt");
			user.setEmail("easy-gwt@easy-gwt.com");

			usersList.add(user);
		}
		
		return usersList;
		
	}

}
