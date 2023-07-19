package TestEngine.TestElement;

import TestEngine.TestObject.TestObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestElement {
    private final int testID;
    public final String[][] actualValues;
    private final String[][] expectedValues;
    private final String testElementIdentification;
    private final String scenario;
    private String[][] testResults;

    private TestObject parentTestObject;
    private String status;
    private String testLog;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private Duration processDuration;

    private int numberOfErrors;

    /*****************************************
     /*Method Name: TestElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Tests
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestElement(TestObject parentTestObject, String testElementIdentification, String scenario, String[][] actualValue, String[][] expectedValue) {
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

        //setup number of errors to zero
        this.numberOfErrors = 0;


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
                        //Construct a message
                        String errormessage = this.testElementIdentification + " " + actualValue[0] + ": value Arrays are not equal";

                        //Create an issue object
                        this.parentTestObject.createIssue(this.scenario, Arrays.toString(expectedValue), Arrays.toString(actualValue), errormessage);

                        //Incriment issue counter
                        this.numberOfErrors++;

                        //Loop through every other element to find the issue
                        for (int i = 1; i < actualValue.length; i++)
                        {
                            if (actualValue[i].equals(expectedValue[i]))
                            {
                                result[i] = "True";
                            } else
                            {
                                result[i] = "False";
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
            this.status = "Passed";

            //create a testLog
            this.testLog = this.testID + " - " + this.scenario + " ------- " + this.status;
        } else
        {
            this.status = "Failed";
            //create a testLog
            this.testLog = this.testID + " - " + this.scenario + " ------- " + this.status + " - " + this.numberOfErrors + "errors detected";
        }
    }

}