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


    }
}
