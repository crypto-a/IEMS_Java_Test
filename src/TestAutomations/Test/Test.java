package TestAutomations.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public abstract class Test implements Runnable
{
    private final String chromeDriverUrl = "src/TestAutomations/ChromeDriver/chromedriver.exe";
    public final WebDriver driver;
    public final JavascriptExecutor js;

    public Test()
    {
        // Configure ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode (optional)

        //Setup Selenium
        System.setProperty("webdriver.chrome.driver", this.chromeDriverUrl);

        //Create the ChromeDriver Object
        this.driver = new ChromeDriver(/* ToDo: Add the options to run headless */);

        // Create a JavascriptExecutor instance
         this.js = (JavascriptExecutor) this.driver;
    }

    public void logIn(String username, String password)
    {
        try
        {
            /* Start with the username element */

            // Locate the text field element using the name locator
            WebElement usernameElement = this.driver.findElement(By.name("userName"));

            // Clear any existing text in the text field (optional)
            usernameElement.clear();

            // Fill the text field with custom text
            usernameElement.sendKeys(username);

            /* Also Fill the password field */

            // Locate the text field element using the name locator
            WebElement passwordElement = this.driver.findElement(By.cssSelector(".p-inputtext.p-component.p-password-input[name='password']"));

            // Clear any existing text in the text field (optional)
            passwordElement.clear();

            // Fill the text field with custom text
            passwordElement.sendKeys(password);

            /* Click on the login Button */
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

    public void loadPage(String url)
    {
        //Connect to URL
        this.driver.get(url);
    }

    public void clickButton(String buttonCssSelector)
    {
        //perform the click
        this.driver.findElement(By.cssSelector(buttonCssSelector)).click();
    }

    public void selectDropDown(String dropDownCssSelector, int elementNumber)
    {
        // Perform the click on the feeder button
        this.driver.findElement(By.cssSelector(dropDownCssSelector)).click();

        //Select the feeder
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(elementNumber).click();
    }

    public void selectDropDownRandomly(String dropDownCssSelector)
    {
        // Create an instance of the Random class
        Random random = new Random();

        //Select the date-button
        this.driver.findElement(By.cssSelector(dropDownCssSelector)).click();

        //Select the date and click
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(random.nextInt(this.driver.findElements(By.cssSelector(".p-dropdown-item")).size())).click();
    }

    public int dropDownOptionsCount(String elementCssSelector)
    {
        //Select the dropdown and click it
        WebElement dropDownButton = this.driver.findElement(By.cssSelector(elementCssSelector));

        // Perform the click action
        dropDownButton.click();

        //Record all the list elements in the dropdown and save the text aria-label element
        int dropDownButtonNumbs = this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .stream()
                .map(element -> element.getAttribute("aria-label"))
                .toList()
                .size();


        // Perform the click action to close the dropdown
        dropDownButton.click();

        //Return the names
        return dropDownButtonNumbs;
    }

    public void waitUntilChange(int maxDuration, String elementCssSelector, String waitUntilAttribute, String attributeValue )
    {
        //create a wait session
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(maxDuration));

        //get web element
        WebElement element = this.driver.findElement(By.cssSelector(elementCssSelector));

        //wait until
        wait.until(ExpectedConditions.attributeContains(element, waitUntilAttribute, attributeValue));
    }

    public void waitUntilPageLoads(String elementWaitingForCssSelector)
    {
        //create a wait session
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));

        //wait until
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementWaitingForCssSelector)));
    }

    public void requestDataFromWebElement()
    {
        //ToDo
    }

    public String requestDataFromJavaScriptConsole(String query)
    {
        //Return the map Object Element Data
        return this.js.executeScript(query).toString();
    }

    public void terminateDriver()
    {
        //Quit the driver
        this.driver.quit();
    }


    @Override
    public abstract void run();



}
