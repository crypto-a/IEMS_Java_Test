/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package TestManager.Test;


import GUI.Event.Event;
import GUI.GUI;
import TestManager.ActualData.ActualData;
import TestManager.CollectedData.CollectedData;
import io.netty.handler.codec.socksx.v5.Socks5PasswordAuthStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import TestManager.UserInterfaceReader.UserInterfaceReader;

import java.util.Arrays;

public class Test
{
    //Object properties
    private final String url;
    private final Event event;
    private ActualData actualData;
    private CollectedData collectedData;
    private final GUI gui;
    Object testResults;

    public Test(String url, Event event, GUI gui)
    {
        //Setup Properties
        this.url = url;
        this.event = event;

        this.actualData = new ActualData();
        this.collectedData = new CollectedData();
        this.gui = gui;

    }

    public void UITest()
    {
        /* Request data form the User Interface reader */
        UserInterfaceReader userInterfaceReader = new UserInterfaceReader(this.url, this.event, this.actualData, this.collectedData, this);

        userInterfaceReader.readScenarioPage();
    }

    public void executeTest(String currentState)
    {
        //Test Bus Elements
        this.testBusElements(currentState);

    }

    private void testBusElements(String currentState)
    {
        String message = "Passed Successfully";

        //Loop though every element of the collected data
        for (String[] collectedArray : this.collectedData.bus_element)
        {

            //loop through the second list
            for (String[] actualArray: this.actualData.bus_element)
            {
                //ToDo: Fix the Finding error system and optimize it
                //Check to match with the same value
                if (collectedArray[0].equals(actualArray[0]))
                {

                    //If value one is the same
                    if (Math.abs(Double.parseDouble(collectedArray[1]) - Double.parseDouble(actualArray[1])) <= 0.001)
                    {
                        //If value two is the same
                        if (Math.abs(Double.parseDouble(collectedArray[2]) - Double.parseDouble(actualArray[2])) <= 0.001)
                        {
                            //If value 3 is ok
                            if (Math.abs(Double.parseDouble(collectedArray[3]) - Double.parseDouble(actualArray[3])) <= 0.001)
                            {
                                //System.out.println("Bus " + collectedArray[0] + " - OK");
                            }
                            else
                            {
                                message = "Bus " + collectedArray[0] + " - Failed";
                            }
                        }
                        else
                        {
                            message = "Bus " + collectedArray[0] + " - Failed";
                        }
                    }
                    else
                    {
                        message = "Bus " + collectedArray[0] + " - Failed";
                    }
                    //System.out.println(Arrays.toString(collectedArray) + " " + Arrays.toString(actualArray));
                }

            }

        }

        //Push to Event
        this.event.dataWrite(new String[]{currentState, message});

        //Request UI update
        this.gui.updateUI();

        //Delete the array Lists for the next test
        this.actualData.bus_element.clear();
        this.collectedData.bus_element.clear();

    }
}
