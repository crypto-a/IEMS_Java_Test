package TestEngine.TestElement;

import TestEngine.TestObject.TestObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestElement
{
    private final int testID;
    public final String[][] actualValues;
    private final String[][] expectedValues;
    private final String testElementIdentification;
    private final String scenario;
    private String[][] testResults;

    private TestObject parentTestObject;
    private Boolean status;
    private String testLog;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private Duration processDuration;

    /*****************************************
     /*Method Name: TestElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Tests
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestElement(TestObject parentTestObject, String testElementIdentification, String scenario, String[][] actualValue, String[][] expectedValue)
    {
        //SetUp object Properties
        this.parentTestObject = parentTestObject;
        this.testID = parentTestObject.testID;
        this.testElementIdentification = testElementIdentification;
        this.scenario = scenario;
        this.actualValues = actualValue;
        this.expectedValues = expectedValue;
        this.testResults = new String[0][];

        //Record the time of starting the test
        this.testStartTime = LocalDateTime.now();


    }

    /*****************************************
     /*Method Name: TestElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the old tests in called form the database
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
//    public TestElement()
//    {
//
//    }

    public void compareValues()
    {
        /* {id, value} */

        //Loop through the elements of the actualValues
        for (String[] actualValue : actualValues)
        {
            //Loop through the elements of the expectedValues
            for (String[] expectedValue : expectedValues)
            {
                //Check to match with the same value
                if (actualValue[0].equals(expectedValue[0]))
                {
                    //Create the results array to record the results
                    String[] results = new String[actualValue.length];

                    //Set the ID in the first value of results
                    results[0] = actualValue[0];

                    //Loop through every other element
                    for (int i = 1; i < actualValue.length; i++)
                    {
                        if (actualValue[i].equals(expectedValue[i]))
                        {
                            results[i] = "True";
                        }
                        else
                        {
                            results[i] = "False";
                        }
                    }

                    //Add results to the testResults array
                    this.testResults = Arrays.copyOf(this.testResults, this.testResults.length + 1);
                    this.testResults[this.testResults.length - 1] = results;
                }
            }

        }
    }

    public void generateResults() {
        //Loop through all the results
        for (String[] result : this.testResults) {
            System.out.println(Arrays.toString(result));
            boolean allTrue = true;
            //Check if all the properties are true
            for (int i = 1; i < result.length; i++) {
                //If a value is not True
                if (!(result[i].equals("True"))) {

                    //Report False
                    allTrue = false;
                }

            }

            if (allTrue == false) {
                //Create the Issue
                System.out.println(result[0] + " - Failed");

            } else {
                System.out.println(result[0] + " - Passed");
            }

        }
    }
}
