package Runner;

import Database.Database;
import GUI.Event.Event;
import GUI.GUI;
import TestEngine.TestObject.TestObject;
import User.User;
import TestEngine.TestEngine;

public class Runner
{
    private final TestEngine testEngine;
    private final Database database;
    private final Event event;
    private final User user;
    private final GUI gui;
    private Boolean isRunning;

    public Runner()
    {
        //Create the Event Object
        this.event = new Event();

        //Create the TestEngine Object
        this.testEngine = new TestEngine(this.event);

        //Create the Database

        this.database = new Database(this.testEngine);

        //Create the User Object
        this.user = new User(database, event);

        //Create the GUI Object
        this.gui = new GUI(this.event, this.testEngine, this.user);

        //create the change stream
        this.database.startChangeStreamSync();

        //Start the Code Process
        this.run();
    }

    private void run()
    {
        //Set up the is running value and set it to true
        this.isRunning = true;

        //Load Data from the database
        this.loadData();
        
        //Loop Forever
        while (this.isRunning)
        {
            //Make code Sleep
            this.sleep(30);

            //Get the Code State from the event Object
            int codeState = this.event.getCodeState();

            //Make different pages run based on the codeState
            switch (codeState)
            {
                case 0:
                    /* If we are in the SignIn page */

                    //Run the authentication method
                    this.userAuthenticationPage();

                    break;

                case 1:
                    /* If we are in the main page */

                    //Run the main page method
                    this.mainPage();

                    break;

                case 2:
                    /* If we are in the new Test Page */

                    //Run the new Test method
                    this.newTestPage();

                    break;
                case 3:
                    /* If we are in the Test Object Display Page */

                    //Run the testObject page method
                    this.testObjectPage();

                    break;
                case 4:
                    /* If we are in the test element display page */

                    //Run the test Element Page method
                    this.testElementPage();

                    break;
                case 5:
                    /* If we are in the issue element Display page */

                    //Run the Issue element Page method
                    this.issueElementPage();

                    break;
                case 6:
                    /* If we are in the Settings page */

                    //Run the settings page method
                    this.settingPage();

                    break;
            }
        }
    }

    private void userAuthenticationPage()
    {
        //If user is authenticated
        if (this.user.isUserAuthenticated())
        {
            //clear User info
            this.user.clearUserInfo();
        }

        //Update the ui
        this.gui.updateMainPage();

        //Wait till the user is authenticated
        while (!this.user.isUserAuthenticated())
        {
            sleep(30);
        }

        //Change the user state
        this.event.setCodeState(1);
    }

    private void mainPage()
    {
        //Push the values to the event object
        this.testEngine.pushDisplayObjectsToEvent();

        //Update the ui
        this.gui.updateMainPage();

        while(this.event.getCodeState() == 1)
        {
            //Sleep
            this.sleep(30);
        }
    }

    private void sleep(int milliseconds)
    {
        /* Java Engine Timeout */
        try
        {
            //Sleep
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {
            //Print error
            e.printStackTrace();
        }
    }

    private void loadData()
    {
        //Clear all objects
        this.testEngine.clearData();

        //Start with loading the testObjects
        this.database.loadTestHistoryArray(20);

        //loop through all the test objects
        for (TestObject testObject: this.testEngine.getTestObjectArrayList())
        {
            //loop through all the testElements
            for (Object testElementID: testObject.getTestElements())
            {
                //Request the full object from the database and add it to the related arrayList
                this.testEngine.addTestElement(this.database.getTestElement(String.valueOf(testElementID)));
            }

            //loop through all the issue Elements
            for (Object issueElementID: testObject.getIssueElements())
            {
                //Request the full object from the database and add it to the related arrayList
                this.testEngine.addIssueElement(this.database.getIssueElement(String.valueOf(issueElementID)));
            }
        }


    }

    private void newTestPage()
    {
        //Update the UI
        this.gui.updateMainPage();

        //wait until the code state changes
        while(this.event.getCodeState() == 2)
        {
            //Sleep
            this.sleep(30);
        }

        //Check how the form was submitted. Was it submitted or was ot canceled?
        switch (this.event.getFromEvent())
        {
            case 0 ->
            {
                /* If the form was submitted */

                //Collect the user inputs
                String[] userInputs = this.event.getAndResetUserInput();

                //create the new test object
                this.testEngine.createNewTestObject(this.user, userInputs[0], userInputs[1], new String[] {userInputs[2], userInputs[3]});

            }
            case 1 ->
            {
                /* If the form was canceled */


            }
        }

    }

    private void testObjectPage ()
    {
        //ToDo
    }

    private void testElementPage()
    {
        //ToDo
    }

    private void issueElementPage()
    {
        //ToDo
    }

    private void settingPage()
    {
        //ToDo
    }
}
