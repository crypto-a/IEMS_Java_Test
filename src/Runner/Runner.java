/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package Runner;

import TestManager.TestManager;
import GUI.GUI;
import GUI.Event.Event;

public class Runner
{
    //Object Properties
    private Boolean isRunning = true;


    public Runner()
    {
        //Create the event Object
        Event event = new Event();

        //Run GUI
        GUI gui = new GUI(event);

        //Run Test Manager
        TestManager testManager = new TestManager(event);

        //Test test = new Test(50, "http://ec2-54-210-75-155.compute-1.amazonaws.com:3452/");

        //test.UITest();
    }

    public void terminateRun()
    {
        //et isRunning to false to end code
        this.isRunning = false;
    }

}
