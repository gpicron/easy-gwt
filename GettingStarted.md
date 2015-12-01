### Setup your development environment ###

  * Download and install Eclipse.
  * Install the Eclipse GWT Plugin.
  * Create a new GWT Project.
  * Download and Configure GXT.
  * Download the latest stable EasyGWT Jar and add it to your project's class path.
  * Add :

```
   <inherits name='com.emitrom.easygwt.wf.EasyGWT'/>
```

to your X.gwt.xml.

  * You should by now have the following dir hierarchy under war:

```
     war
        => resources
           => gxt
```

  * Add a couple of directories and the final result should look like this excluding the gwt4air directory (Alain from gwt4air is working very hard to provide Easy integration with us).

![http://easy-gwt.googlecode.com/svn/trunk/src/com/emitrom/easygwt/client/resources/images/war_dir_structure.png](http://easy-gwt.googlecode.com/svn/trunk/src/com/emitrom/easygwt/client/resources/images/war_dir_structure.png)

**_Notice that under images you have sample ones that are being used for the sake of this Guide. In your case if you wanted to replace the Left and Right Banner as well as all the other images used by the framework all you have to do is to add them under war/resources/easygwt/images._**

  * In your GWT project's entry point class, add:

```
   public void onModuleLoad() {
	ColumnViewPort columnView = ColumnViewPort.getInstance();
   }
```

  * Run your project.

### Add Navigation items ###

> The Navigation Panel is composed of Parent Items. Each Parent represents an element in an Accordion Layout. Parents are composed consequently of children which will be represented under the parent by an icon and text.

```
   List<ColumnNavigationParent> navigationParentsList = new ArrayList<ColumnNavigationParent>();
```

```
   ColumnNavigationParent parent1 = new ColumnNavigationParent();
   parent1.setHeading(constants.parentOne());
   parent1.setIcon(AbstractImagePrototype.create(icons.chartBar()));
```

> In this case, we've created a Column Navigation Parent instance and set the heading as well as the icon that will be used when the application loads up.

> The next step is to simply add a child instance to the recently created parent. See below:

```
   ColumnNavigationChild parent1Child1 = new ColumnNavigationChild(injector.getMyView(), 
      constants.parentOneChildOne());
   parent1Child1.setIcon(icons.house());
   parent1.addNavigationChild(parent1Child1);
```

> You have the ability as in the parent case to set a heading as well as an icon for all children.

> Lastly you simply add the list of navigation parents to the column view instance obtained initially:

```
   columnView.addNavigationItems(navigationParentsList);
```

### Create your first view ###

> A view should extend the abstract class Column View provided by the framework. A view will be displayed by the framework in the Center Panel and follows a Card Layout implementation. Views are invoked when an item in the Navigation Panel is clicked on. See below:

```
   public class MyView extends ColumnView {

	@Override
	public void onRender() {

		Window window = new Window();
		window.setSize(300, 300);
		window.setHeading("My Window");
		
		add(window);
		window.show();

        }

	@Override
	public void prepareToHideView() {}

	@Override
	public void prepareToShowView() {}

   }
```

> View elements should be added to the onRender method. This method is only called once by the framework and it's typically good enough for most implementations. Should you need access to the About to Show and About to Hide Events for the view, you can override prepareToHideView as well as prepareToShowView respectively.

### Using GIN for dependency injection ###

> The framework does not require the usage of dependency injection, however it is strongly encouraged as well as an MVP implementation for the entire application. The examples provided above used an injector to inject the View. See below:

```
   ColumnNavigationChild parent1Child1 = new ColumnNavigationChild(injector.getMyView(), 
      constants.parentOneChildOne());
```

> The same could have been accomplished by doing:

```
   ColumnNavigationChild parent1Child1 = new ColumnNavigationChild(new MyView(), 
      constants.parentOneChildOne());
```

> Dependency injection can easily be achieved via GIN. For cases where the View requires Custom Widgets, Constants as well as Images and other elements this can be achieved as follows:

```
public class MyView extends ColumnView {

	private static final long serialVersionUID = 1L;
	private SampleConstants constants;

	@Inject
	public MyView(SampleConstants constants) {
		this.constants = constants;
	}

   }
```

> For more details on how to use GIN, reference [GIN](http://code.google.com/p/google-gin/)