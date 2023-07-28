package TestEngine;

import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestObject.TestObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class TestEngine
{
    ArrayList<TestObject> testObjectArrayList = new ArrayList<TestObject>();
    ArrayList<TestElement> testElementArrayList = new ArrayList<TestElement>();
    ArrayList<IssueElement> issueElementArrayList = new ArrayList<IssueElement>();

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
}
