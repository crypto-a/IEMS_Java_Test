package TestAutomations.DLCDemo;

import TestAutomations.Test.Test;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DLCDemo extends Test
{

    private TestObject testObject;
    private TestEngine testEngine;

    private String[][] elements = new String[][]
            {
                    { "line-element", "line_data", "p_from_mw", "q_from_mvar", "p_to_mw", "q_to_mvar", "pl_mw", "ql_mvar", "i_from_ka", "i_to_ka", "vm_from_pu", "va_from_degree", "vm_to_pu", "va_to_degree", "loading_percent"},
                    { "bus-element", "bus_data", "vm_pu", "va_degree", "lam_p" },
//                    {"transformer-element", "transformer_data"},
//                    {"evse-element", "evse_data"},
                    {"pv-element", "pv_data", "p_mw", "q_mvar"},
                    {"load-element", "load_data", "p_mw", "q_mvar"}

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

                    for (String[] elementData: this.elements)
                    {
                        //extract the busData
                        String mapData = this.requestDataFromJavaScriptConsole("return map.current.getSource('" + elementData[0] + "')._data.features;");

                        //Run a test
                        this.testEngine.createNewTestElement(elementData[0], "ScnarioPage with panelCombination: " + Arrays.toString(currentState), this.normalizeScenarioMapData(mapData, elementData), this.normalizeScenarioJsonData(JsonData, elementData));
                    }

                }
            }
        }

        this.terminateDriver();
        this.testObject.postTestCalculations();
    }



    public String[][] normalizeScenarioJsonData(String jsonData, String[] jsonElements)
    {
        //Save the file as a Json Object
        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject(jsonElements[1]);


        ArrayList<JSONObject> jsonObjectsData = new ArrayList<>();

        for (int i=2; i < jsonElements.length; i++)
        {
            jsonObjectsData.add((JSONObject) jsonObject.get(jsonElements[i]));
        }

        //Construct the final 2d array
        String[][] finalArray = new String[jsonObjectsData.get(0).length()][];


        //Create an Iterator object for one element
        Iterator<String> elementZeroKeys = jsonObjectsData.get(0).keys();

        while(elementZeroKeys.hasNext())
        {
            //Collect the index number
            String currentIndex = elementZeroKeys.next();

            //Create an instance to round numbers
            DecimalFormat decimalFormat = new DecimalFormat("0.000");

            String[] testStructure = new String[jsonElements.length - 1];

            testStructure[0] = currentIndex;

            //loop through all the elements
            for (int i = 0; i < jsonObjectsData.size(); i++)
            {
                testStructure[i + 1] = (String) decimalFormat.format(Double.parseDouble(jsonObjectsData.get(i).get(currentIndex).toString()));
            }

            finalArray[Integer.parseInt(currentIndex)] = testStructure;
        }




        return finalArray;
    }
    public String[][] normalizeScenarioMapData(String mapData, String[] elementData)
    {
        //Retrieve the bus Data
        String jsonString = mapData.substring(1, mapData.length() - 1);

        // Split the array content into individual object strings
        String[] objectStrings = jsonString.split("\\},\\s*\\{");

        //Create the data ArrayList
        ArrayList<String[]> data = new ArrayList<>();

        // Iterate over each object string
        for (String objectString : objectStrings)
        {
            String[] properties = objectString.split("\\}, (\\w+)=\\{")[1].split(", (\\w+)=")[1].substring(elementData[0].split("-")[0].length() + 1).split("[^\\d.-]+");

            // Filter out empty strings and dots
            String[] propertiesFinal = Arrays.stream(properties)
                    .filter(s -> !s.isEmpty() && !s.equals(".") && !s.equals("-"))
                    .toArray(String[]::new);

            //propertiesFinal[0] = String.valueOf((Integer.parseInt(propertiesFinal[0])));

            //Create an instance to round numbers
            DecimalFormat decimalFormat = new DecimalFormat("0.000");

            System.out.println(propertiesFinal[0]);

            //loop thought the numeric elements of the array
            for(int i = 1; i < propertiesFinal.length; i++)
            {

                try
                {
                    System.out.println(propertiesFinal[i]);
                    propertiesFinal[i] = (String) decimalFormat.format(Double.parseDouble(propertiesFinal[i]));

                    System.out.println(propertiesFinal[i]);

                    if (propertiesFinal[i].equals("-0.000"))
                    {
                        propertiesFinal[i] = "0.000";
                    }
                }
                catch (Exception e)
                {
                    System.out.println(propertiesFinal[i]);

                    propertiesFinal[i] = (String) decimalFormat.format(Double.parseDouble(propertiesFinal[i]));

                    if (propertiesFinal[i].equals("-0.000"))
                    {
                        propertiesFinal[i] = "0.000";
                    }
                }
                //round

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
