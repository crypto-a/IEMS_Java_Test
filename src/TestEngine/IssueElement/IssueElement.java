package TestEngine.IssueElement;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.bson.Document;

public class IssueElement
{
    private Object issueID;
    private LocalDateTime occurringTime;
    private String errorMessage;
    private int scenario;
    private String expectedValue;
    private String actualValue;
    private Boolean isIssueOpen;
    private String targetedWebPage;
    private Object issueCloser;
    private String developerMessage;
    private LocalDateTime closedTime;

    private String targetedWebElement;

    /*****************************************
     /*Method Name: IssueElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the new Issues
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public IssueElement(String targetedWebPage, Object issueID, int scenario, String expectedValue, String actualValue, String errorMessage, String targetedWebElement)
    {
        //SetUp ObjectID
        this.targetedWebPage = targetedWebPage;
        this.scenario = scenario;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
        this.errorMessage = errorMessage;
        this.targetedWebElement = targetedWebElement;

        //Add issueID
        this.issueID = issueID;

        //record the time it happens
        this.occurringTime = LocalDateTime.now();

        //SetIssues status to open
        this.isIssueOpen = true;

    }

    /*****************************************
     /*Method Name: IssuesElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the data pulled form the database
     /*Method Inputs: issueElementDoc
     /*Method Outputs: None
     ******************************************/

    public IssueElement(Document issueElementDoc)
    {
        //SetUp Issue Properties
        this.issueID = issueElementDoc.get("_id");
        this.occurringTime = LocalDateTime.parse(issueElementDoc.getString("occurringTime"));
        this.errorMessage = issueElementDoc.getString("errorMessage");
        this.scenario = issueElementDoc.getInteger("scenario");
        this.expectedValue = issueElementDoc.getString("expectedValue");
        this.actualValue = issueElementDoc.getString("expectedValue");
        this.isIssueOpen = issueElementDoc.getBoolean("isIssueOpen");
        this.targetedWebPage = issueElementDoc.getString("targetedWebPage");
        this.targetedWebElement = issueElementDoc.getString("targetedWebElement");

        if (!this.isIssueOpen)
        {
            this.issueCloser = issueElementDoc.get("issueCloser");
            this.developerMessage = issueElementDoc.getString("developerMessage");
            this.closedTime = LocalDateTime.parse(issueElementDoc.getString("closedTime"));
        }
    }

    public void updateObject(Document newIssueElementDoc)
    {
        //SetUp Issue Properties
        this.issueID = newIssueElementDoc.get("_id");
        this.occurringTime = LocalDateTime.parse(newIssueElementDoc.getString("occurringTime"));
        this.errorMessage = newIssueElementDoc.getString("errorMessage");
        this.scenario = newIssueElementDoc.getInteger("scenario");
        this.expectedValue = newIssueElementDoc.getString("expectedValue");
        this.actualValue = newIssueElementDoc.getString("expectedValue");
        this.isIssueOpen = newIssueElementDoc.getBoolean("isIssueOpen");
        this.targetedWebPage = newIssueElementDoc.getString("targetedWebPage");
        this.targetedWebElement = newIssueElementDoc.getString("targetedWebElement");

        if (!this.isIssueOpen)
        {
            this.issueCloser = newIssueElementDoc.get("issueCloser");
            this.developerMessage = newIssueElementDoc.getString("developerMessage");
            this.closedTime = LocalDateTime.parse(newIssueElementDoc.getString("closedTime"));
        }
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
        this.isIssueOpen = false;
    }

    public String getIssueID()
    {
        //return the issue ID
        return String.valueOf(this.issueID);
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

    public Boolean getIsIssueOpen()
    {
        return isIssueOpen;
    }

    public int getScenario()
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

    public Document getAsDocument()
    {
        //Create the issueDoc
        Document issueDoc = new Document();
        issueDoc.append("_id", this.issueID);
        issueDoc.append("occurringTime", this.occurringTime.toString());
        issueDoc.append("errorMessage", this.errorMessage);
        issueDoc.append("scenario", this.scenario);
        issueDoc.append("expectedValue", this.expectedValue);
        issueDoc.append("actualValue", this.actualValue);
        issueDoc.append("isIssueOpen", this.isIssueOpen);
        issueDoc.append("targetedWebPage", this.targetedWebPage);
        issueDoc.append("issueCloser", this.issueCloser);
        issueDoc.append("developerMessage", this.developerMessage);
        issueDoc.append("closedTime", this.closedTime.toString());
        issueDoc.append("targetedWebElement", this.targetedWebElement);

        return issueDoc;
    }

    public LocalDateTime getDateTime()
    {
        return this.occurringTime;
    }

    public String getTargetedWebElement()
    {
        return targetedWebElement;
    }
}
