package TestManager.TestObject;

import TestManager.IssueElement.IssueElement;
import TestManager.TestElement.TestElement;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestObject
{
    private int testID;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private Duration testDuration;
    private String issuer;
    private String targetedWebPage;
    private String webPageURL;
    private ArrayList<TestElement> testElements = new ArrayList<TestElement>();
    private ArrayList<IssueElement> issueElements = new ArrayList<IssueElement>();
    private ArrayList<String> testLogs = new ArrayList<String>();
    private ArrayList<Duration> backEndResponseTimes = new ArrayList<Duration>();

    private int numberOfTests;
    private int estimatedTime;
    private Duration averageBackEndResponseTime;
    private String[] webPageLoginInfo;


    /*****************************************
     /*Method Name: TestObject
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Tests
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestObject(String issuer, String targetedWebPage, String webPageURL, String[] webPageLoginInfo )
    {
        //SetUp object Properties
        this.issuer = issuer;
        this.targetedWebPage = targetedWebPage;
        this.webPageURL = webPageURL;
        this.webPageLoginInfo = webPageLoginInfo;

        //Record the time of starting a test
        this.testStartTime = LocalDateTime.now();

        /* Run Tests */

        //Check to see witch test was requested
        switch (this.targetedWebPage)
        {
            case "DLC Demo":
                //ToDO
                break;

            case "DERMS":
                //ToDO
                break;
            case "PA SaaS":
                //ToDO
                break;
            case "CALFUSE":
                //ToDO
                break;
        }

        /* Do after Test Processes */

        //Record the end Time of the test
        this.testEndTime = LocalDateTime.now();

        //Calculate the duration of the test
        testDuration = Duration.between(testStartTime, testEndTime);

        // Calculate the total duration for server responseTime
        Duration totalDuration = Duration.ZERO;
        for (Duration duration : backEndResponseTimes) {
            totalDuration = totalDuration.plus(duration);
        }

        // Calculate the average duration
        long numDurations = backEndResponseTimes.size();

        //Save value to the server
        this.averageBackEndResponseTime = totalDuration.dividedBy(numDurations);

    }

    /*****************************************
     /*Method Name: TestObject
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for when the object is being built from the database info
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestObject()
    {

    }

    /*****************************************
     /*Method Name: runTestDLCDemo
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is in charge of doing tests on the DLC demo page
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    private synchronized void runTestDLCDemo()
    {
        //ToDo
    }
}
