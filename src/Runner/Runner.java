package Runner;

import GUI.GUI;
import GUI.Event.Event;
import TestManager.TestManager;

public class Runner
{
    //Initialize object properties
    private final Event event;
    private final TestManager testManager;
    private final GUI gui;



    public Runner()
    {
        //Initialize the event Object
        this.event = new Event();

        //Initialize the testManager Object
        this.testManager = new TestManager(this.event);

        //Initialize the GUI object
        this.gui = new GUI(event, testManager);

        //Show the GUI to the User
        this.gui.updateMainPage();

        //ToDO: Create the code Loop Executor
        while (true)
        {
            this.eventHandler();
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
                this.testManager.createTest(new Boolean[]{true, false, false}, this.event.getInputValues()[0]);//ToDo

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
