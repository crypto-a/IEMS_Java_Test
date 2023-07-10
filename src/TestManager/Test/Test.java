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
import TestManager.ActualData.ActualData;
import TestManager.CollectedData.CollectedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import TestManager.UserInterfaceReader.UserInterfaceReader;

public class Test
{
    //Object properties
    private final String url;
    private final Event event;
    private ActualData actualData;
    private CollectedData collectedData;
    Object testResults;

    public Test(String url, Event event)
    {
        //Setup Properties
        this.url = url;
        this.event = event;

        this.actualData = new ActualData();
        this.collectedData = new CollectedData();

    }

    public void UITest()
    {
        /* Request data form the User Interface reader */
        UserInterfaceReader userInterfaceReader = new UserInterfaceReader(this.url, this.event, this.actualData, this.collectedData);

        userInterfaceReader.readScenarioPage();
    }

    public void executeTest()
    {

    }
}
