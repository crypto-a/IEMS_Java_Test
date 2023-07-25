package TestEngine.TestObject;

import TestAutomations.DLCDemoTest.DLCDemoTest;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestObject
{
    public Object testID;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private final Duration testDuration;
    private final Object issuer;
    private final String targetedWebPage;
    private final String webPageURL;
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
    public TestObject(Object issuer, String targetedWebPage, String webPageURL, String[] webPageLoginInfo )
    {
        //SetUp object Properties
        this.issuer = issuer;
        this.targetedWebPage = targetedWebPage;
        this.webPageURL = webPageURL;
        this.webPageLoginInfo = webPageLoginInfo;

        //Record the time of starting a test
        this.testStartTime = LocalDateTime.now();

        //Set up objetc ID
        this.testID = new ObjectId();

        /* Run Tests */

        //Check to see witch test was requested
        switch (this.targetedWebPage)
        {
            case "DLC Demo":
                //SetUp the Thread
                Thread thread = new Thread((Runnable) new DLCDemoTest(this, this.webPageURL, webPageLoginInfo));

                //Start the thread
                thread.start();

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
        this.testDuration = Duration.between(testStartTime, testEndTime);

        if (backEndResponseTimes.size() != 0)
        {
            // Calculate the total duration for server responseTime
            Duration totalDuration = Duration.ZERO;
            for (Duration duration : backEndResponseTimes)
            {
                totalDuration = totalDuration.plus(duration);
            }

            // calculate the average duration and Save value to the server
            this.averageBackEndResponseTime = totalDuration.dividedBy(backEndResponseTimes.size());
        }


    }

    /*****************************************
     /*Method Name: TestObject
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for when the object is being built from the database info
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestObject(Object testID, LocalDateTime testStartTime, LocalDateTime testEndTime, Object issuer, String targetedWebPage, String webPageURL, ArrayList<TestElement> testElements, ArrayList<IssueElement> issueElements, ArrayList<String> testLogs, int numberOfTests)
    {
        //SetUp Object properties
        this.testID = testID;
        this.testStartTime = testStartTime;
        this.testEndTime = testEndTime;
        this.issuer = issuer;
        this.targetedWebPage = targetedWebPage;
        this.webPageURL = webPageURL;
        this.testElements = testElements;
        this.issueElements = issueElements;
        this.testLogs = testLogs;
        this.numberOfTests = numberOfTests;

        //Calculate Duration
        this.testDuration = Duration.between(this.testStartTime, this.testEndTime);


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

    /*****************************************
     /*Method Name: testScenario
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is in charge of testing a scenario with standardized data;
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public void testScenario(String testElementIdentification, String scenario, String[][] actualValue, String[][] expectedValue)
    {
        //Create the new Test Element Object and Add it to the arrayList
        this.testElements.add(new TestElement(this, testElementIdentification, scenario, actualValue, expectedValue));

    }

    public void createIssue(String scenario, String expectedValue, String actualValue, String errorMessage)
    {
        //this.issueElements.add(new IssueElement(scenario, expectedValue, actualValue, errorMessage));
    }

    public String getTestID()
    {
        //Return test ID
        return String.valueOf(this.testID);
    }

    public String getTestDate()
    {
        //Return the Date
        return this.testStartTime.toString().split("T")[0];
    }

    public String getTestTime()
    {
        //Return the time
        return this.testStartTime.toString().split("T")[1].split("\\.")[0];
    }

    public Object getIssuerID()
    {
        //Return the ID of the Issuer
        return this.issuer;
    }

    public String getTargetedWebPage()
    {
        //return the targetedWebPage value
        return this.targetedWebPage;
    }

    public String getWebPageURL()
    {
        //return the webPageURL value
        return this.webPageURL;
    }

    public String getDuration()
    {
        if (this.testDuration != null)
        {
            //Collect the time values
            long hours = this.testDuration.toHours();
            long minutes = this.testDuration.minusHours(hours).toMinutes();
            long seconds = this.testDuration.minusHours(hours).minusMinutes(minutes).getSeconds();

            //Return the duration in a text format
            return hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
        }
        else
        {
            return "Test is currently Running";
        }
    }

    public String getIssueNum()
    {
        //return the size of the issue arraylist
        return String.valueOf(this.issueElements.size());
    }

    public ArrayList<TestElement> getTestElements()
    {
        //return the test element Array List
        return this.testElements;
    }

    public ArrayList<IssueElement> getIssueElements()
    {
        return this.issueElements;
    }
}
