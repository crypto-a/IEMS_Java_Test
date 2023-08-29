package TestEngine.TestObject;

import GUI.Event.Event;
import TestAutomations.DERMS.DERMS;
import TestAutomations.DLCDemo.DLCDemo;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
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
    private ArrayList<Object> testElements = new ArrayList<>();
    private ArrayList<Object> issueElements = new ArrayList<>();
    private ArrayList<String> testLogs = new ArrayList<>();
    private final ArrayList<Duration> backEndResponseTimes = new ArrayList<>();

    private int numberOfTests;
    private int estimatedTime;
    private Duration averageBackEndResponseTime;
    private String[] webPageLoginInfo;

    private String testDescription;

    private Event event;
    private TestEngine testEngine;



    /*****************************************
     /*Method Name: TestObject
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Tests
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public TestObject(TestEngine testEngine, Event event, Object issuer, String targetedWebPage, String webPageURL, String testDescription, String[] webPageLoginInfo )
    {
        //SetUp object Properties
        this.testEngine = testEngine;
        this.issuer = issuer;
        this.targetedWebPage = targetedWebPage;
        this.webPageURL = webPageURL;
        this.webPageLoginInfo = webPageLoginInfo;
        this.testDescription = testDescription;

        this.event = event;

        //Record the time of starting a test
        this.testStartTime = LocalDateTime.now();

        //Set up object ID
        this.testID = new ObjectId();

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
        this.issuer = testObjectDoc.get("testIssuer");
        this.targetedWebPage = testObjectDoc.getString("targetedWebPage");
        this.webPageURL = testObjectDoc.getString("webPageURL");
        this.testElements = (ArrayList<Object>) testObjectDoc.get("testElements");
        this.issueElements = (ArrayList<Object>) testObjectDoc.get("issueElements");
        this.testLogs = (ArrayList<String>) testObjectDoc.get("testLogs");
        this.numberOfTests = testObjectDoc.getInteger("numberOfTests");
        this.testDescription = testObjectDoc.getString("testDescription");
        this.webPageLoginInfo = new String[]{testObjectDoc.getString("websiteUsername"), testObjectDoc.getString("websitePassword")};

        try
        {
            this.testEndTime = LocalDateTime.parse(testObjectDoc.getString("endTestTime"));

            //Calculate Duration
            this.testDuration = Duration.between(this.testStartTime, this.testEndTime);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


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
        this.testDescription = newTestObjectDoc.getString("testDescription");
        this.webPageLoginInfo = new String[]{newTestObjectDoc.getString("websiteUsername"), newTestObjectDoc.getString("websitePassword")};

        //Calculate Duration
        this.testDuration = Duration.between(this.testStartTime, this.testEndTime);
    }

    /*****************************************
     /*Method Name: testScenario
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is in charge of testing a scenario with standardized data;
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public void testScenario(String testElementIdentification, int scenario, String[][] actualValue, String[][] expectedValue)
    {
        //Create the new Test Element Object and Add it to the arrayList
        this.testElements.add(new TestElement(this, new ObjectId(), testElementIdentification, scenario, actualValue, expectedValue));

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

    public String getTestStartTime()
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
            return hours + " hrs, " + minutes + " mins and " + seconds + " secs";
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


    public String getTestEndTime()
    {
        if (this.testEndTime == null)
        {
            return null;
        }

        //Return the time
        return this.testEndTime.toString().split("T")[1].split("\\.")[0];
    }

    public String getTestDuration()
    {
        return this.testDuration.toString();
    }

    public LocalDateTime getTestDateTime()
    {
        //Return the start time
        return testStartTime;
    }

    public void setTestID(Object testID)
    {
        this.testID = testID;
    }

    public String getTestDescription()
    {
        //return the test discription
        return this.testDescription;
    }

    public Boolean containsElement(String elementID)
    {
        /* Look through the test elements and Issue elements arraylist */
        return this.testElements.contains(new ObjectId(elementID)) || this.issueElements.contains(new ObjectId(elementID));
    }

    public String[] getWebPageLoginInfo()
    {
        return webPageLoginInfo;
    }

    public void postTestCalculations()
    {
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

        //requestUpdate
        this.event.setIsTestRunning(false);
        this.event.requestPageRefresh();

        //update DB
        this.event.closeTestObject(this.getAsDocument(), this.getTestID());
    }

    public void addNewTestElement(String testID)
    {
        this.testElements.add(new ObjectId(testID));
    }

    public Document getAsDocument()
    {
        Document testObjectDoc;

        try
        {
            testObjectDoc = new Document().append("_id", this.testID)
                    .append("startTestTime", this.testStartTime.toString())
                    .append("endTestTime", this.testEndTime.toString())
                    .append("testIssuer", this.issuer)
                    .append("targetedWebPage", this.targetedWebPage)
                    .append("webPageURL", this.webPageURL)
                    .append("testElements", this.testElements)
                    .append("issueElements", this.issueElements)
                    .append("testLogs", this.testLogs)
                    .append("numberOfTests", this.numberOfTests)
                    .append("testDescription", this.testDescription)
                    .append("websiteUsername", this.webPageLoginInfo[0])
                    .append("websitePassword", this.webPageLoginInfo[1]);

        }
        catch (Exception e)
        {
            testObjectDoc = new Document().append("_id", this.testID)
                    .append("startTestTime", this.testStartTime.toString())
                    .append("endTestTime", null)
                    .append("testIssuer", this.issuer)
                    .append("targetedWebPage", this.targetedWebPage)
                    .append("webPageURL", this.webPageURL)
                    .append("testElements", this.testElements)
                    .append("issueElements", this.issueElements)
                    .append("testLogs", this.testLogs)
                    .append("numberOfTests", this.numberOfTests)
                    .append("testDescription", this.testDescription)
                    .append("websiteUsername", this.webPageLoginInfo[0])
                    .append("websitePassword", this.webPageLoginInfo[1]);
        }


        return testObjectDoc;
    }

    public void createNewIssue(String testElementIdentification, int scenario, String expectedValue, String actualValue, String errorMessage)
    {
        //make a new issue id
        Object issueID = new ObjectId();

        //add it to the arrayList
        this.issueElements.add(issueID);

        //push the issue to the test engine
        this.testEngine.createNewIssue(this.targetedWebPage, testElementIdentification, issueID, scenario, expectedValue, actualValue, errorMessage);


    }
}
