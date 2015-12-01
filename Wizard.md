Implementing a Wizard has never been easier. The Wizard consists of three main components all of which are depicted below:

  * Wizard Dialog.
  * Wizard Pages.
  * Wizard Model.

### Wizard Dialog ###

The Wizard Dialog is the entry point to the creation of a new Wizard. A new instance can be obtained by:

```
   WizardDialog wizard = new WizardDialog() {
					
      @Override
      public void finish() {
         // Invoked when the user successfully completes all the Wizard Pages and clicks on
         // the Finish button.
      }
   }

```

### Wizard Pages ###

Wizard Pages are the elements that compose the Wizard. To create a Wizard Page all that is needed is to create a class that extends Wizard Page.

```
   public class WelcomePage extends WizardPage {

   }
```

A Wizard Page typically overrides two methods:

```
   @Override
   public void renderPage() {
      // Will be invoked only once when the Page is rendered as the user
      // is navigating through the different pages composing the Wizard.
   }

   @Override
   public void saveModel() {
      // This method is typically used to save the state of the model and
      // it's automatically invoked by the framework whenever the current page is about to hide.
   }
```

Adding pages to the Wizard is extremely simple:

  * Use the convenience method `addPage` that is part of the Wizard Dialog implementation or invoke `addPages` and pass in a List of Wizard Pages.

### Wizard Model ###

A Wizard model acts as a persistence mechanism that can be accessed from all pages to save the state of each one of the Page's elements.

There is an already implemented Wizard Model class that provides a convenience method to store properties as strings in a Map with values being objects allowing the developer to essentially save the state of pretty much anything. Should the developer decide to use a custom implementation for the model all that is needed is to ensure that the custom model implements WizardModelInterface.

### Complete Example ###

The example provided below was extracted from the Demo Application:

```
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

```

### Summary ###

  * Create a Wizard Dialog instance.
  * Create Wizard Pages.
  * If needed save the Wizard Pages elements's data in the model.
  * When the user clicks on Finish act accordingly.

_**Now, that was easy, wasn't it?**_