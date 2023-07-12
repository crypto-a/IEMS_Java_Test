package TestManager.Issue;

import TestManager.Test.Test;

import java.time.LocalDateTime;

public class Issue
{
    private Test testParent;
    private LocalDateTime localDateTimeOpened;
    private String issueStatus = "Open";
    private LocalDateTime localDateTimeClosed;
    private String issueCloser;
    private String messageReport;

    private String expectedValue;
    private String actualValue;


    public Issue(Test testParent)
    {
        //SetUp Object Properties
        this.testParent = testParent;

        //Record The time of Issue Opening
        this.localDateTimeOpened = LocalDateTime.now();
    }

    public Issue (Test testParent, LocalDateTime localDateTimeOpened)
    {
        //SetUp Object Properties
        this.testParent = testParent;
        this.localDateTimeOpened = localDateTimeOpened;

    }

    public Issue(Test testParent, LocalDateTime localDateTimeOpened, LocalDateTime localDateTimeClosed, String issueCloser, String messageReport)
    {

    }

    public void closeIssue (String issueCloser, String messageReport)
    {
        //Set Issue to closed
        this.issueStatus = "Closed";

        //Record the person who closed the issue
        this.issueCloser = issueCloser;

        //Save the message they have sent for the issue
        this.messageReport = messageReport;

        //Record Time of closing
        this.localDateTimeClosed = LocalDateTime.now();
    }
}
