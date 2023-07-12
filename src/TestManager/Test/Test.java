package TestManager.Test;

import java.util.Arrays;

public class Test
{
    public final String[][] actualValues;
    private final String[][] expectedValues;
    private final String testElement;

    private final String scenario;

    private String[][] testResults;

    private Boolean status;
    private String message;

    public Test (String testElement, String scenario, String[][] actualValue, String[][] expectedValue)
    {
        //SetUp object properties
        this.testElement = testElement;
        this.scenario = scenario;
        this.actualValues = actualValue;
        this.expectedValues = expectedValue;

    }

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

    public void generateResults()
    {
        //Loop through all the results
        for (String[] result: this.testResults)
        {
            boolean allTrue = true;
            //Check if all the properties are true
            for (int i = 1; i < result.length; i++)
            {
                //If a value is not True
                if (!(result[i].equals("True")))
                {

                    //Report False
                    allTrue = false;
                }
            }

            if (allTrue == false)
            {
                //Create the Issue
                
            }

        }
    }



}
