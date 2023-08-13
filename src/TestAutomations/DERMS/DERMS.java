package TestAutomations.DERMS;

import TestAutomations.Test.Test;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;


import java.text.DecimalFormat;
import java.util.*;

public class DERMS extends Test
{
    private TestObject testObject;
    private TestEngine testEngine;

    public DERMS(TestEngine testEngine, TestObject testObject)
    {
        //create the driver
        super();

        //set up the property
        this.testObject = testObject;
        this.testEngine = testEngine;

    }

    private String[] dersmTestElementsID = new String[]
            {
                    "motor_1",
                    "externalGrid_1",
                    "pv_1",
                    "load_1",
                    "combinedLoad_1",
                    "extendedCombinedLoad_1",
                    "wind_1",
                    "line_1",
                    "winding_transformer2_1",
                    "bus_1",
                    "bus_2",
                    "node_1"
            };

    private String[][] dermsTestJsonObjectID = new String[][]
            {
                    {"bus", "Vm", "Vang"},
                    {"motor", "p_mw", "q_mvar"},
                    {"line", "i_from_ka", "i_to_ka", "loading_percent", "p_from_mw", "p_to_mw", "q_from_mvar", "q_to_mvar"},
                    {"pv", "p_mw", "q_mvar"},
                    {"winding_transformer2", "i_hv_ka", "i_lv_ka", "loading_percent", "p_hv_mw", "p_lv_mw", "q_hv_mvar", "q_lv_mvar"},
                    {"extendedCombinedLoad", "p_mw", "q_mvar", "vm_pu"},
                    {"externalGrid", "p_mw", "q_mvar"},
                    {"combinedLoad", "p_mw", "q_mvar", "vm_pu"},
                    {"node", "Vm", "Vang"},
                    {"load", "p_mw", "q_mvar"},
                    {"wind", "p_mw", "q_mvar"}
            };


    private HashMap<String, String[]> actualData = new HashMap<>();
    private  HashMap<String, String[]> expectedData = new HashMap<>();

    @Override
    public void run()
    {
        //load the page
        this.loadPage(this.testObject.getWebPageURL());

        //log in
        this.logIn(this.testObject.getWebPageLoginInfo()[0], this.testObject.getWebPageLoginInfo()[1]);

        //wait for a couple of seconds
        this.waitUntilPageLoads("label.mx-auto");

        /* Java Engine Timeout */
        try
        {
            //Sleep
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            //Print error
            e.printStackTrace();
        }

        //click and enter project
        this.doubleClick("tr[tabindex=\"0\"]");

        this.waitUntilPageLoads(".react-flow__nodes");

        /* Java Engine Timeout */
        try
        {
            //Sleep
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            //Print error
            e.printStackTrace();
        }

        System.out.println(1);

        // Assuming you have already created a WebDriver instance called "driver"
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;


        for (int i = 1; i < 4; i++)
        {

            jsExecutor.executeScript(
                    "window.consoleInfoMessages = [];" +
                            "var originalConsoleInfo = console.info;" +
                            "console.info = function(message) {" +
                            "    window.consoleInfoMessages.push(arguments);" +
                            "    originalConsoleInfo.apply(console, arguments);" +
                            "};"
            );


            //run a scenario
            this.selectDropDown("div.p-dropdown-trigger", 1);

            //Click the run button
            this.clickButton("button[class=\"p-button p-component p-button-secondary p-button-outlined mr-1\"]");

            //wait until loading
            this.waitUntilPageLoads(".p-datatable-tbody");

            /* Java Engine Timeout */
            try
            {
                //Sleep
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                //Print error
                e.printStackTrace();
            }


            // Find the parent element containing all elements you want to loop through
            WebElement parentDiv = this.getElementFromWebPage(".react-flow__nodes");

            for (String data_id : this.dersmTestElementsID)
            {
                WebElement nodeMain = parentDiv.findElement(By.cssSelector("div[data-id=\"" + data_id + "\"]"));

                WebElement secondaryNode = nodeMain.findElement(By.cssSelector("div[data-id=\"" + data_id + "\"]"));

                this.normalizeWebsiteData(secondaryNode.getAttribute("title"));
            }


            // Search for the log entry containing the API response
            // Retrieve the consoleInfoMessages array
            List<Object> consoleInfoMessages = (List<Object>) jsExecutor.executeScript("return window.consoleInfoMessages["+ String.valueOf(i-1) +"];");

            // Print the captured console.info messages

            int a = 0;
            String jsonString = null;

            for (Object message : consoleInfoMessages)
            {
                if (a==1)
                {
                    jsonString = message.toString();

                    break;
                }

                a++;
            }



            String modefiedJsonString = jsonString.replace("=", ":");

            JSONObject jsonObject = new JSONObject(modefiedJsonString);

            for (String[] elementArray : this.dermsTestJsonObjectID)
            {

                JSONArray jsonArray = jsonObject.getJSONArray(elementArray[0]);


                for (int z = 0; z < jsonArray.length(); z++)
                {
                    JSONObject jsonObjectElement = jsonArray.getJSONObject(z);

                    String id = this.normalizeJsonData(jsonObjectElement, elementArray);

                    //create a test
                    String[] actualData = this.actualData.get(id);
                    String[] expectedData = this.expectedData.get(id);

                    this.testEngine.createNewTestElement(id, " scenario ran: " + i, new String[][] {actualData}, new String[][]{expectedData});
                }
            }




        }


    }

    private void normalizeWebsiteData(String data)
    {
        String[] splitData = data.split("(?:\\n)?\\b\\w+ = ");

        splitData[splitData.length -1] = splitData[splitData.length -1].substring(0, splitData[splitData.length -1].length() - 1);

        splitData = Arrays.copyOfRange(splitData, 1, splitData.length);

        // Format numeric values with 5 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("0.00000");

        for (int i = 0; i < splitData.length; i++)
        {
            try
            {
                double value = Double.parseDouble(splitData[i]);
                splitData[i] = decimalFormat.format(value);
            } catch (NumberFormatException e)
            {
                // Not a numeric value, continue to the next element
            }
        }

        this.actualData.put(splitData[0], splitData);
    }

    private String normalizeJsonData(JSONObject json, String[] elementArray)
    {
        String[] jsonData = new String[elementArray.length + 2];

        jsonData[0] = json.getString("id");
        jsonData[1] = json.getString("name");
        jsonData[2] = elementArray[0];

        for (int i = 3; i < jsonData.length; i++)
        {
            jsonData[i] = String.valueOf(json.get(elementArray[i - 2]));
        }

        //normalize data
        // Format numeric values with 5 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("0.00000");

        for (int i = 0; i < jsonData.length; i++)
        {
            try
            {
                double value = Double.parseDouble(jsonData[i]);
                jsonData[i] = decimalFormat.format(value);
            } catch (NumberFormatException e)
            {
                // Not a numeric value, continue to the next element
            }
        }

        //put it in them in a hash map
        this.expectedData.put(jsonData[0], jsonData);

        return jsonData[0];
    }
}
