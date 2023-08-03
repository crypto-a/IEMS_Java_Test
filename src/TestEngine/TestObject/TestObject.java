package TestEngine.TestObject;

import TestAutomations.DLCDemo.DLCDemo;
import TestEngine.TestElement.TestElement;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestObject
{
    public Object testID;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private Duration testDuration;
    private Object issuer;
    private String targetedWebPage;
    private String webPageURL;
    private ArrayList<Object> testElements = new ArrayList<Object>();
    private ArrayList<Object> issueElements = new ArrayList<Object>();
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
                Thread thread = new Thread((Runnable) new DLCDemo());//ToDo

                //Start the thread
                thread.start();

                break;

            case "DERMS":
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
    public TestObject(Document testObjectDoc)
    {
        //SetUp Object properties
        this.testID = testObjectDoc.get("_id");
        this.testStartTime = LocalDateTime.parse(testObjectDoc.getString("startTestTime"));
        this.testEndTime = LocalDateTime.parse(testObjectDoc.getString("endTestTime"));
        this.issuer = testObjectDoc.get("testIssuer");
        this.targetedWebPage = testObjectDoc.getString("targetedWebPage");
        this.webPageURL = testObjectDoc.getString("webPageURL");
        this.testElements = (ArrayList<Object>) testObjectDoc.get("testElements");
        this.issueElements = (ArrayList<Object>) testObjectDoc.get("issueElements");
        this.testLogs = (ArrayList<String>) testObjectDoc.get("testLogs");
        this.numberOfTests = testObjectDoc.getInteger("numberOfTests");

        //Calculate Duration
        this.testDuration = Duration.between(this.testStartTime, this.testEndTime);
    }

    public void updateObject(Document newTestObjectDoc)
    {
        //SetUp Object properties
        this.testID = newTestObjectDoc.get("_id");
        this.testStartTime = LocalDateTime.parse(newTestObjectDoc.getString("startTestTime"));
        this.testEndTime = LocalDateTime.parse(newTestObjectDoc.getString("endTestTime"));
        this.issuer = newTestObjectDoc.get("testIssuer");
        this.targetedWebPage = newTestObjectDoc.getString("targetedWebPage");
        this.webPageURL = newTestObjectDoc.getString("webPageURL");
        this.testElements = (ArrayList<Object>) newTestObjectDoc.get("testElements");
        this.issueElements = (ArrayList<Object>) newTestObjectDoc.get("issueElements");
        this.testLogs = (ArrayList<String>) newTestObjectDoc.get("testLogs");
        this.numberOfTests = newTestObjectDoc.getInteger("numberOfTests");

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

    public ArrayList<Object> getTestElements()
    {
        //return the test element Array List
        return this.testElements;
    }

    public ArrayList<Object> getIssueElements()
    {
        return this.issueElements;
    }


}
