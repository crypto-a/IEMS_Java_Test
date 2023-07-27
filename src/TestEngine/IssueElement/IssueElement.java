package TestEngine.IssueElement;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.bson.Document;

public class IssueElement
{
    private Object issueID;
    private LocalDateTime occurringTime;
    private String errorMessage;
    private Document scenario;
    private String expectedValue;
    private String actualValue;
    private Boolean isIssueOpen;
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
    public IssueElement(String targetedWebPage, Document scenario, String expectedValue, String actualValue, String errorMessage)
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
        this.isIssueOpen = true;

    }

    /*****************************************
     /*Method Name: IssuesElement
     /*Programmer Name: Ali Rahbar
     /*Method Date: July 19, 2023
     /*Method Description: This method is the constructor for the data pulled form the database
     /*Method Inputs: issueDoc
     /*Method Outputs: None
     ******************************************/

    public IssueElement(Document issueDoc)
    {
        //SetUp Issue Properties
        this.issueID = issueDoc.get("_id");
        this.occurringTime = LocalDateTime.parse(issueDoc.getString("occurringTime"));
        this.errorMessage = issueDoc.getString("errorMessage");
        this.scenario = (Document) issueDoc.get("scenario");
        this.expectedValue = issueDoc.getString("expectedValue");
        this.actualValue = issueDoc.getString("expectedValue");
        this.isIssueOpen = issueDoc.getBoolean("isIssueOpen");
        this.targetedWebPage = issueDoc.getString("targetedWebPage");

        if (!this.isIssueOpen)
        {
            this.issueCloser = issueDoc.get("issueCloser");
            this.developerMessage = issueDoc.getString("developerMessage");
            this.closedTime = LocalDateTime.parse(issueDoc.getString("closedTime"));
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

    public Boolean getIsIssueOpen()
    {
        return isIssueOpen;
    }

    public Document getScenario()
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

        return issueDoc;
    }
}
