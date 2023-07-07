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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import TestManager.UserInterfaceReader.UserInterfaceReader;

public class Test
{
    //Object properties
    private final String url;
    private final Event event;
    Object testResults;

    public Test(String url, Event event)
    {
        //Setup Properties
        this.url = url;
        this.event = event;

    }

    public void UITest()
    {
        /* Request data form the User Interface reader */
        UserInterfaceReader userInterfaceReader = new UserInterfaceReader(this.url, this.event);

        userInterfaceReader.readScenarioPage();
    }

    public void executeTest(String ExpectedValue, String actualValue)
    {

    }
}
