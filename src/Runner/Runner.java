package Runner;

import Database.Database;
import GUI.GUI;
import GUI.Event.Event;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestEngine;
import User.User;

public class Runner
{
    //Initialize object properties
    private final Event event;
    private final TestEngine testEngine;
    private final GUI gui;
    private final Database database;
    private final User user;




    public Runner()
    {
        //Initialize the event Object
        this.event = new Event();

        //Initialize the database
        this.database = new Database();

        //Create the User Object
        this.user = new User(this.database);

        //Initialize the testManager Object
        this.testEngine = new TestEngine(this.event, this.user, this.database);

        //Initialize the GUI object
        this.gui = new GUI(event, testEngine, user);

        //Show the GUI to the User
        this.gui.updateMainPage();

        //Wait till User is authenticated
        this.userAuthenticate();

        //Change the event page
        this.event.changeCurrentPage("mainPage");

        //UpdateUI
        this.gui.updateMainPage();

        //LoadData From the Database
        this.testEngine.loadData();

        this.gui.updateMainPage();


        System.out.println(this.user.getName());
        //ToDO: Create the code Loop Executor
        while (true)
        {
            this.eventHandler();
        }


    }

    private void userAuthenticate()
    {
        while (!this.user.isUserAuthenticated())
        {
            /* Java Engine Timeout */
            try
            {
                //Time Out
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }

        }
    }


    private void eventHandler()
    {
        /* Java Engine Timeout */
        try
        {
            //Time Out
            Thread.sleep(30);
        }
        catch (InterruptedException e)
        {
            //Print error
            e.printStackTrace();
        }

        /* Collect the event that is happening */

        int eventCode = this.event.getEvent();

        //Looks at different eventRequests
        //ToDo
        switch (eventCode)
        {
            case 0:
                /* If the user requests to add a button */


                //run the addNewTest Button
                this.addNewTest();

                //Break from switch statement
                break;

            case 1:
                /* If the user requests to see a test detail */
                this.showOldTest();

                break;

            case 2:
                /* If the use requests to see the details of the TextUnitElement */
                this.showOldTestUnit();

                break;

            case 3:
                /* If they request to see the details of an Issue from the main page */
                this.displayIssueComponent();

                break;

            default:
                break;

        }

        // Check if the screen needs updating
        if (eventCode != -1)
        {
            //update the main screen
            this.gui.updateMainPage();
        }
    }

    private void addNewTest()
    {
        //Change the event Page
        this.event.changeCurrentPage("addTestPage");

        //Update UI
        this.gui.updateMainPage();

        //Collect the current operation
        int eventCode = this.event.getFromEvent();

        //Wait till an action happens
        while (eventCode == -1)
        {
            /* Java Engine Timeout */

            try
            {
                //Sleep
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }

            //Update event Code
            eventCode = this.event.getFromEvent();
        }

        //When the loop is broken, check for operation

        switch (eventCode)
        {
            case 0:
                /* If the form is submitted */
                //change the active page
                this.event.changeCurrentPage("mainPage");

                //Update UI
                this.gui.updateMainPage();

                //Start Test
                this.testEngine.createTest("DLC Demo", this.event.getInputValues()[1], new String[] {this.event.getInputValues()[2], this.event.getInputValues()[3]});//ToDo

                //Reset Inputs
                this.event.resetInput();

                break;

            case 1:
                /* If the form is canceled */
                //change the active page
                this.event.changeCurrentPage("mainPage");

                //Update UI
                this.gui.updateMainPage();

                //Reset Inputs
                this.event.resetInput();

                break;

            default:
                break;
        }





    }

    private void showOldTest()
    {
        //Change the event Page
        this.event.changeCurrentPage("oldTestDisplay");

        //Update UI
        this.gui.updateMainPage();

        //Collect the current operation
        int eventCode = this.event.getFromEvent();

        //Wait till an action happens
        while (eventCode == -1)
        {
            /* Java Engine Timeout */

            try
            {
                //Sleep
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }

            //Update event Code
            eventCode = this.event.getFromEvent();
        }

        //When the loop is broken, check for operation

        switch (eventCode)
        {
            case 0:
                //Reset Inputs
                this.event.resetInput();
                break;

            case 1:
                /* If the form is canceled */
                //change the active page
                this.event.changeCurrentPage("mainPage");


                //Reset Inputs
                this.event.resetInput();

                break;

            default:
                break;
        }
    }

    private void showOldTestUnit()
    {
        //Change the event Page
        this.event.changeCurrentPage("oldTestUnitDisplay");

        //Update UI
        this.gui.updateMainPage();

        //Collect the current operation
        int eventCode = this.event.getFromEvent();

        //Wait till an action happens
        while (eventCode == -1)
        {
            /* Java Engine Timeout */

            try
            {
                //Sleep
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }

            //Update event Code
            eventCode = this.event.getFromEvent();
        }

        //When the loop is broken, check for operation

        switch (eventCode)
        {
            case 0:
                //Reset Inputs
                this.event.resetInput();

                break;

            case 1:
                /* If the form is canceled */
                //change the active page
                this.event.changeCurrentPage("oldTestDisplay");

                //Reset Inputs
                this.event.resetInput();

                //Execute an action
                this.event.setControlPanelButtonPressed(1);

                break;

            default:
                break;
        }
    }

    private void displayIssueComponent()
    {
        //Change the event Page
        this.event.changeCurrentPage("IssueComponentDisplay");

        //Update UI
        this.gui.updateMainPage();

        //Collect the current operation
        int eventCode = this.event.getFromEvent();

        //Wait till an action happens
        while (eventCode == -1)
        {
            /* Java Engine Timeout */

            try
            {
                //Sleep
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }

            //Update event Code
            eventCode = this.event.getFromEvent();
        }

        switch (eventCode)
        {
            case 0:
                /* If they submit the form */


                IssueElement issueElement = this.event.getSelectedIssueElement();

                //Close the Issue
                issueElement.closeIssue(this.user.getUserID(), this.event.getInputValues()[0]);

                //reset the input
                this.event.resetInput();

                //update the issue value in the database
                this.database.updateIssueStatus(issueElement);

                //Reset the openIssues list
                this.testEngine.resetIssues();

                //Update page
                this.gui.updateMainPage();

                //Reset Inputs
                this.event.resetInput();
                break;
            case 1:
                /* if they cancel the form */
                //change the active page
                this.event.changeCurrentPage("mainPage");


                //Reset Inputs
                this.event.resetInput();

                break;

        }


        //Collect the current operation
        eventCode = this.event.getFromEvent();

        //Wait till an action happens
        while (eventCode == -1)
        {
            /* Java Engine Timeout */

            try
            {
                //Sleep
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }

            //Update event Code
            eventCode = this.event.getFromEvent();
        }

        switch (eventCode)
        {
            case 0:
                break;
            case 1:
                /* if they cancel the form */
                //change the active page
                this.event.changeCurrentPage("mainPage");


                //Reset Inputs
                this.event.resetInput();

                this.gui.updateMainPage();
                break;
        }
    }

    private void issueDetailsPage()
    {

    }

    private void sortByName()
    {

    }

    private void sortByDate()
    {

    }

    private void sortByTime()
    {
        
    }
}
