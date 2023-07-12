package TestManager.UserInterfaceReader;


import GUI.Event.Event;
import TestManager.Test.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.*;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class UserInterfaceReader
{
    //Object Properties
    private final String chromeDriverUrl = "src/TestManager/ChromeDriver/chromedriver.exe";
    private final WebDriver driver;

    public UserInterfaceReader(String url)
    {
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

    public void dropDownButtonClick (String ariaLabel, int elementNumber)
    {
        // Perform the click on the feeder button
        this.driver.findElement(By.cssSelector("div[aria-label=\"" + ariaLabel + "\"]")).click();

        //Select the feeder
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(elementNumber - 1).click();
    }

    public void dropDownButtonRandomClick (String ariaLabel)
    {
        // Create an instance of the Random class
        Random random = new Random();

        //Select the date-button
        this.driver.findElement(By.cssSelector("div[aria-label=\"" + ariaLabel + "\"]")).click();

        //Select the date and click
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(random.nextInt(this.driver.findElements(By.cssSelector(".p-dropdown-item")).size())).click();
    }

    public void runScenario()
    {
        //Click on the Run Scenario Button
        this.driver.findElement(By.cssSelector("button[aria-label=\"Run Scenario\"]")).click();

        // Wait until the run button changes its class to "p-button-success"
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45));
        WebElement runButton = this.driver.findElement(By.cssSelector(".p-button"));
        wait.until(ExpectedConditions.attributeContains(runButton, "class", "p-button-success"));
    }

    public String mapDataRetrieve(String mapElement)
    {
        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        //Return the map Object Element Data
        return js.executeScript("return map.current.getSource('" + mapElement + "')._data.features;").toString();
    }

    public String jsonDateRetrieve(String jsonElement)
    {
        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        //Collect json file
        JSONObject jsonObject = new JSONObject(js.executeScript("return sessionStorage.resultScenario;").toString());

        //Return the requested property
        return jsonObject.get(jsonElement).toString();
    }

    public int dropDownOptionsCount(String ariaLabel)
    {
        //Select the dropdown and click it
        WebElement dropDownButton = this.driver.findElement(By.cssSelector("div[aria-label=\"" + ariaLabel + "\"]"));

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


}
