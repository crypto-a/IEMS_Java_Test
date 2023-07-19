package TestEngine.IssueElement;

import java.time.LocalDateTime;

public class IssueElement
{
    private int testID;
    private LocalDateTime occurringTime;
    private String errorMessage;
    private String scenario;
    private String expectedValue;
    private String actualValue;
    private String issueStatus;
    private String issueCloser;
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
    public IssueElement(int testID, String scenario, String expectedValue, String actualValue, String errorMessage)
    {
        //SetUp object Properties
        this.testID = testID;
        this.scenario = scenario;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
        this.errorMessage = errorMessage;

        //record the time it happens
        this.occurringTime = LocalDateTime.now();

        //SetIssues status to open
        this.issueStatus = "Open";

    }

    /*****************************************
     /*Method Name: IssuesElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the data pulled form the database
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/

    public IssueElement(int testID, String scenario, String expectedValue, String actualValue, String errorMessage, LocalDateTime occurringTime, String issueStatus, String issueCloser, String developerMessage, LocalDateTime closedTime)
    {
        //SetUp Issue Properties
        this.testID = testID;
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
    public void closeIssue(String issueCloser, String developerMessage)
    {
        //record the info of the closer
        this.issueCloser = issueCloser;
        this.developerMessage = developerMessage;

        //Record the time of closing the issue
        this.closedTime = LocalDateTime.now();

        //set Issues status to closed
        this.issueStatus = "Closed";
    }
}
