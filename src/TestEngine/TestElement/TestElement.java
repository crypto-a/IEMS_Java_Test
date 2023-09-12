package TestEngine.TestElement;

import TestEngine.TestObject.TestObject;
import org.bson.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestElement
{
    private Object testID;
    public String[][] actualValues;
    private String[][] expectedValues;
    private String testElementIdentification;
    private int scenario;
    private String[][] testResults;
    private Boolean didPass;
    private String testLog;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private Duration processDuration;
    private int numberOfErrors;
    private TestObject testObject;

    /*****************************************
     /*Method Name: TestElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Tests
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestElement(TestObject testObject, Object testID, String testElementIdentification, int scenario, String[][] actualValue, String[][] expectedValue)
    {
        //SetUp object Properties
        this.testObject = testObject;
        this.testID = testID;
        this.testElementIdentification = testElementIdentification;
        this.scenario = scenario;
        this.actualValues = actualValue;
        this.expectedValues = expectedValue;
        this.testResults = new String[0][];

        //Record the time of starting the test
        this.testStartTime = LocalDateTime.now();

        //setup number of errors to zero
        this.numberOfErrors = 0;

        //run the tests
        this.checkValues();

        //record endtime
        this.testEndTime = LocalDateTime.now();

        //Calculate Duration
        this.processDuration = Duration.between(this.testStartTime, this.testEndTime);

    }

    /*****************************************
     /*Method Name: TestElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the old tests in called form the database
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestElement(Document testElementDoc)
    {
        //SetUp object Properties
        this.testID = testElementDoc.get("_id");
        this.didPass = testElementDoc.getBoolean("didPass");
        this.testElementIdentification = testElementDoc.getString("testElementIdentification");
        this.scenario = testElementDoc.getInteger("scenario");
        this.actualValues = this.convertListListToStringArray((List<List<String>>) testElementDoc.get("actualValue"));
        this.expectedValues = this.convertListListToStringArray((List<List<String>>) testElementDoc.get("expectedValue"));
        this.testResults = this.convertListListToStringArray((List<List<String>>) testElementDoc.get("testResults"));
        this.testLog = testElementDoc.getString("testLog");
        this.testStartTime = LocalDateTime.parse(testElementDoc.getString("startTime"));
        this.testEndTime = LocalDateTime.parse(testElementDoc.getString("endTime"));
        this.numberOfErrors = testElementDoc.getInteger("numberOfErrors");

        //Calculate Duration
        this.processDuration = Duration.between(this.testStartTime, this.testEndTime);
    }

    public void updateObject(Document newTestElementDoc)
    {
        System.out.println(2);
        //SetUp object Properties
        this.testID = newTestElementDoc.get("_id");
        this.didPass = newTestElementDoc.getBoolean("didPass");
        this.testElementIdentification = newTestElementDoc.getString("testElementIdentification");
        this.scenario = newTestElementDoc.getInteger("scenario");
        System.out.println(2);
        this.actualValues = this.convertListListToStringArray((List<List<String>>) newTestElementDoc.get("actualValue"));
        this.expectedValues = this.convertListListToStringArray((List<List<String>>) newTestElementDoc.get("expectedValue"));
        this.testResults = this.convertListListToStringArray((List<List<String>>) newTestElementDoc.get("testResults"));
        this.testLog = newTestElementDoc.getString("testLog");
        this.testStartTime = LocalDateTime.parse(newTestElementDoc.getString("startTime"));
        this.testEndTime = LocalDateTime.parse(newTestElementDoc.getString("endTime"));
        this.numberOfErrors = newTestElementDoc.getInteger("numberOfErrors");


        //Calculate Duration
        this.processDuration = Duration.between(this.testStartTime, this.testEndTime);
    }


    private void checkValues()
    {
        /* {id, value} */

        //Loop through every element of the actualValues
        for (String[] actualValue : this.actualValues)
        {
            //Loop through the elements of the expectedValues
            for (String[] expectedValue : this.expectedValues)
            {
                //Check to match with the same value
                if (actualValue[0].equals(expectedValue[0]))
                {
                    //create tje result array
                    String[] result = new String[actualValue.length];
                    result[0] = actualValue[0];
                    //Collect the property of it

                    //check if the two arrays are the same
                    if (!(Arrays.equals(actualValue, expectedValue)))
                    {
                        /* If the two arrays are not the same*/

                        //Loop through every other element to find the issue
                        for (int i = 1; i < actualValue.length; i++)
                        {
                            if (actualValue[i].equals("-0.000"))
                            {
                                actualValue[i] = "0.000";
                            }
                            if (expectedValue[i].equals("-0.000"))
                            {
                                expectedValue[i] = "0.000";
                            }
                            if (actualValue[i].equals(expectedValue[i]))
                            {
                                result[i] = "True";
                            } else
                            {
                                result[i] = "False";
                                //Incriment issue counter
                                this.numberOfErrors++;

                                //Construct a message
                                String errormessage = this.testElementIdentification + " " + actualValue[0] + ": value Arrays are not equal";

                                //Create an issue object
                                this.createIssue(this.scenario, Arrays.toString(expectedValue), Arrays.toString(actualValue), errormessage);
                            }
                        }

                    } else
                    {
                        for (int i = 1; i < actualValue.length; i++)
                        {
                            result[i] = "True";
                        }
                    }

                    //Add result to the testResults array
                    this.testResults = Arrays.copyOf(this.testResults, this.testResults.length + 1);
                    this.testResults[this.testResults.length - 1] = result;
                }
            }
        }

        //check how many errors there was
        if (this.numberOfErrors == 0)
        {
            //set test status to pass
            this.didPass = true;

            //create a testLog
            this.testLog = this.testID + " - Passed";
        } else
        {
            this.didPass = false;
            //create a testLog
            this.testLog = this.testID + " - Failed";
        }
    }

    private void createIssue(int scenario, String expectedValue, String actualValue, String errorMessage)
    {
        //call the parent test-object to create the issue
        this.testObject.createNewIssue(this.testElementIdentification, scenario, expectedValue, actualValue, errorMessage);
    }

    public String getTestID()
    {
        //return test

        return this.testID.toString();
    }

    public Boolean getDidPass()
    {
        //return test status
        return this.didPass;
    }

    public String getErrorsNum()
    {
        //return the number of errors
        return String.valueOf(this.numberOfErrors);
    }

    public int getScenario()
    {
        System.out.println(this.scenario);
        //return scenario
        return this.scenario;
    }

    public String getTestElementIdentification()
    {
        //Return the test ID
        return this.testElementIdentification;
    }

    public String[][][] getTestData()
    {
        //return the test data
        return new String[][][]{this.expectedValues, this.actualValues, this.testResults};
    }

    public Document getAsDocument()
    {
        System.out.println("here");
        Document testDoc = new Document();
        testDoc.append("_id", this.testID);
        testDoc.append("didPass", this.didPass);
        testDoc.append("testElementIdentification", this.testElementIdentification);
        testDoc.append("scenario", this.scenario);
        testDoc.append("actualValue", this.convetStringArrayTo2DList(this.actualValues));
        testDoc.append("expectedValue", this.convetStringArrayTo2DList(this.expectedValues));
        testDoc.append("testResults", this.convetStringArrayTo2DList(this.testResults));
        testDoc.append("testLog", this.testLog);
        testDoc.append("startTime", this.testStartTime.toString());
        testDoc.append("endTime", this.testEndTime.toString());
        testDoc.append("numberOfErrors", this.numberOfErrors);

        return testDoc;
    }

    private List<List<String>> convetStringArrayTo2DList(String[][] twoDArray)
    {
        //Convert arrays to lists
        List<List<String>> listOfLists = new ArrayList<>();
        for (String[] row : twoDArray) {
            listOfLists.add(Arrays.asList(row));
        }

        return listOfLists;
    }
    private String[][] convertListListToStringArray(List<List<String>> listOfLists)
    {

        if (listOfLists.size() == 0)
        {
            return null;
        }

        int rows = listOfLists.size();
        int cols = listOfLists.get(0).size();

        String[][] stringArray = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            List<String> list = listOfLists.get(i);
            for (int j = 0; j < cols; j++) {
                stringArray[i][j] = list.get(j);
            }
        }

        return stringArray;
    }

    public String getStatus()
    {
        if (didPass)
        {
            return "Passed";
        }
        else
        {
            return "Failed";
        }
    }

    public String getTestLog()
    {
        //Return the testLog
        return this.testLog;
    }


}
