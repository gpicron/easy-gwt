**An easy to use GWT Web Framework!**

**Demo: http://easy-gwt.appspot.com/**

  * _Username_: **demo@easy-gwt.com**
  * _Password_:  **demo**

**Why EasyGWT?**

If you have ever worked on multiple projects for large enterprise applications you've probably found yourself writing and wiring a view usually containing the commonly used HTML column layout. You typically end up having the need for a North Panel that contains some actions. A South Panel is usually also employed to display hyper links containing things such as "Contact Us", "Report Issues", etc. Then there is the highly used Navigation Panel which contains hyperlinks that upon being clicked on display a view in a Center Panel.

The process of creating this boilerplate code can at times feel repetitive, time consuming and if you work for a large company then your department might end up having a mini-framework and a different organization using something completely different. Maintaining such environments quickly become cumbersome, duplication starts to trickle in and soon you have a problem that gets exponentially bigger as the organizations and company grows.

The need to have a common platform upon which to build enterprise applications then becomes apparent and the benefits of such approach are huge. To name a few:

  * Applications built with a common platform provide a common look and feel for both internal as well as external customers.
  * Patches and new features are instantly available to all developers across the company.
  * The Go To Market time for new applications decreases drastically while the stability of the application itself is greater since it uses a well tested and used through out framework.

**Enter EasyGWT**

How easy?

  1. Download the latest stable [easy-gwt](http://code.google.com/p/easy-gwt/downloads/list) and add it to your project's classpath.
  1. Add the following line to your X.gwt.xml
```
  <inherits name='com.emitrom.easygwt.wf.EasyGWT'/>
```
  1. One line of code is all you need to get started:

```
   ColumnViewPort columnView = ColumnViewPort.getInstance();
```

![http://easy-gwt.googlecode.com/svn/trunk/src/com/emitrom/easygwt/client/resources/images/easygwt.column.png](http://easy-gwt.googlecode.com/svn/trunk/src/com/emitrom/easygwt/client/resources/images/easygwt.column.png)

The view illustrated was obtained by:

```

	public void onModuleLoad() {
		
		/**
		 * Column View Sample
		 */
		ColumnViewPort columnView = ColumnViewPort.getInstance();
		
		/**
		 * Add items to the Action Tool Bar.
		 */
		columnView.getNorthPanel().getNorthPanelToolBar().add(new FillToolItem());
				
                LabelField loggedInUser = new LabelField();
		loggedInUser.setValue("<b>" + loginDialog.getUsernameTextField().getValue() + "<b/>"); 
				
		Button logoutButton = new Button();
		logoutButton.setIcon(AbstractImagePrototype.create(icons.doorOut()));
		logoutButton.setToolTip("Logout");
		logoutButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				logout();
			}
		});
				
		columnView.getNorthPanel().getNorthPanelToolBar().add(loggedInUser);
		columnView.getNorthPanel().getNorthPanelToolBar().add(new SeparatorToolItem());
		columnView.getNorthPanel().getNorthPanelToolBar().add(logoutButton);
		
		/**
		 * Build Navigation
		 */
		List<ColumnNavigationParent> navigationParentsList = new ArrayList<ColumnNavigationParent>();
				
		ColumnNavigationParent parent1 = new ColumnNavigationParent();
		parent1.setHeading(constants.usersViewHeading());
		parent1.setIcon(AbstractImagePrototype.create(icons.chartBar()));
				
		ColumnNavigationChild parent1Child1 = new ColumnNavigationChild(injector.getUsersView(), 
						constants.usersViewHeading());
		parent1Child1.setIcon(icons.house());
		parent1.addNavigationChild(parent1Child1);
				
		navigationParentsList.add(parent1);

		columnView.addNavigationItems(navigationParentsList);
	}

```

**Module Dependencies**

  1. GWT 2.1.1
  1. GXT 2.2.0

**The module strives to:**

  1. Use GWT as the underlying framework on which to build a Rich Internet Application.
  1. Be simple to use.
  1. Provide the foundations necessary for Enterprise Applications avoiding duplication and time wasted during the initial phase of an Enterprise development project.

**What does the module provide:**

  * The commonly used column layout that can be seen in most web applications today.

  1. North Panel with Task Bar.
  1. East Panel.
  1. Navigation Panel.
  1. South Panel.
  1. Center Panel:

> -  Views are added to this panel.

> -  All views have instant access to:
```

   @Override
   public void onRender() {}

   @Override
   public void prepareToHideView() {}

   @Override
   public void prepareToShowView() {}

```

  * An implementation of the GWT Event Bus hence allowing the user to fire and handle events through out the application.

  * Four themes out of the box since the framework exposes the four CSS styles provided by GXT.

  * Login Dialog implementation with Signup and Forgot Password buttons already included.

  * The framework loads the left and right banner images as well as the login dialog image. All the developer needs to do is to simply create them and put them under the easyGWT resources in the war directory.

  * More importantly the framework allows the developer to concentrate on simply creating a List of Navigation Parents with their respective children containing each a view which is the part where the Software Engineer needs to really invest time and effort. Simply work on the views needed and all the other wiring is provided by the framework.