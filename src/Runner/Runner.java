/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package Runner;

import TestManager.Test.Test;

public class Runner
{
    public Runner()
    {
        //Run GUI

        //Run Test Manager
        Test test = new Test(50, "http://ec2-54-210-75-155.compute-1.amazonaws.com:3452/");

        test.UITest();
    }

}
