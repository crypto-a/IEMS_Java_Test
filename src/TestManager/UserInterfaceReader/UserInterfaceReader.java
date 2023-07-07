/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package TestManager.UserInterfaceReader;

import GUI.Event.Event;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class UserInterfaceReader
{
    //Object properties
    private final Event event;
    private final String chromeDriverUrl = "src/TestManager/ChromeDriver/chromedriver.exe";
    private final WebDriver driver;

    public UserInterfaceReader(String url, Event event)
    {
        //Setup Object properties
        this.event = event;

        /* Connect to the URL */

        // Configure ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode (optional)

        //Setup Selenium
        System.setProperty("webdriver.chrome.driver", this.chromeDriverUrl);

        //Create the ChromeDriver Object
        this.driver = new ChromeDriver();

        //Connect to URL
        this.driver.get(url);
    }
}
