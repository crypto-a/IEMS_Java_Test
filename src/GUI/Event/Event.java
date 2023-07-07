/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package GUI.Event;


import org.openqa.selenium.WebDriver;

public class Event
{
    //Object properties
    private WebDriver driver;


    public Event()
    {

    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver readDriver()
    {
        return this.driver;
    }

}
