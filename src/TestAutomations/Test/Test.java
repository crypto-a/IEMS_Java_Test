package TestAutomations.Test;

import TestEngine.TestObject.TestObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public abstract class Test implements Runnable
{
    private String webAppURL;
    private final String chromeDriverUrl = "src/TestEngine/ChromeDriver/chromedriver.exe";
    protected final WebDriver driver;
    protected final TestObject testObject;
    private String[] userLoginInfo;

    public Test(TestObject testObject, String url, String[] userLoginInfo)
    {

        //Set up object Properties
        this.webAppURL = url;
        this.testObject = testObject;
        this.userLoginInfo = userLoginInfo;

        // Configure ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode (optional)

        //Setup Selenium
        System.setProperty("webdriver.chrome.driver", this.chromeDriverUrl);

        //Create the ChromeDriver Object
        this.driver = new ChromeDriver(options);

        System.out.println("Web driver created");

        //Connect to URL
        this.driver.get(this.webAppURL);

    }

    private void userLogin(String[] userLoginInfo)
    {
        try
        {
            /* Start with the username element */

            // Locate the text field element using the name locator
            WebElement usernameElement = this.driver.findElement(By.name("userName"));

            // Clear any existing text in the text field (optional)
            usernameElement.clear();

            // Fill the text field with custom text
            usernameElement.sendKeys(userLoginInfo[0]);

            /* Also Fill the password field */

            // Locate the text field element using the name locator
            WebElement passwordElement = this.driver.findElement(By.cssSelector(".p-inputtext.p-component.p-password-input[name='password']"));

            // Clear any existing text in the text field (optional)
            passwordElement.clear();

            // Fill the text field with custom text
            passwordElement.sendKeys(userLoginInfo[1]);

            /* Click on hte login Button */
            WebElement loginButton = this.driver.findElement(By.cssSelector("[aria-label='Login']"));

            //Click on the login button
            loginButton.click();
        }
        catch (Exception e)
        {
            //Print error message
            System.out.println(e.getMessage());
        }


    }

    private void pageTerminate()
    {
        //Close Driver
        this.driver.quit();
    }

    private void pushDriver()
    {
        //Set the driver to the driver object
        this.testObject.setDriver(this.driver);
    }

    protected abstract void test();

    @Override
    public void run()
    {
        /* Start Login Process */
        this.userLogin(this.userLoginInfo);

        /* Start Test */
        this.test();

        /* Close Web Driver */
        this.pageTerminate();
    }
}
