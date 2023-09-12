package TestAutomations.DLCDemo;

import TestAutomations.Test.Test;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;

import java.text.DecimalFormat;
import java.util.*;

public class DLCDemo extends Test
{

    private String[][] elements = new String[][]
            {
                    { "line-element", "line_data", "p_from_mw", "q_from_mvar", "p_to_mw", "q_to_mvar", "pl_mw", "ql_mvar", "i_from_ka", "i_to_ka", "vm_from_pu", "va_from_degree", "vm_to_pu", "va_to_degree", "loading_percent"},
                    { "bus-element", "bus_data", "vm_pu", "va_degree", "lam_p" },
                    {"transformer-element", "transformer_data", "p_hv_mw", "q_hv_mvar", "p_lv_mw", "q_lv_mvar", "pl_mw", "ql_mvar", "i_hv_ka", "i_lv_ka", "i_lv_ka", "vm_hv_pu", "va_hv_degree", "vm_lv_pu", "va_lv_degree", "loading_percent"},
                    {"evse-element", "evse_data", "p_mw", "q_mvar"},
                    {"pv-element", "pv_data", "p_mw", "q_mvar"},
                    {"load-element", "load_data", "p_mw", "q_mvar"}
            };
    public DLCDemo(TestEngine testEngine, TestObject testObject, int useCase)
    {
        //create the driver
        super(testEngine, testObject, useCase);

    }

    public DLCDemo(TestObject testObject, int useCase, int stepRequested)
    {
        //create the driver
        super(testObject, useCase, stepRequested);

    }

    @Override
    public void test() {
        System.out.println(3);
        //load the page
        this.loadPage(this.testObject.getWebPageURL());

        //Log in
        this.logIn(this.testObject.getWebPageLoginInfo()[0], this.testObject.getWebPageLoginInfo()[1]);

        //wait for a couple of seconds
        this.waitUntilPageLoads("button[aria-label=\"Run Scenario\"]");

        //get how many possibilities exist
        int scenarioPossibilities = this.dropDownOptionsCount("div[aria-label=\"Select a Feeder\"]");


        //loop through all the scenario possibilities
//        String JsonData = null;
//        for (int a = 0; scenarioPossibilities > a; a++)
//        {
//            //select the first dropdown
//            this.selectDropDown("div[aria-label=\"Select a Feeder\"]", a);
//
//            //Select DropDown (Random)
//            this.selectDropDownRandomly("div[aria-label=\"Select a Date\"]");
//            this.selectDropDownRandomly("div[aria-label=\"Select a Time\"]");
//
//            //loop though other possibilities
//            for (int d = 0; d < 3; d++)
//            {
//                //Select DropDown
//                this.selectDropDown("div[aria-label=\"Select an EVSE Mode\"]", d);
//
//                for (int e = 0; e < 2; e++)
//                {
//                    //Select DropDown
//                    this.selectDropDown("div[aria-label=\"Select an Objective Function\"]", e);
//
//                    //Create Current State Array
//                    int[] currentState = {a, -1, -1, d, e};
//
//                    //Click on the run button
//                    this.clickButton("button[aria-label=\"Run Scenario\"]");
//
//                    //Wait until the scenario is loaded
//                    this.waitUntilChange(45, ".p-button", "class", "p-button-success");
//
//                    //extract the json data
//                    JsonData = this.requestDataFromJavaScriptConsole("return sessionStorage.resultScenario;");
//
//                    for (String[] elementData : this.elements)
//                    {
//                        //extract the busData
//                        String mapData = this.requestDataFromJavaScriptConsole("return map.current.getSource('" + elementData[0] + "')._data.features;");
//
//                        //Run a test
//                        if (!this.runTest(elementData[0], this.normalizeScenarioMapData(mapData, elementData), this.normalizeScenarioJsonData(JsonData, elementData)))
//                        {
//                            System.out.println("out");
//                            return;
//                        }
//
//                        break;
//                    }
//
//                    break;
//                }
//                break;
//            }
//            break;
//        }

        System.out.println("network");
//        /* Go to the next page */
//        this.loadPage(this.testObject.getWebPageURL() + "#/dashboard/network");
//
//
//        //Pull out the data for the webpage
//        List<WebElement> summaryTable = this.requestDataFromWebElement("td");
//
//        //Get the summary table form the json file
//        JSONObject jsonObject = new JSONObject(JsonData);
//
////        JSONObject summaryTableJson = jsonObject.getJSONObject("summary_table");
//
//        System.out.println(summaryTableJson.toString());
//        //get the keys
//        String[] keys = summaryTableJson.keySet().toArray(new String[0]);
//
//        //Create the arrays
//        String[][] actualData = new String[keys.length][];
//        String[][] expectedData = new String[keys.length][];
//
//        //loop through all the elements
//        for (int i = 0; i < keys.length; i++)
//        {
//            //populate the actualData
//            actualData[i] = new String[]{String.valueOf(i), String.valueOf(summaryTable.get(i).getText())};
//
//        }
//
//        //populate the first element of the expected data
//        expectedData[0] = new String[] {String.valueOf(0), jsonObject.getString("date")};
//
//        //"PV Total (kW-h)"
//        String[] dataCollection = new String[]{"Load (kW-h)", "Maximum Transformer Loading (%)", "V2G Total (kW-h)", "G2V Total (kW-h)", "GHG Reduction (ton)", "Loss (kW-h)", "Loss Cost ($)", "Co2 Emission Cost Reduction ($)", "Maximum Line Loading (%)", "Minimum Nodal Voltage (p.u.)", "Maximum Nodal Voltage (p.u.)", };
//
//        //loop through the rest of the elements
//        for (int i = 1; i < keys.length; i++)
//        {
//            expectedData[i] = new String[]{String.valueOf(i), String.valueOf(summaryTableJson.get(dataCollection[i - 1]))};
//        }
//
//        //Run a test
//        if (!this.runTest("Summary Table", actualData, expectedData))
//        {
//            System.out.println("out");
//            return;
//        }

//        //collect data from \
//        //pull data from Forecasted Load
//        JSONArray jsonArray = new JSONArray(this.requestDataFromJavaScriptConsole("return ForecastLoad.props.data.datasets[0].data"));
//
//        //convert jsonArray into a 2d arrayList
//        String[][] actualData = new String[24][];
//
//        for (int i = 0; i < jsonArray.length(); i++)
//        {
//            actualData[i] = new String[] {String.valueOf(i), String.valueOf(jsonArray.get(i))};
//        }
//
//        // collect json-array form JSON DATA
//        JSONArray forecastLoadArray = jsonObject.getJSONArray("forecasted_load_data");
//
//        String[][] expectedData = new String[24][];
//
//        for (int i = 0; i < forecastLoadArray.length(); i++)
//        {
//            expectedData[i] = new String[]{String.valueOf(i), String.valueOf(forecastLoadArray.get(i))};
//        }
//
//
//        //run a test
//        if (!this.runTest("Summary Table", actualData, expectedData))
//        {
//            System.out.println("out");
//            return;
//        }
//
//        //do the same process for the other objects
//        JSONArray forcastPVJsonArray = new JSONArray(this.requestDataFromJavaScriptConsole("return ForecastPV.props.data.datasets[0].data"));
//
//        //convert jsonArray into a 2d arrayList
//        String[][] forecastPVActualData = new String[24][];
//
//        for (int i = 0; i < forcastPVJsonArray.length(); i++)
//        {
//            forecastPVActualData[i] = new String[] {String.valueOf(i), String.valueOf(forcastPVJsonArray.get(i))};
//        }
//
//        // collect json-array form JSON DATA
//        JSONArray forecastPVArray = jsonObject.getJSONArray("forecasted_pv_data");
//
//        String[][] forecastPVExpectedData = new String[24][];
//
//        for (int i = 0; i < forecastPVArray.length(); i++)
//        {
//            forecastPVExpectedData[i] = new String[]{String.valueOf(i), String.valueOf(forecastPVArray.get(i))};
//        }
//
//
//        //run a test
//        if (!this.runTest("ForecastPV Graph", forecastPVActualData, forecastPVExpectedData))
//        {
//            System.out.println("out");
//            return;
//        }
//
//        //continue to the forecasted LMP
//        //do the same process for the other objects
//        JSONArray forcastLMPJsonArray = new JSONArray(this.requestDataFromJavaScriptConsole("return ForecastPV.props.data.datasets[0].data"));
//
//        //convert jsonArray into a 2d arrayList
//        String[][] forecastLMPActualData = new String[24][];
//
//        for (int i = 0; i < forcastLMPJsonArray.length(); i++)
//        {
//            forecastLMPActualData[i] = new String[] {String.valueOf(i), String.valueOf(forcastLMPJsonArray.get(i))};
//        }
//
//        // collect json-array form JSON DATA
//        JSONArray forecastLMPArray = jsonObject.getJSONArray("forecasted_pv_data");
//
//        String[][] forecastLMPExpectedData = new String[24][];
//
//        for (int i = 0; i < forecastLMPArray.length(); i++)
//        {
//            forecastLMPExpectedData[i] = new String[]{String.valueOf(i), String.valueOf(forecastLMPArray.get(i))};
//        }
//
//
//        //run a test
//        if (!this.runTest("ForecastPV Graph", forecastLMPActualData, forecastLMPExpectedData))
//        {
//            System.out.println("out");
//            return;
//        }
//
//        /* Total Feeder Contiodn page */
//
//        this.loadPage(this.testObject.getWebPageURL() + "#/dashboard/totalFeederCondition");
//
//        JSONArray transformerArray = new JSONArray(this.requestDataFromJavaScriptConsole("return Transformer.props.data.datasets[0].data"));
//
//        String[][] transformerActualData = new String[26][];
//
//
//        for (int i = 0; i < transformerArray.length(); i++)
//        {
//            transformerActualData[i] = new String[]{String.valueOf(i), String.valueOf(transformerArray.get(i))};
//        }
//
//        JSONObject transformerJsonData = jsonObject.getJSONObject("transformer_data").getJSONObject("loading_percent");
//
//        String[][] transformerExpectedData = new String[26][];
//
//        for (int i= 0; i < 26; i++)
//        {
//            transformerExpectedData[i] = new String[]{String.valueOf(i), String.valueOf(transformerJsonData.get(String.valueOf(i)))};
//        }
//
//        //run a test
//        if (!this.runTest("Transformer Graph", transformerActualData, transformerExpectedData))
//        {
//            System.out.println("out");
//            return;
//        }
//
//        //test the voltage magnitude
//        JSONArray voltageMagnitudeArrayEVSE = new JSONArray(this.requestDataFromJavaScriptConsole("return VoltageMagnitude.props.data.datasets[0].data"));
//        JSONArray voltageMagnitudeArrayPV = new JSONArray(this.requestDataFromJavaScriptConsole("return VoltageMagnitude.props.data.datasets[1].data"));
//
//        String[][] voltageMagnitudeActualDataArray = new String[voltageMagnitudeArrayPV.length()][];
//
//        for (int i=0; i < voltageMagnitudeArrayPV.length(); i++)
//        {
//            voltageMagnitudeActualDataArray[i] = new String[]{String.valueOf(i), String.valueOf(voltageMagnitudeArrayEVSE.get(i)), String.valueOf(voltageMagnitudeArrayPV.get(i))};
//        }
//
//        //collect the voltage labels
//        JSONArray busLabels = new JSONArray(this.requestDataFromJavaScriptConsole("return VoltageMagnitude.props.data.labels"));
//
//        String[] busLabelsArray = new String[busLabels.length()];
//
//        for (int i = 0; i < busLabels.length(); i++)
//        {
//            busLabelsArray[i] = busLabels.getString(i).split(" ")[1];
//        }
//
//        //load the related data from the JsonObject
//
//        JSONObject vm_pu = jsonObject.getJSONObject("bus_data").getJSONObject("vm_pu");
//        String[][] voltageMagnitudeExpectedDataArray = new String[voltageMagnitudeArrayPV.length()][];
//
//        for (int i = 0; i < voltageMagnitudeExpectedDataArray.length; i++)
//        {
//            voltageMagnitudeExpectedDataArray[i] = new String[]{String.valueOf(i), String.valueOf(vm_pu.get(busLabelsArray[i])), String.valueOf(vm_pu.get(busLabelsArray[i]))};
//        }
//
//        //Test Graph
//        if (!this.runTest("Voltage Magnitude Graph", voltageMagnitudeActualDataArray, voltageMagnitudeExpectedDataArray))
//        {
//            System.out.println("out");
//            return;
//        }
//
//        //ToDo: Nodal Voltages Graph

        /* Time Series Page */
        this.loadPage(this.testObject.getWebPageURL() + "#/dashboard/timeSeries");

        //wait for a couple of seconds
        this.waitUntilPageLoads("button[aria-label=\"Run Scenario\"]");

        //get how many possibilities exist
        int scenarioPossibilitiesTimeSeries = this.dropDownOptionsCount("div[aria-label=\"Select a Feeder\"]");


        System.out.println(scenarioPossibilitiesTimeSeries);
        for (int a = 0; scenarioPossibilitiesTimeSeries > a; a++)
        {
            //select the first dropdown
            this.selectDropDown("div[aria-label=\"Select a Feeder\"]", a);

            //Select DropDown (Random)
            this.selectDropDownRandomly("div[aria-label=\"Select a Date\"]");

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
                    this.waitUntilChange(60, ".p-button", "class", "p-button-success");

                    //ToDo: read data and test
                }
            }
        }
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



            //loop thought the numeric elements of the array
            for(int i = 1; i < propertiesFinal.length; i++)
            {

                try
                {

                    propertiesFinal[i] = (String) decimalFormat.format(Double.parseDouble(propertiesFinal[i]));



                    if (propertiesFinal[i].equals("-0.000"))
                    {
                        propertiesFinal[i] = "0.000";
                    }
                }
                catch (Exception e)
                {

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
