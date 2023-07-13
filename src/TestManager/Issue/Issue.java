package TestManager.Issue;

import TestManager.subTest.subTest;

import java.time.LocalDateTime;

public class Issue
{
    private subTest testParent;
    private LocalDateTime localDateTimeOpened;
    private String issueStatus = "Open";
    private LocalDateTime localDateTimeClosed;
    private String issueCloser;
    private String messageReport;

    private String expectedValue;
    private String actualValue;


    public Issue(subTest testParent, String expectedValue, String actualValue)
    {
        //SetUp Object Properties
        this.testParent = testParent;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;

        //Record The time of Issue Opening
        this.localDateTimeOpened = LocalDateTime.now();
    }

    public Issue (subTest testParent, LocalDateTime localDateTimeOpened)
    {
        //SetUp Object Properties
        this.testParent = testParent;
        this.localDateTimeOpened = localDateTimeOpened;

    }

    public Issue(subTest testParent, LocalDateTime localDateTimeOpened, LocalDateTime localDateTimeClosed, String issueCloser, String messageReport)
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
