# Project_appDev CR
Project for the "Mobile Apps development" class for IOS 3.

Mobile Apps development - lab3

CR

In this repository, you will find my project for the lab3.

The "app-release.apk" is the released version of the apk, and the working one.

The "lab3_Clément_Ramond.apk" is the debug version, is cannot be installed.

Now, we will cover the point asked in the subject.

### Check the user starting the app

To make sure the user is the right one, we use the Android's confirm credential the user use for his device.

The app cannot be used if no authentification method is activated.

Several kind of lock cna be used : from the pin code to the fingerprint, if activated.

The application will use the type of lock that the device uses.

(I cannot illustrate this part since my phone don't accept screenshot of such important screen)

### How do you securely save the user's data on the device

To store data, there is several file location possible. The security of the data depends on the type of location used.

In this case I used the App-specific storage, as it's name implies, only the app will be able to access this file, by doing so, we reduce the risk by denying the access to any other app on the device.

### How did you hide the API url

For that purpose, I hid the URL in an environmental variable, making sure that only the machine knows it.

More precisely, in the gradle.properties. After that we can access the URL while keeping it secret.

![image](https://user-images.githubusercontent.com/75326864/110254646-4a176f00-7f90-11eb-8f96-6ac6d61a1ec8.png)

![image](https://user-images.githubusercontent.com/75326864/110254664-56033100-7f90-11eb-9491-811b2e3b581d.png)
 
![image](https://user-images.githubusercontent.com/75326864/110254672-5ef40280-7f90-11eb-860c-17a9074b8aee.png)

### Screenshot of my application and functionment

Here, we have the logging screen of the application :

![image](https://user-images.githubusercontent.com/75326864/110254820-1d178c00-7f91-11eb-9882-af6cdee31fe7.png)

The welcome message turn into a warning message if no authentification method is selected.

![image](https://user-images.githubusercontent.com/75326864/110254932-9adb9780-7f91-11eb-9ee6-9f3a8d3d3d05.png)

Now, if we have an authentification method, you can unlock the app, and access the main page :

![image](https://user-images.githubusercontent.com/75326864/110254969-c068a100-7f91-11eb-8d14-ade0ac2d1477.png)

![image](https://user-images.githubusercontent.com/75326864/110254971-c8c0dc00-7f91-11eb-8d77-ac75bd396e4f.png)

On the main page, we can see all the account, as well as the status of the application, whether your are offline or online.

The button allows you to do several things:

- Return to the main menu.
- Refesh the account, this function only work if you are online, it can also be used to pass from offline state to online state if you reactivated the connection.
- Delete data allow you to delete all data from the device, if use in offline mode, after reconnecting to the app (still in offline mode) you won't see any account.

![image](https://user-images.githubusercontent.com/75326864/110255116-821fb180-7f92-11eb-823e-743b5e692671.png)

No data are displayed, because we deleted the data. (Note that the account will be displayed, even after deleting the data, until you hit return and try to reconnect again).

(Exemple of an offline case:)

![image](https://user-images.githubusercontent.com/75326864/110255025-11789500-7f92-11eb-9334-2a4578539961.png)

There is also some message, such as storage timing, of additional information about the status.







