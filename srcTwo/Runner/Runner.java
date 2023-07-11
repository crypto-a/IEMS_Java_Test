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

    private Event event;
    private TestManager testManager;
    private GUI gui;


    public Runner()
    {
        //Create the event Object
        this.event = new Event();

        //Initialize Test Manager
        this.testManager = new TestManager(event);

        //Initialize GUI Object
        this.gui = new GUI("IEMS Test Software", new int[]{800, 600}, this.event, this.testManager);



    }

    public void terminateRun()
    {
        //et isRunning to false to end code
        this.isRunning = false;
    }

}
