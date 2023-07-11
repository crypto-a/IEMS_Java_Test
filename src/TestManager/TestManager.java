/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package TestManager;

import GUI.Event.Event;
import GUI.GUI;
import TestManager.Test.Test;

public class TestManager
{
    //Object properties
    private final Event event;
    private final GUI gui;
    Test[] testList;

    public TestManager(Event event, GUI gui)
    {

        //SetUp object Properties
        this.event = event;
        this.gui = gui;


        this.newTest("https://dlcdemo.iemssolution.com/");
    }

    public void newTest(String url)
    {
        //create the new test object
        Test test = new Test(url, event, this.gui);
        test.UITest();
    }

}
