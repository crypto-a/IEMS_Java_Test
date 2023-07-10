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
import TestManager.ActualData.ActualData;
import TestManager.CollectedData.CollectedData;
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
    //Object properties
    private final Event event;
    private final String chromeDriverUrl = "src/TestManager/ChromeDriver/chromedriver.exe";
    private final WebDriver driver;
    private ActualData actualData;
    private CollectedData collectedData;

    private final Test test;
    public UserInterfaceReader(String url, Event event, ActualData actualData, CollectedData collectedData, Test test)
    {
        //Setup Object properties
        this.event = event;
        this.actualData = actualData;
        this.collectedData = collectedData;
        this.test = test;

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


    public void readScenarioPage()
    {
        this.scenarioSurfer();
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

                    System.out.println(Arrays.toString(currentState));

                    //build scenario
                    this.buildScenario(currentState);

                    //Retrieve data from map
                    this.normalizeScenarioMapData((String[]) this.mapDataRetrieve());

                    //Retrieve date from Json
                    this.normalizeScenarioJsonData(this.jsonDataRetrieve());

                    //Send data to the test function to test them
                    this.test.executeTest();


                }
            }
        }

    }

    private void buildScenario(int[] scenario) {

        // Create an instance of the Random class
        Random random = new Random();


        /* Setup Feeder */

        // Perform the click on the feeder button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select a Feeder\"]")).click();

        //Select the feeder
        this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .get(scenario[0] - 1)
                .click();

        /* Setup Date */

        //Select the date-button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select a Date\"]")).click();

        //Select the date and click
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(random.nextInt(this.driver.findElements(By.cssSelector(".p-dropdown-item")).size())).click();

        /* Setup Time */

        ///Select the time-button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select a Time\"]")).click();

        //Select the time and click
        this.driver.findElements(By.cssSelector(".p-dropdown-item")).get(random.nextInt(this.driver.findElements(By.cssSelector(".p-dropdown-item")).size())).click();

        /* Setup EVSMode */

        // Perform the click on the EVS button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select an EVSE Mode\"]")).click();

        //Select the EVSMode
        this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .get(scenario[3] - 1)
                .click();

        /* Setup Objective Function */
        // Perform the click on the feeder button
        this.driver.findElement(By.cssSelector("div[aria-label=\"Select an Objective Function\"]")).click();

        //Select the Objective Function
        this.driver.findElements(By.cssSelector(".p-dropdown-item"))
                .get(scenario[4] - 1)
                .click();

        /* Run Scenario */
        this.driver.findElement(By.cssSelector("button[aria-label=\"Run Scenario\"]")).click();

        /* Test Scenario */
        // Wait for a brief moment to allow the information to appear

        // Wait until the run button changes its class to "p-button-success"
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement runButton = this.driver.findElement(By.cssSelector(".p-button"));
        wait.until(ExpectedConditions.attributeContains(runButton, "class", "p-button-success"));

    }

    private String[] mapDataRetrieve()
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
        // Create an array to store all the objects
        String[] elementsObjectsArray = new String[9];

        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) this.driver;


        // Save line data
        elementsObjectsArray[0] = js.executeScript("return map.current.getSource('line-element')._data.features;").toString();

        // Save bus data
        elementsObjectsArray[1] = js.executeScript("return map.current.getSource('bus-element')._data.features;").toString();

        // Save external grid data
        elementsObjectsArray[2] = js.executeScript("return map.current.getSource('externalGrid-element')._data.features;").toString();

        // Save transformer data
        elementsObjectsArray[3] = js.executeScript("return map.current.getSource('transformer-element')._data.features;").toString();

        // Save fuse data
        elementsObjectsArray[4] = js.executeScript("return map.current.getSource('fuse-element')._data.features;").toString();

        // Save evse data
        elementsObjectsArray[5] = js.executeScript("return map.current.getSource('evse-element')._data.features;").toString();

        // Save pv data
        elementsObjectsArray[6] = js.executeScript("return map.current.getSource('pv-element')._data.features;").toString();

        // Save reactor data
        elementsObjectsArray[7] = js.executeScript("return map.current.getSource('reactor-element')._data.features;").toString();

        // Save load data
        elementsObjectsArray[8] = js.executeScript("return map.current.getSource('load-element')._data.features;").toString();


        return elementsObjectsArray;

    }

    private String jsonDataRetrieve()
    {
        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        //Return json file
        return js.executeScript("return sessionStorage.resultScenario;").toString();
    }

    private void normalizeScenarioJsonData(String jsonData)
    {
        //Save the file as a Json Object
        JSONObject jsonObject = new JSONObject(jsonData);

        /* Collect the bus data */

        //Collect the bus data Json
        JSONObject busData = (JSONObject) jsonObject.get("bus_data");

        //Collect the Json for diffrent properties
        JSONObject vmpu = (JSONObject) busData.get("vm_pu");
        JSONObject vaDegree = (JSONObject) busData.get("va_degree");
        JSONObject dlmp = (JSONObject) busData.get("lam_p");

        //Create an Iterator object for one element
        Iterator<String> vmpuKeys = vmpu.keys();



        //Loop through every element
        while (vmpuKeys.hasNext())
        {
            //Collect the index number
            String currentIndex = vmpuKeys.next();



            //Collect properties from each JsonObject
            String vmpuValue = vmpu.get(currentIndex).toString();
            String vaDegreeValue = vaDegree.get(currentIndex).toString();
            String dlmpValue = dlmp.get(currentIndex).toString();



            //Construct the list of data values
            String[] busValues = {String.valueOf((Integer.parseInt(currentIndex) + 1)), vmpuValue, vaDegreeValue, dlmpValue};

            //Add the values to the Actual data arraylist
            this.actualData.bus_element.add(busValues);
        }


    }

    private void normalizeScenarioMapData(String[] mapData)
    {
        /* Collect bus data for the map */

        //Retrieve the bus Data
        String jsonString = mapData[1].substring(1, mapData[1].length() - 1);

        // Split the array content into individual object strings
        String[] objectStrings = jsonString.split("\\},\\s*\\{");

        // Iterate over each object string
        for (String objectString : objectStrings)
        {
            //Pull out the properties
            String[] properties = objectString.split("\\}, (\\w+)=\\{")[1].split(", (\\w+)=")[1].substring(5).split("[^\\d.-]+");

            // Filter out empty strings and dots
            String[] propertiesFinal = Arrays.stream(properties)
                    .filter(s -> !s.isEmpty() && !s.equals("."))
                    .toArray(String[]::new);

            propertiesFinal[0] = String.valueOf((Integer.parseInt(propertiesFinal[0]) + 1));

            //Add it to the collected data arraylist
            this.collectedData.bus_element.add(propertiesFinal);
        }




        //

    }



}
