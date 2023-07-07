package TestManager.UserInterfaceScenarioTester;
/*****************************************
 /*Program Name: UserInterfaceScenarioTester
 /*Programmer Name: Ali Rahbar
 /*Program Date: July 5, 2023
 /*Program Description: This program contains the tools used to test the Scenario section
 /*Inputs: None
 /*Outputs: None
 ******************************************/

/************************************************
//Imports
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;


import java.time.Duration;
import java.util.Random;

public class UserInterfaceScenarioTester
{
    //Define Object Properties
    private String chromeDriverUrl = "src/TestManager/ChromeDriver/chromedriver.exe";
    private WebDriver driver;

    /*****************************************
     /*Method Name: UserInterfaceScenarioTester
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 5, 2023
     /*Method Description: This method is the constructor method of this class
     /*Method Inputs: URL
     /*Method Outputs: None
     ******************************************/
/***********
    public UserInterfaceScenarioTester(String url)
    {
        /* Connect to the URL */
/**********
        try
        {
            //Setup Selenium
            System.setProperty("webdriver.chrome.driver", this.chromeDriverUrl);

            //Create the ChromeDriver Object
            this.driver = new ChromeDriver();

            //Connect to URL
            this.driver.get(url);

        }
        catch(Exception e)
        {
            System.out.println("There is an issue with the setup");
        }



        //use the scenarioSurfer
        this.scenarioSurfer();

        //End session
        this.driver.quit();
    }

    private int feederNumbs()
    {
        //Select the feeder-button
        WebElement feederButton = this.driver.findElement(By.cssSelector("div[aria-haspopup=\"listbox\"]"));

        // Perform the click action
        feederButton.click();

        //Record all the list elements in the dropdown and save the text aria-label element
        int feederNumbs = this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .stream()
                .map(element -> element.getAttribute("aria-label"))
                .toList()
                .size();


        // Perform the click action to close the dropdown
        feederButton.click();

        //Return the names
        return feederNumbs;

    }

    private void scenarioSurfer()
    {
        int[] scenarios = {this.feederNumbs(), 1, 1, 3, 2};

        //Loop through every possibility
        for (int a = 1; a <= scenarios[0]; a++)
        {
            for (int b = 1; b <= scenarios[3]; b++)
            {
                for (int c = 1; c <= scenarios[4]; c++)
                {
                    int[] currentState = {a, 1, 1, b, c};
                    //build scenario
                    this.buildScenario(currentState);

                    //Retrieve data from map
                    Object[] data = this.mapDataRetrieve();

                    //Retrieve date from Json
                    this.jsonDataRetrieve();

                    //Send data to the test function to test them


                }
            }
        }

    }

    private void buildScenario(int[] scenario) {

        // Create an instance of the Random class
        Random random = new Random();


        /* Setup Feeder */

/*
        // Perform the click on the feeder button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select a Feeder\"]")).click();

        //Select the feeder
        this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .get(scenario[0] - 1)
                .click();

        /* Setup Date */
/***
        //Select the date-button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select a Date\"]")).click();

        //Select the date and click
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(random.nextInt(this.driver.findElements(By.cssSelector(".p-dropdown-item")).size())).click();

        /* Setup Time */
/****
        ///Select the time-button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select a Time\"]")).click();

        //Select the time and click
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(random.nextInt(this.driver.findElements(By.cssSelector(".p-dropdown-item")).size())).click();

        /* Setup EVSMode */
/*****
        // Perform the click on the EVS button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select an EVSE Mode\"]")).click();

        //Select the EVSMode
        this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .get(scenario[3] - 1)
                .click();

        /* Setup Objective Function */
        // Perform the click on the feeder button
/*****
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select an Objective Function\"]")).click();

        //Select the Objective Function
        this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .get(scenario[4] - 1)
                .click();

        /* Run Scenario */
/*****
        this.driver.findElement(By.cssSelector("button[aria-label=\"Run Scenario\"]")).click();

        /* Test Scenario */
        // Wait for a brief moment to allow the information to appear
/****
        // Wait until the run button changes its class to "p-button-success"
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement runButton = this.driver.findElement(By.cssSelector(".p-button"));
        wait.until(ExpectedConditions.attributeContains(runButton, "class", "p-button-success"));

    }
    */
/******

    private Object[] mapDataRetrieve()
    {
        /*
        Properties:
            line: 'line-element',
            bus: 'bus-element',
            externalGrid: 'externalGrid-element',
            transformer: 'transformer-element',
            fuse: 'fuse-element',
            evse: 'evse-element',
            pv: 'pv-element',
            reactor: 'reactor-element',
            load: 'load-element'
         */
/****
        // Create an array to store all the objects
        Object[] elementsObjectsArray = new Object[9];

        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) this.driver;


        // Save line data
        elementsObjectsArray[0] = js.executeScript("return map.current.getSource('line-element')._data.features;");

        // Save bus data
        elementsObjectsArray[1] = js.executeScript("return map.current.getSource('bus-element')._data.features;");

        // Save external grid data
        elementsObjectsArray[2] = js.executeScript("return map.current.getSource('externalGrid-element')._data.features;");

        // Save transformer data
        elementsObjectsArray[3] = js.executeScript("return map.current.getSource('transformer-element')._data.features;");

        // Save fuse data
        elementsObjectsArray[4] = js.executeScript("return map.current.getSource('fuse-element')._data.features;");

        // Save evse data
        elementsObjectsArray[5] = js.executeScript("return map.current.getSource('evse-element')._data.features;");

        // Save pv data
        elementsObjectsArray[6] = js.executeScript("return map.current.getSource('pv-element')._data.features;");

        // Save reactor data
        elementsObjectsArray[7] = js.executeScript("return map.current.getSource('reactor-element')._data.features;");

        // Save load data
        elementsObjectsArray[8] = js.executeScript("return map.current.getSource('load-element')._data.features;");

        //Return Object
        return elementsObjectsArray;

    }

    private void jsonDataRetrieve()
    {
        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        // Retrieve the Json
        Object jsonFile = js.executeScript("return sessionStorage.resultScenario;");

        //System.out.println(jsonFile.toString());
    }
}

*/
