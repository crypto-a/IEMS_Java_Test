package TestManager;

import GUI.Event.Event;
import TestManager.Issue.Issue;

import java.util.ArrayList;

public class TestManager
{
    private final Event event;

    private ArrayList<Issue> issuesList;
    public TestManager(Event event)
    {
        //SetUp Object Properties
        this.event = event;
    }

    public void createIssue()
    {
        //ToDo
    }
}
