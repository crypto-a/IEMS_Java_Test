package TestManager;

import GUI.Event.Event;
import TestManager.Issue.Issue;
import TestManager.Test.Test;

import java.util.ArrayList;

public class TestManager
{
    //Object Properties
    private final Event event;
    private Test runningTest;
    private ArrayList<Issue> issuesList;
    private ArrayList<Test> testsList;


    public TestManager(Event event)
    {
        //SetUp Object Properties
        this.event = event;

        //ToDo: Load Objects from db
    }

    public void createTest(String testType)
    {
        //Request Different Data Depending on the Test Type
        switch (testType)
        {
            case "UITest":
                //Request Data From the UI


            default:
                System.out.println("testType Command not Supported");
        }
        //Create the Test Object
    }

    public void createIssue()
    {
        //ToDo
    }

    private void UserInterfaceTesting()
    {
        /* Start with the Scenario section Testing */
        String[] uiElementsList =
                {
                "line-element",
                "bus-element",
                "externalGrid-element",
                "transformer-element",
                "fuse-element",
                "evse-element",
                "pv-element",
                "reactor-element",
                "load-element"
        };



    }
}
