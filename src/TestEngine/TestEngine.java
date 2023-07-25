package TestEngine;

import Database.Database;
import GUI.Event.Event;
import TestEngine.Issue.Issue;
import TestEngine.IssueElement.IssueElement;
import TestEngine.Test.Test;
import TestEngine.TestElement.TestElement;
import TestEngine.TestObject.TestObject;
import User.User;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEngine
{
    //Object Properties
    private final Event event;
    private final User user;

    private final Database database;
    private Test runningTest;
    private ArrayList<IssueElement> openIssuesList = new ArrayList<IssueElement>();
    private ArrayList<TestObject> testsList = new ArrayList<TestObject>();


    public TestEngine(Event event, User user, Database database)
    {
        //SetUp Object Properties
        this.event = event;
        this.user = user;
        this.database = database;
    }

    public void createTest(String targetedWebPage, String webAppUrl, String[] webPageLoginInfo)
    {

        TestObject testObject = new TestObject(this.user.getUserID(), targetedWebPage, webAppUrl, webPageLoginInfo);

        //Add the ingoing test to the array
        this.testsList.add(testObject);

        //Push it to the database
        this.database.pushNewTestObject(testObject);

    }

    public ArrayList<TestObject> getTestArrayList()
    {
        //return the arrayList
        return this.testsList;
    }

    public ArrayList<IssueElement> getIssueArrayList()
    {
        return this.openIssuesList;
    }

    public TestObject getTestObject(String testID)
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


    public void loadData()
    {
        /* Load the 20 most recent Tests */
        FindIterable<Document> testObjects = this.database.requestTestHistory();

        //Loop through all the objects
        for (Document testObject : testObjects)
        {
            //Create the array list for the tests
            ArrayList<TestElement> testElementList = new ArrayList<TestElement>();

            //Loop through all testElements
            for (Object testElementID : (List<Object>) testObject.get("testElements"))
            {
                //Request the document element
                Document testElement = this.database.getTestElement(testElementID);

                if (testElement == null)
                {
                    //Break out of loop
                    break;
                }

                //Create the issue object and add it to the arraylist
                testElementList.add(new TestElement(
                        testElementID,
                        testElement.getString("status"),
                        testElement.getString("testElementIdentification"),
                        testElement.getString("scenario"),
                        this.arrayListToTwoDArrayString((ArrayList<ArrayList<String>>) testElement.get("actualValue")),
                        this.arrayListToTwoDArrayString((ArrayList<ArrayList<String>>) testElement.get("expectedValue")),
                        this.arrayListToTwoDArrayString((ArrayList<ArrayList<String>>) testElement.get("testResults")),
                        testElement.getString("testLog"),
                        LocalDateTime.parse(testElement.getString("startTime")),
                        LocalDateTime.parse(testElement.getString("endTime")),
                        Integer.parseInt(testElement.getString("numberOfErrors"))
                        ));
            }

            //Create the arrayList for the issues
            ArrayList<IssueElement> issueElementsList = new ArrayList<IssueElement>();

            for (Object issueElementID: (List<Object>) testObject.get("issueElements"))
            {
                //Pull and add the elements to the list
                Document issueElement = this.database.getIssueElement(issueElementID);

                if (issueElement == null)
                {
                    //Break out of loop
                    break;
                }

                //Create the issue object and add it to the arraylist
                issueElementsList.add(new IssueElement(
                        issueElementID,
                        issueElement.getString("scenario"),
                        issueElement.getString("expectedValue"),
                        issueElement.getString("actualValue"),
                        issueElement.getString("errorMessage"),
                        LocalDateTime.parse(issueElement.getString("occurringTime")),
                        issueElement.getString("issueStatus"),
                        issueElement.get("issueCloser"),
                        issueElement.getString("developerMessage"),
                        LocalDateTime.parse(issueElement.getString("closedTime"))
                        ));
            }

            //Create the TestObject and Add it to the list
            this.testsList.add(new TestObject(
                    testObject.get("_id"),
                    LocalDateTime.parse(testObject.getString("startTestTime")),
                    LocalDateTime.parse((testObject.getString("endTestTime"))),
                    testObject.get("teastIssuer"),
                    testObject.getString("targetedWebPage"),
                    testObject.getString("webPageURL"),
                    testElementList,
                    issueElementsList,
                    (ArrayList<String>) testObject.get("testLogs"),
                    Integer.parseInt(testObject.getString("numberOfTests"))
            ));


        }

        /* Load the open Issues in the issue tab */
        FindIterable<Document> openIssueObjects = this.database.requestOpenIssues();

        //Loop through all the objects
        for (Document issueObject : openIssueObjects)
        {
            //Create the object and add it to the openIssuesList
            this.openIssuesList.add(new IssueElement(
                    issueObject.get("_id"),
                    issueObject.getString("targetedWebPage"),
                    issueObject.getString("scenario"),
                    issueObject.getString("expectedValue"),
                    issueObject.getString("actualValue"),
                    issueObject.getString("errorMessage"),
                    LocalDateTime.parse(issueObject.getString("occouringTime"))
            ));
        }

    }

    private String[][] arrayListToTwoDArrayString(ArrayList<ArrayList<String>> arrayList)
    {
        String[][] actualValue = new String[arrayList.size()][];
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<String> innerList = arrayList.get(i);
            actualValue[i] = innerList.toArray(new String[0]);
        }

        return actualValue;
    }

    private Boolean[][] arrayListToTwoDArrayBoolean(ArrayList<ArrayList<Boolean>> arrayList)
    {
        Boolean[][] actualValue = new Boolean[arrayList.size()][];
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Boolean> innerList = arrayList.get(i);
            actualValue[i] = innerList.toArray(new Boolean[0]);
        }

        return actualValue;
    }

    public String getUserName(Object userID)
    {
        //Collect iot from the database and return it
        return this.database.getUserFullName(userID);
    }

    public void resetIssues()
    {
        //Clear it form the database
        this.openIssuesList.clear();

        //Add the new ones to the array List
        /* Load the open Issues in the issue tab */
        FindIterable<Document> openIssueObjects = this.database.requestOpenIssues();

        //Loop through all the objects
        for (Document issueObject : openIssueObjects)
        {
            //Create the object and add it to the openIssuesList
            this.openIssuesList.add(new IssueElement(
                    issueObject.get("_id"),
                    issueObject.getString("targetedWebPage"),
                    issueObject.getString("scenario"),
                    issueObject.getString("expectedValue"),
                    issueObject.getString("actualValue"),
                    issueObject.getString("errorMessage"),
                    LocalDateTime.parse(issueObject.getString("occouringTime"))
            ));
        }
    }

}