package TestEngine.IssueElement;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

public class IssueElement
{
    private Object issueID;
    private LocalDateTime occurringTime;
    private String errorMessage;
    private String scenario;
    private String expectedValue;
    private String actualValue;
    private String issueStatus;
    private String targetedWebPage;
    private Object issueCloser;
    private String developerMessage;
    private LocalDateTime closedTime;

    /*****************************************
     /*Method Name: IssueElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Issues
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public IssueElement(String targetedWebPage, String scenario, String expectedValue, String actualValue, String errorMessage)
    {
        //SetUp ObjectID
        this.targetedWebPage = targetedWebPage;
        this.scenario = scenario;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
        this.errorMessage = errorMessage;

        //Add issueID
        this.issueID = new ObjectId();

        //record the time it happens
        this.occurringTime = LocalDateTime.now();

        //SetIssues status to open
        this.issueStatus = "Open";

    }

    /*****************************************
     /*Method Name: IssuesElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the data pulled form the database for open Issues
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/

    public IssueElement(Object issueID, String targetedWebPage, String scenario, String expectedValue, String actualValue, String errorMessage, LocalDateTime occurringTime)
    {
        //SetUp Issue Properties
        this.issueID = issueID;
        this.occurringTime = occurringTime;
        this.errorMessage = errorMessage;
        this.scenario = scenario;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
        this.issueStatus = "Open";
        this.targetedWebPage = targetedWebPage;
    }

    public IssueElement(Object issueID, String scenario, String expectedValue, String actualValue, String errorMessage, LocalDateTime occurringTime, String issueStatus, Object issueCloser, String developerMessage, LocalDateTime closedTime)
    {
        //SetUp Issue Properties
        this.issueID = issueID;
        this.occurringTime = occurringTime;
        this.errorMessage = errorMessage;
        this.scenario = scenario;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
        this.issueStatus = issueStatus;
        this.issueCloser = issueCloser;
        this.developerMessage = developerMessage;
        this.closedTime = closedTime;
    }

    /*****************************************
     /*Method Name: IssuesElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the data pulled form the database
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public void closeIssue(Object issueCloser, String developerMessage)
    {
        //record the info of the closer
        this.issueCloser = issueCloser;
        this.developerMessage = developerMessage;

        //Record the time of closing the issue
        this.closedTime = LocalDateTime.now();

        //set Issues status to closed
        this.issueStatus = "Closed";
    }

    public Object getIssueID()
    {
        //return the issue ID
        return this.issueID;
    }

    public String getOccurringDate ()
    {
        //return the data value
        return String.valueOf(this.occurringTime).split("T")[0];
    }

    public String getClosedDateTime()
    {
        return String.valueOf(this.closedTime);
    }

    public Object getIssueCloser()
    {
        return issueCloser;
    }

    public String getDeveloperMessage()
    {
        return this.developerMessage;
    }

    public String getIssueStatus()
    {
        return issueStatus;
    }

    public String getScenario()
    {
        return this.scenario;
    }

    public String getOccurringTime()
    {
        return String.valueOf(occurringTime).split("T")[1].split("\\.")[0];
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public String getTargetedWebPage()
    {
        return targetedWebPage;
    }

    public String getClosedTime()
    {
        return String.valueOf(this.closedTime).split("T")[1].split("\\.")[0];
    }

    public String getClosedDate()
    {
        return String.valueOf(this.closedTime).split("T")[0];
    }


}
