package TestEngine;

import GUI.Event.Event;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestObject.TestObject;
import User.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class TestEngine
{
    private final Event event;
    ArrayList<TestObject> testObjectArrayList = new ArrayList<TestObject>();
    ArrayList<TestElement> testElementArrayList = new ArrayList<TestElement>();
    ArrayList<IssueElement> issueElementArrayList = new ArrayList<IssueElement>();

    public TestEngine(Event event)
    {
        //Set up object properties
        this.event = event;
    }

    public void addIssueElement(Document issueDoc)
    {
        //Add the issue doc to the arrayList
        this.issueElementArrayList.add(new IssueElement(issueDoc));
    }

    public void addTestElement(Document testDoc)
    {
        //add the testDoc to the arrayList
        this.testElementArrayList.add(new TestElement(testDoc));
    }

    public void addTestObject(Document testObjectDoc)
    {
        //add the testObjectODc to the testObjectArrayList
        this.testObjectArrayList.add(new TestObject(testObjectDoc));
    }

    public TestObject getTestObject(String objectIDString)
    {
        //loop though every element of the array
        for(int i = 0; i < this.testObjectArrayList.size(); i++)
        {
            //If it is the one we are looking for
            if (this.testObjectArrayList.get(i).getTestID().equals(objectIDString))
            {
                //Return the object
                return this.testObjectArrayList.get(i);
            }
        }

        return null;
    }

    public TestElement getTestElement(String objectIDString)
    {
        //loop though every element of the array
        for(int i = 0; i < this.testElementArrayList.size(); i++)
        {
            //If it is the one we are looking for
            if (this.testElementArrayList.get(i).getTestID().equals(objectIDString))
            {
                //Return the object
                return this.testElementArrayList.get(i);
            }
        }

        return null;
    }

    public IssueElement getIssueElement(String objectIDString)
    {
        //loop though every element of the array
        for(int i = 0; i < this.issueElementArrayList.size(); i++)
        {
            //If it is the one we are looking for
            if (this.issueElementArrayList.get(i).getIssueID().equals(objectIDString))
            {
                //Return the object
                return this.issueElementArrayList.get(i);
            }
        }

        return null;
    }

    public void clearData()
    {
        //Clear all the arrayLists
        this.testObjectArrayList.clear();
        this.testElementArrayList.clear();
        this.issueElementArrayList.clear();
    }

    public ArrayList<TestObject> getTestObjectArrayList()
    {
        return this.testObjectArrayList;
    }

    public void createNewTestObject(User user, String targetedWebPage, String webPageURL, String[] webPageLoginInfo)
    {
        //Create the new Test Object and add it to the test arraylist and the ongoing test
        this.testObjectArrayList.add(new TestObject(user, targetedWebPage, webPageURL, webPageLoginInfo));
    }

    public void pushDisplayObjectsToEvent()
    {
        /* Push the TestObjects to the event object */
        this.event.setTestObjectDisplayArrayList(this.testObjectArrayList);

        ArrayList<IssueElement> openIssuesArrayList = new ArrayList<IssueElement>();

        //Loop through all issues
        for (IssueElement issueElement : this.issueElementArrayList)
        {
            //If the issue is open
            if (issueElement.getIsIssueOpen())
            {
                //Add the object to the arrayList
                openIssuesArrayList.add(issueElement);
            }
        }

        //Add it to the event object
        this.event.setOpenIssueElementDisplayArrayList(openIssuesArrayList);
    }

    public void pushDisplayObjectsToEventForOldTestDisplay(TestObject testObject)
    {
        //create the arraylists that will be pushed
        ArrayList<TestElement> testElementArrayListToPush = new ArrayList<TestElement>();
        ArrayList<IssueElement> issueElementArrayListToPush = new ArrayList<IssueElement>();

        //Loop trough every test element
        for (TestElement testElement: this.testElementArrayList)
        {
            //If the id is in the arrayList
            if (testObject.getTestElements().contains(new ObjectId(testElement.getTestID())))
            {
                //Add the object to the to push array List
                testElementArrayListToPush.add(testElement);
            }
        }

        //Push it to event
        this.event.setTestElementDisplayArrayList(testElementArrayListToPush);

        //loop through every issue element
        for (IssueElement issueElement: this.issueElementArrayList)
        {
            //Check if it is contained in the obvjects issue arraylost
            if (testObject.getIssueElements().contains(new ObjectId(issueElement.getIssueID())))
            {
                //Add the object to the array list
                issueElementArrayListToPush.add(issueElement);
            }
        }

        //Push the to push array list to the event object to get displayed
        this.event.setIssueElementDisplayArrayList(issueElementArrayListToPush);


    }




}
