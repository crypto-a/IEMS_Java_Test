package Runner;

import Database.Database;
import GUI.Event.Event;
import GUI.GUI;
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
        //Create the TestEngine Object
        this.testEngine = new TestEngine();

        //Create the Database

        this.database = new Database();

        //Create the Event Object
        this.event = new Event();

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
}
