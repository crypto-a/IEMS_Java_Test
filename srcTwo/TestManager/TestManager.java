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
import TestManager.subTest.Test;

public class TestManager
{
    //Object properties
    private final Event event;
    Test[] testList;

    public TestManager(Event event)
    {

        //SetUp object Properties
        this.event = event;



        //this.newTest("https://dlcdemo.iemssolution.com/");
    }

    public void newTest(String url)
    {
        //create the new test object
        Test test = new Test(url, event);
        test.UITest();
    }

}
