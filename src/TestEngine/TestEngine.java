package TestEngine;

import GUI.Event.Event;
import TestEngine.Issue.Issue;
import TestEngine.Test.Test;

import java.util.ArrayList;

public class TestEngine
{
    //Object Properties
    private final Event event;
    private Test runningTest;
    private ArrayList<Issue> issuesList;
    private ArrayList<Test> testsList = new ArrayList<Test>();


    public TestEngine(Event event)
    {
        //SetUp Object Properties
        this.event = event;

        //ToDo: Load Objects from db
    }

    public void createTest(Boolean[] testType, String webAppUrl)
    {

        //Add the ingoing test to the array
        this.testsList.add(new Test(webAppUrl, testType));

    }

    public ArrayList<Test> getTestArrayList()
    {
        //return the arrayList
        return this.testsList;
    }

    public Test getTestObject(String testID)
    {
        //loop trough every element of the tests list
        for (int i = 0; i <this.testsList.size(); i++)
        {
            if (this.testsList.get(i).getTestID().equals(testID))
            {
                return this.testsList.get(i);
            }
        }

        return null;
    }

    public void createIssue()//ToDo
    {
        //ToDo
    }









}
