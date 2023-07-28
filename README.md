# IEMS_Java_Test
## By _Ali Rahbar_
### August 2023

## Introduction
___
This project is a full scale Java Software, designed for the company IEMS Solutions to test their web application and manage the issues and problems that occur when testing. The software automates testing by using the selenium web-driver and records all the data for the tests in the system and saves it, allowing future access to the data! Issues found on the platform are also listed and shown to the developers so the issues can be solved!


## Software States
I came Up with the Idea Of Software states as It helps different modules of the code communicate more efficiently! The Graphics ```GUI.java``` file and the file in-charge of the operations in the back ```Runner.java``` commmunicate through an object called ```Event.java```. These files push doffrent bits of information into the event object and the event object gives both sides access to the file. Software states or code states are also onw of this information. As the Software has diffrent pages, code States are used to help navigate and help the code know what page the user is on. Each the following integers have different meanings in the code states: 
 - __Code State ```0```:__ This is the user "Login page"
 - __Code State ```1```:__ This is the "main page"
 - __Code State ```2```:__ This is the "create new test page"
 - __Code State ```3```:__ This is the "Test Object Display Page"
 - __Code State ```4```:__ This is the "Test Element Display Page"
 - __Code State ```5```:__ This is the "Issue Element Display Page"
 - __Code State ```6```:__ This is the "settings page"