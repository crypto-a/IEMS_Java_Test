package TestAutomations.DLCDemo;

import TestAutomations.Test.Test;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DLCDemo extends Test
{

    private TestObject testObject;
    private TestEngine testEngine;

    private String[] mapElements = new String[]{
            "line-element",
            "bus-element",
            "externalGrid-element",
            "transformer-element",
            "fuse-element",
            "evse-element",
            "pv-element",
            "reactor-element",
            "load-element"
    };

    public DLCDemo(TestEngine testEngine, TestObject testObject)
    {
        //create the driver
        super();

        //set up the property
        this.testObject = testObject;
        this.testEngine = testEngine;

    }

    @Override
    public void run()
    {
        //load the page
        this.loadPage(this.testObject.getWebPageURL());

        //Log in
        this.logIn(this.testObject.getWebPageLoginInfo()[0], this.testObject.getWebPageLoginInfo()[1]);

        //wait for a couple of seconds
        this.waitUntilPageLoads("button[aria-label=\"Run Scenario\"]");

        //get how many possibilities exist
        int scenarioPossibilities = this.dropDownOptionsCount("div[aria-label=\"Select a Feeder\"]");


        //loop through all the scenario possibilities
        for (int a = 0; scenarioPossibilities> a; a++)
        {
            //select the first dropdown
            this.selectDropDown("div[aria-label=\"Select a Feeder\"]", a);

            //Select DropDown (Random)
            this.selectDropDownRandomly("div[aria-label=\"Select a Date\"]");
            this.selectDropDownRandomly("div[aria-label=\"Select a Time\"]");

            //loop though other possibilities
            for (int d = 0; d < 3; d++)
            {
                //Select DropDown
                this.selectDropDown("div[aria-label=\"Select an EVSE Mode\"]", d);

                for (int e = 0; e < 2; e++)
                {
                    //Select DropDown
                    this.selectDropDown("div[aria-label=\"Select an Objective Function\"]", e);

                    //Create Current State Array
                    int[] currentState = {a, -1, -1, d, e};

                    //Click on the run button
                    this.clickButton("button[aria-label=\"Run Scenario\"]");

                    //Wait until the scenario is loaded
                    this.waitUntilChange(45, ".p-button", "class", "p-button-success");

                    //extract the json data
                    String JsonData = this.requestDataFromJavaScriptConsole("return sessionStorage.resultScenario;");

                    //extract the busData
                    String mapData = this.requestDataFromJavaScriptConsole("return map.current.getSource('" + this.mapElements[1] + "')._data.features;");

                    this.testEngine.createNewTestElement(this.mapElements[1], "ScnarioPage with panelCombination: " + Arrays.toString(currentState), this.normalizeScenarioMapBusData(mapData), this.normalizeScenarioJsonBusData(JsonData));

                }
            }
        }

        this.terminateDriver();
        this.testObject.postTestCalculations();
    }


    public String[][] normalizeScenarioJsonBusData(String jsonData)
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

        //Create the data ArrayList
        ArrayList<String[]> data = new ArrayList<String[]>();

        //Loop through every element
        while (vmpuKeys.hasNext())
        {
            //Collect the index number
            String currentIndex = vmpuKeys.next();

            //Create an instance to round numbers
            DecimalFormat decimalFormat = new DecimalFormat("0.000");

            //Collect properties from each JsonObject
            String vmpuValue = vmpu.get(currentIndex).toString();
            String vaDegreeValue = vaDegree.get(currentIndex).toString();
            String dlmpValue = dlmp.get(currentIndex).toString();

            //Round values
            String vmpuValueRound = decimalFormat.format(Double.parseDouble(vmpuValue));
            String vaDegreeValueRound = decimalFormat.format(Double.parseDouble(vaDegreeValue));
            String dlmpValueRound = decimalFormat.format(Double.parseDouble(dlmpValue));


            //Construct the list of data values
            String[] busValues = {String.valueOf(Integer.parseInt(currentIndex)), vmpuValueRound, vaDegreeValueRound, dlmpValueRound};

            //Add the values to the data arraylist
            data.add(busValues);
        }

        //Convert it to an Array
        String[][] dataArray = new String[data.size()][];

        for (int i = 0; i < data.size(); i++)
        {
            dataArray[i] = data.get(i);
        }

        //Return dataArray
        return dataArray;

    }

    public String[][] normalizeScenarioMapBusData(String mapData)
    {
        /* Collect bus data for the map */

        //Retrieve the bus Data
        String jsonString = mapData.substring(1, mapData.length() - 1);

        // Split the array content into individual object strings
        String[] objectStrings = jsonString.split("\\},\\s*\\{");

        //Create the data ArrayList
        ArrayList<String[]> data = new ArrayList<String[]>();

        // Iterate over each object string
        for (String objectString : objectStrings)
        {
            //Pull out the properties
            String[] properties = objectString.split("\\}, (\\w+)=\\{")[1].split(", (\\w+)=")[1].substring(5).split("[^\\d.-]+");

            // Filter out empty strings and dots
            String[] propertiesFinal = Arrays.stream(properties)
                    .filter(s -> !s.isEmpty() && !s.equals("."))
                    .toArray(String[]::new);

            propertiesFinal[0] = String.valueOf((Integer.parseInt(propertiesFinal[0])));

            //Create an instance to round numbers
            DecimalFormat decimalFormat = new DecimalFormat("0.000");

            //loop thought the numeric elements of the array
            for(int i = 1; i < propertiesFinal.length; i++)
            {
                //round
                propertiesFinal[i] = decimalFormat.format(Double.parseDouble(propertiesFinal[i]));
            }

            //Add it to the collected data arraylist
            data.add(propertiesFinal);
        }

        //Convert it to an Array
        String[][] dataArray = new String[data.size()][];

        for (int i = 0; i < data.size(); i++)
        {
            dataArray[i] = data.get(i);
        }

        //Return dataArray
        return dataArray;

    }
}
