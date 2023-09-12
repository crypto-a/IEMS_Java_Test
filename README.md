# IEMS_Java_Test
## By _Ali Rahbar_
### Email: alirhabar2005@gmail.com
#### August 2023

## Introduction
___
This project is a full scale Java Software, designed for the company IEMS Solutions to test their web application and manage the issues and problems that occur when testing. The software automating testing by using the selenium web-driver and records all the data for the tests in the system and saves it, allowing future access to the data! Issues found on the platform are also listed and shown to the developers so the issues can be solved!


## Software Components
The software was designed in a way that allows efficient runtime as well as scalability! Difrent modules are grouped into doffrent classes and they communicate with pne another to complete tasks!
The follwoing are the main classes in the code and what are their main functionalities:
 - __Main__: The main class has the only static method in the code and its used for initializing the run! Info like software version and debug mode are also recorded in that method!
 - __Runner__: The Runner class manages the processes that happen in the code! Things like update checking, and updating the UI is managed by that code! In the runner objetc each page in the softweare has a mehtod witch is runned depending on the __code state__ of the software!
 - __TestEngine__: The TestEngine is in charge of keeping the data object like the tests and issues. it also provide backend data manipulations like sorting!
 - __TestObject__: The test object is the general object for all the tests that happen in the code! A list of the sub tests and the issues are also listed in this object!
 - __TestElement__: Test Element is incharge of the mini tests that happen. The testing modules give the class data and ot checks for mitakes and errors and reports them! 
 - __IssueElement__: This objetc is inchargfe of the issue! It records the data as well as the scenario Step! When issue is closed it also records who closed the message and when they did it!
 - __Database__: The database Obejct is incharge of the dtaabase CRUD opereations! It has multiple ,methods for diffrent applications in the software! 
 - __ChangeStreamUpdater__: This class is incharge of keeping the data updated with the central databse and update changes when they happen!
 - __SearchEngine__: The Search Engine class is incharge of the search feature! Every obejct that is created is indexed in the class so when searched the c,ss can find it!
 - __Email__: The Email class is inchzrge of communication with users via email!
 - __User__: The user object keeps track of the user info!
 - __PasswordManager__: The password manager is in-charge of saving the users password on their own device!
 - __GUI__: This class is incharge of the UI and how it should be displayed! 
 - __Form Files__: The form files are the graphics of the pages! These files can only be edited in IntelliJ!
 - __Event__: The Event object is incharge of the communications between the backe nfd and the front end!

## Software States
I came Up with the Idea Of Software states as It helps different modules of the code communicate more efficiently! The Graphics ```GUI.java``` file and the file in-charge of the operations in the back ```Runner.java``` commmunicate through an object called ```Event.java```. These files push doffrent bits of information into the event object and the event object gives both sides access to the file. Software states or code states are also onw of this information. As the Software has diffrent pages, code States are used to help navigate and help the code know what page the user is on. Each the following integers have different meanings in the code states: 
 - __Code State ```0```:__ This is the user "Login page"
 - __Code State ```1```:__ This is the "main page"
 - __Code State ```2```:__ This is the "create new test page"
 - __Code State ```3```:__ This is the "Test Object Display Page"
 - __Code State ```4```:__ This is the "Test Element Display Page"
 - __Code State ```5```:__ This is the "Issue Element Display Page"
 - __Code State ```6```:__ This is the "settings page"

## Change Streams 
Change streams is a new feature provided by mongodb atlas that allows clients to set up a watch and get notified when a change happens in the databse and to the object! The Code for this section of the code is in the ChangeStreamUpdater File! The code creates a thread for each collectiong and updates the arrayLists in the TestEngine object! The code is also designed in a way that is does not triger when the cleint ots self pushes a change!

## Automated Tests
The Process of making new tests are relativly easy! All the commades and pricesses that are required for testing software have been writing in the form o methods for the developers! What they have to do is to add the code thay have to a method called __test__! the code will run the commands and test the software for them!

## Scenarios
The scenario is a number that shows the number of tests that that test was when testing! Thus the load sceanrio feature follows the steps and stops at the scenario the error haopend at sot eh developer can see the error!