dailyExpense
============
IST 380B Mobile Application
Chun-Ming Chen
1.  Overview of the project
I choose to Daily Expense as my project of mobile application because there some apps I used before were not so customized that were not useful. For example, the type or category of a transaction is fixed in some apps. Everyone has a different view of the type of a transaction. Thus, if there are some functions user can adjust themself may be more useful.
In the scenario of my application, there will three main activities. They are Transaction, Report, and Setting. Transaction activity is used to record expense, Report activity is used to analyze transactions, and Setting activity is used to have some extra functions. 
	However, because my knowledge of Java programming is limited and the class duration is short, I decide to start it by creating a simple native application with basic functions. The application will be able to record or delete transactions with dynamic date user choose, and user can build his/her own category he/she likes. In order to handle these data in device, the local database will be implemented.  
2.	Required Android Permissions and why the app needs them.
There no required web service and built in features such as camera, Bluetooth are needed. But, the permission of internet connection is needed when update this application.
3.	Required Web Services, e.g., GIS, Google, etc., and why the app needs them.
None of these services are used in my application. However, I would like to add reporting function in the future that some web services may be used. For example, the application will send monthly summary of user’s expense to user’s email. 
4.	Required, built-in, features/devices: GPS, Camera, Gallery, Contacts, SMS, etc. 
None of these services are used in my application.
5.	Testing procedures (emulators, device tests, unit tests, etc.)
I use both emulator and device to test my application. The testing procedures as following:
1).	Open Daily Expense application
2).	Switch to Category tab page from default Transaction page
3).	Add at least one category
4).	Back to Transaction page and choose a date and press add button to add a transaction
5).	In adding a transaction page, input item name and amount and choose a category which you create in step (3) from drop down menu
6).	Viewing what you add in Transaction page
7).	Delete a transaction you added
8).	Repeat step (3) to step (7)  
6.	Screenshots
   
  
7.	Known bugs, limitations, and problems.
So far, there are some problems needed to solve. First, there is no default category for user to choose that will make application terminated when adding a transaction. Second, alert dialogue should be implemented in order prevent some error situation. For example, when user click the delete button, there should be a dialogue pop up that ask user whether they really want to delete it. Sometime user may click the button by mistake. Further, screen can’t refresh instantly after deleting a transaction that should be fixed.
8.	Set of features to be implemented or next steps
In the future, the reporting function should be added in in order to let user analyze their expense. Also, there should be an export function to send analysis to output report to other format such as email or Excel.
9.	License and contributors

