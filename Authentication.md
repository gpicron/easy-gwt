## Login Dialog ##

A Login Dialog is provided to aid the developer in the process of creating an authentication mechanism. The Login Dialog consists of:

a) A User Name field with a very simple email validator. This validator can be overridden by obtaining access to the field's instance and setting a new validation scheme.

b) A password field.

c) Validation is built in and the OK button will not be set to enabled until both User Name and Password fields have been filled out. This ensures that both field's values will never return null or empty.

d) There are also two additional buttons that are hidden by default containing a "Forgot Password" button as well as a "Sign Up" button.

The Login Dialog constructor takes an Authenticator interface as its only argument. See the constructor's signature below:

```
public LoginDialog(Authenticator authenticator) {
   ...
}
```

![http://easy-gwt.googlecode.com/svn/trunk/src/com/emitrom/easygwt/client/resources/images/login_dialog.png](http://easy-gwt.googlecode.com/svn/trunk/src/com/emitrom/easygwt/client/resources/images/login_dialog.png)

### Authenticator Interface ###

The framework provides an Authenticator interface with the following methods:

```
   public void login();
   public void logout();
   public void onSuccess();
```

Many Authentication implementations can be created to allow users to validate login credentials. The LoginDialog widget provides the necessary user interface functionality to help the developer create user credential validation in an easy and fast maner.

Let's create a Sample implementation that will act as one of the many possible ways in which the user could authenticate against the system.

```

public abstract class SampleLoginAuthenticator implements Authenticator {

   private LoginDialog loginDialog;
	
   public SampleLoginAuthenticator() {
      loginDialog = new LoginDialog(this);
   }
	
   @Override
   public void login() {
		
      String userName = loginDialog.getUsernameTextField().getValue();
      String passWord = loginDialog.getPasswordTextField().getValue();

      /**
        * Here you would typically have a call to a backend service for example (GWT-RPC)
        * or REST request and if successful invoke onSuccess. If not the LoginDialog
        * will provide the user with a failure message.
        * Simple validation in this case shown below:
        */		
      if (userName.equals("demo@easy-gwt.com") && passWord.equals("demo")) {
         onSuccess();
      } else {
         loginDialog.getLoginFailureMessageLabelField().setVisible(true);
      }
   }

   @Override
   public void logout() {
      // TODO Auto-generated method stub
   }

}

```

### Putting it all together ###

By creating classes that implement the Authenticator interface you can have a wide range of possibilities for Authentication mechanisms. You could implement an LDAP Authenticator for instance or an SSH based one for example. Both could be combined with the Login Dialog to give you access to an already implemented Login Dialog user interface widget with all the basic buttons needed and built in validation necessary for an enterprise application.

Let's finally combine all the things mentioned above and see what it would take to wire things together. In your entry's point class for your GWT project you would have something like the following under your _**onModuleLoad()**_ method:

```

   /**
   * Authenticate
   */
   new SampleLoginAuthenticator() {

      @Override
      public void onSuccess() {

         /**
           * Column View Sample
           */
         ColumnViewPort columnView = ColumnViewPort.getInstance();
         /**
           * Build your UI, add Navigation items, etc...
           */

      }

   };

```

### Summary ###

  * Implement Authenticator.
  * Override onSuccess.

_**Now, that was easy, wasn't it?**_