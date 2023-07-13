package TestManager.DataProcessor;

import org.json.*;

import java.text.DecimalFormat;
import java.util.*;

public class DataProcessor
{
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
