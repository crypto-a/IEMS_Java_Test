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
    ArrayList<TestObject> testObjectArrayList = new ArrayList<>();
    ArrayList<TestElement> testElementArrayList = new ArrayList<>();
    ArrayList<IssueElement> issueElementArrayList = new ArrayList<>();

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
        for (TestObject testObject : this.testObjectArrayList)
        {
            //If it is the one we are looking for
            if (testObject.getTestID().equals(objectIDString))
            {
                //Return the object
                return testObject;
            }
        }

        return null;
    }

    public TestElement getTestElement(String objectIDString)
    {
        //loop though every element of the array
        for (TestElement testElement : this.testElementArrayList)
        {
            //If it is the one we are looking for
            if (testElement.getTestID().equals(objectIDString))
            {
                //Return the object
                return testElement;
            }
        }

        return null;
    }

    public IssueElement getIssueElement(String objectIDString)
    {
        //loop though every element of the array
        for (IssueElement issueElement : this.issueElementArrayList)
        {
            //If it is the one we are looking for
            if (issueElement.getIssueID().equals(objectIDString))
            {
                //Return the object
                return issueElement;
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

        //Reset the sort button
        this.event.setMainPageTestObjectSortComboBoxSelect(0);
    }

    public void pushDisplayObjectsToEvent()
    {
        /* Push the TestObjects to the event object */
        this.event.setTestObjectDisplayArrayList(this.testObjectArrayList);

        //Add it to the event object
        this.event.setOpenIssueElementDisplayArrayList(this.getOpenIssues());
    }

    public void pushDisplayObjectsToEventForOldTestDisplay(TestObject testObject)
    {
        //create the arraylists that will be pushed
        ArrayList<TestElement> testElementArrayListToPush = new ArrayList<>();
        ArrayList<IssueElement> issueElementArrayListToPush = new ArrayList<>();

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
            //Check if it is contained in the object's issue arraylist
            if (testObject.getIssueElements().contains(new ObjectId(issueElement.getIssueID())))
            {
                //Add the object to the array list
                issueElementArrayListToPush.add(issueElement);
            }
        }

        //Push the to push array list to the event object to get displayed
        this.event.setIssueElementDisplayArrayList(issueElementArrayListToPush);


    }

    public void sortTestObjectsByNewToOld()
    {
        //Call the merge sort function
        this.testObjectsMergeSortAscending(this.testObjectArrayList);

        //Push the new arrayList to event
        this.pushTestObjectArrayListToEvent();
    }


    private void testObjectsMergeSortAscending(ArrayList<TestObject> inputArrayList)
    {
        //Get the length of the input Array
        int inputLength = inputArrayList.size();

        //check if the input length is less than two then it breaks
        if (inputLength < 2)
        {
            //break out of the method
            return;
        }

        //get the mid index
        int midIndex = inputLength / 2;

        //Create the left and right arrays
        ArrayList<TestObject> leftHalf = new ArrayList<>();
        ArrayList<TestObject> rightHalf = new ArrayList<>();

        //Populate the leftHalf array
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(inputArrayList.get(i));
        }

        //Populate the right half array
        for (int i = midIndex; i < inputLength; i++)
        {
            rightHalf.add(inputArrayList.get(i));
        }

        //Call the merge sort to add recursion
        testObjectsMergeSortAscending(leftHalf);
        testObjectsMergeSortAscending(rightHalf);

        //Merge the two arrayLists

        //Merge the two methods
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        //Create the merge indexes
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize)
        {

            if (leftHalf.get(i).getTestDateTime().isAfter(rightHalf.get(j).getTestDateTime()))
            {
                //Add the i index of the left half to the input array
                inputArrayList.set(k, leftHalf.get(i));

                //incriment i
                i++;
            }
            else
            {
                //Add the j index of the left half to the input array
                inputArrayList.set(k, rightHalf.get(j));

                //incriment j
                j++;
            }

            k++;
        }

        while (i < leftSize)
        {
            //Add the i index of the left half to the input array
            inputArrayList.set(k, leftHalf.get(i));

            //incriment i and k
            i++;
            k++;
        }

        while (j < rightSize)
        {
            //Add the j index of the left half to the input array
            inputArrayList.set(k, rightHalf.get(j));

            //incriment j and k
            j++;
            k++;
        }
    }
    public void sortTestObjectsByOldToNew()
    {
        //Call the merge sort descending method
        this.testObjectsMergeSortDescending(this.testObjectArrayList);

        //Push the new arrayList to event
        this.pushTestObjectArrayListToEvent();
    }

    private void testObjectsMergeSortDescending(ArrayList<TestObject> inputArrayList)
    {
        //Get the length of the input Array
        int inputLength = inputArrayList.size();

        //check if the input length is less than two then it breaks
        if (inputLength < 2)
        {
            //break out of the method
            return;
        }

        //get the mid index
        int midIndex = inputLength / 2;

        //Create the left and right arrays
        ArrayList<TestObject> leftHalf = new ArrayList<>();
        ArrayList<TestObject> rightHalf = new ArrayList<>();

        //Populate the leftHalf array
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(inputArrayList.get(i));
        }

        //Populate the right half array
        for (int i = midIndex; i < inputLength; i++)
        {
            rightHalf.add(inputArrayList.get(i));
        }

        //Call the merge sort to add recursion
        testObjectsMergeSortDescending(leftHalf);
        testObjectsMergeSortDescending(rightHalf);

        //Merge the two arrayLists

        //Merge the two methods
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        //Create the merge indexes
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize)
        {

            if (rightHalf.get(j).getTestDateTime().isAfter(leftHalf.get(i).getTestDateTime()))
            {
                //Add the i index of the left half to the input array
                inputArrayList.set(k, leftHalf.get(i));

                //incriment i
                i++;
            }
            else
            {
                //Add the j index of the left half to the input array
                inputArrayList.set(k, rightHalf.get(j));

                //incriment j
                j++;
            }

            k++;
        }

        while (i < leftSize)
        {
            //Add the i index of the left half to the input array
            inputArrayList.set(k, leftHalf.get(i));

            //incriment i and k
            i++;
            k++;
        }

        while (j < rightSize)
        {
            //Add the j index of the left half to the input array
            inputArrayList.set(k, rightHalf.get(j));

            //incriment j and k
            j++;
            k++;
        }
    }

    public void pushTestObjectArrayListToEvent()
    {
        //Change the value in the event object
        this.event.setTestObjectDisplayArrayList(this.testObjectArrayList);
    }

    public void sortOpenIssueObjectsByNewToOld()
    {
        //Collect the open Issues
        ArrayList<IssueElement> openIssues = this.getOpenIssues();

        //Sort them
        this.openIssuesMergeSortAscending(openIssues);

        //push them to the event object
        this.event.setOpenIssueElementDisplayArrayList(openIssues);
    }

    public void sortOpenIssueObjectsByOldToNew()
    {
        //Collect the open Issues
        ArrayList<IssueElement> openIssues = this.getOpenIssues();

        //Sort them
        this.openIssuesMergeSortDescending(openIssues);

        //push them to the event object
        this.event.setOpenIssueElementDisplayArrayList(openIssues);
    }

    private ArrayList<IssueElement> getOpenIssues()
    {
        ArrayList<IssueElement> openIssuesArrayList = new ArrayList<>();

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

        return openIssuesArrayList;
    }

    private void openIssuesMergeSortAscending(ArrayList<IssueElement> inputArrayList)
    {
        //Get the length of the input Array
        int inputLength = inputArrayList.size();

        //check if the input length is less than two then it breaks
        if (inputLength < 2)
        {
            //break out of the method
            return;
        }

        //get the mid index
        int midIndex = inputLength / 2;

        //Create the left and right arrays
        ArrayList<IssueElement> leftHalf = new ArrayList<>();
        ArrayList<IssueElement> rightHalf = new ArrayList<>();

        //Populate the leftHalf array
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(inputArrayList.get(i));
        }

        //Populate the right half array
        for (int i = midIndex; i < inputLength; i++)
        {
            rightHalf.add(inputArrayList.get(i));
        }

        //Call the merge sort to add recursion
        openIssuesMergeSortAscending(leftHalf);
        openIssuesMergeSortAscending(rightHalf);

        //Merge the two arrayLists

        //Merge the two methods
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        //Create the merge indexes
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize)
        {

            if (leftHalf.get(i).getDateTime().isAfter(rightHalf.get(j).getDateTime()))
            {
                //Add the i index of the left half to the input array
                inputArrayList.set(k, leftHalf.get(i));

                //incriment i
                i++;
            }
            else
            {
                //Add the j index of the left half to the input array
                inputArrayList.set(k, rightHalf.get(j));

                //incriment j
                j++;
            }

            k++;
        }

        while (i < leftSize)
        {
            //Add the i index of the left half to the input array
            inputArrayList.set(k, leftHalf.get(i));

            //incriment i and k
            i++;
            k++;
        }

        while (j < rightSize)
        {
            //Add the j index of the left half to the input array
            inputArrayList.set(k, rightHalf.get(j));

            //incriment j and k
            j++;
            k++;
        }
    }

    private void openIssuesMergeSortDescending(ArrayList<IssueElement> inputArrayList)
    {
        //Get the length of the input Array
        int inputLength = inputArrayList.size();

        //check if the input length is less than two then it breaks
        if (inputLength < 2)
        {
            //break out of the method
            return;
        }

        //get the mid index
        int midIndex = inputLength / 2;

        //Create the left and right arrays
        ArrayList<IssueElement> leftHalf = new ArrayList<>();
        ArrayList<IssueElement> rightHalf = new ArrayList<>();

        //Populate the leftHalf array
        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(inputArrayList.get(i));
        }

        //Populate the right half array
        for (int i = midIndex; i < inputLength; i++)
        {
            rightHalf.add(inputArrayList.get(i));
        }

        //Call the merge sort to add recursion
        openIssuesMergeSortDescending(leftHalf);
        openIssuesMergeSortDescending(rightHalf);

        //Merge the two arrayLists

        //Merge the two methods
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        //Create the merge indexes
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize)
        {

            if (rightHalf.get(j).getDateTime().isAfter(leftHalf.get(i).getDateTime()))
            {
                //Add the i index of the left half to the input array
                inputArrayList.set(k, leftHalf.get(i));

                //incriment i
                i++;
            }
            else
            {
                //Add the j index of the left half to the input array
                inputArrayList.set(k, rightHalf.get(j));

                //incriment j
                j++;
            }

            k++;
        }

        while (i < leftSize)
        {
            //Add the i index of the left half to the input array
            inputArrayList.set(k, leftHalf.get(i));

            //incriment i and k
            i++;
            k++;
        }

        while (j < rightSize)
        {
            //Add the j index of the left half to the input array
            inputArrayList.set(k, rightHalf.get(j));

            //incriment j and k
            j++;
            k++;
        }
    }
}
