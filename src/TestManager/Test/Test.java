package TestManager.Test;

import TestManager.DataProcessor.DataProcessor;
import TestManager.UserInterfaceReader.UserInterfaceReader;
import TestManager.subTest.subTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Test
{
    private final int testID;
    private final LocalDateTime testStartDateTime;
    private final String webAppUrl;
    private final Boolean[] requestedTest;
    private ArrayList<subTest> subTests = new ArrayList<subTest>();

    public Test(String webAppUrl, Boolean[] requestedTest)
    {
        this.testID = 119384302;
        this.testStartDateTime = LocalDateTime.now();
        this.webAppUrl = webAppUrl;
        this.requestedTest = requestedTest;

        /* Start Doing Tests */
        for (int i = 0; i < this.requestedTest.length; i++)
        {
            //If test is requested
            if (this.requestedTest[i])
            {
                switch(i)
                {
                    case 0:
                        /* if UI Test is Requested */
                        this.UserInterfaceTesting(this.webAppUrl);
                        break;
                        //ToDo: Complete the other test Types

                    default:
                        break;
                }
            }
        }

    }

    public Test(int testID, LocalDateTime testStartDateTime, String webAppUrl, Boolean[] requestedTest)
    {
        this.testID = testID;
        this.testStartDateTime = testStartDateTime;
        this.webAppUrl = webAppUrl;
        this.requestedTest = requestedTest;
    }

    private void UserInterfaceTesting(String url)
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

        System.out.println(this.testStartDateTime);

        //Create the Instance of the UserInterFaceReader object
        UserInterfaceReader userInterfaceReader = new UserInterfaceReader(url);

        //Create an Instance of the Data processor
        DataProcessor dataProcessor = new DataProcessor();

        /* Start with the Scenario section Testing */

        //Count the number of feeders on the platform
        int[] scenarios = {userInterfaceReader.dropDownOptionsCount("Select a Feeder"), -1, -1, 3, 2};

        //Loop through every possibility
        for (int a = 1; scenarios[0] >= a; a++) {
            //Select DropDown
            userInterfaceReader.dropDownButtonClick("Select a Feeder", a);

            for (int b = -2; b < -1; b++) {
                //Select DropDown (Random)
                userInterfaceReader.dropDownButtonRandomClick("Select a Date");

                for (int c = -2; c < -1; c++) {
                    //Select DropDown (Random)
                    userInterfaceReader.dropDownButtonRandomClick("Select a Time");

                    for (int d = 1; d <= scenarios[3]; d++) {
                        //Select DropDown
                        userInterfaceReader.dropDownButtonClick("Select an EVSE Mode", d);

                        for (int e = 1; e <= scenarios[4]; e++) {
                            //Select DropDown
                            userInterfaceReader.dropDownButtonClick("Select an Objective Function", e);

                            //Create Current State Array
                            int[] currentState = {a, b, c, d, e};

                            //Run Scenario
                            userInterfaceReader.runScenario();

                            //Request JsonObject
                            String jsonFile = userInterfaceReader.jsonDateRetrieve();

                            /* Test Bus Data */
                            //Request the data
                            String[][] givenData = dataProcessor.normalizeScenarioMapBusData(userInterfaceReader.mapDataRetrieve("bus-element"));
                            String[][] expectedDate = dataProcessor.normalizeScenarioJsonBusData(jsonFile);

                            //Save subTest to array
                            this.subTests.add(new subTest(this.testID, "bus-element", Arrays.toString(currentState), givenData, expectedDate));
                        }
                    }
                }
            }
        }


        //Terminate Driver
        userInterfaceReader.terminateDriver();
    }

    public String getTestID()
    {
        //Return test ID
        return String.valueOf(this.testID);
    }

    public String getTestDate()
    {
        return this.testStartDateTime.toString().split("T")[0];
    }

    public String getTestTime()
    {
        return this.testStartDateTime.toString().split("T")[1];
    }

}
