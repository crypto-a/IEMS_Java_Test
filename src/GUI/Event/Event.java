package GUI.Event;

import Database.Database;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;

import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;

public class Event
{
    private int codeState;
    private int previousCodeState;
    private Database database;
    private Object[] userAuthFields;
    private final Boolean[] formButtonsClicked;
    private ArrayList<TestObject> testObjectDisplayArrayList;
    private ArrayList<TestElement> testElementDisplayArrayList;
    private ArrayList<IssueElement> issueElementDisplayArrayList;
    private ArrayList<IssueElement> openIssueElementDisplayArrayList;
    private String[] userInputs;
    private TestElement selectedTestElement;
    private IssueElement selectedIssueElement;
    private TestObject selectedTestObject;
    private Object[] dataUpdateInfo;
    private Boolean isDataUpdated;

    private int testElementShowCode;
    private int issueElementShowCode;

    private int oldTestPageTestComponentComboBoxSelected;
    private int oldTestPageIssuesComboBoxSelected;
    private int oldTestPagePanelSelected;

    public Boolean refreshNeeded;

    public Event()
    {
        //Set Up the Event properties
        this.formButtonsClicked = new Boolean[] {false, false};
        this.userInputs = new String[4];

        //Set the Initial Value of the Code State to Zero
        this.codeState = 0;

        //Set up is dataUpdated to true
        this.isDataUpdated = true;

        //Set Up the refresh needed
        this.refreshNeeded = false;
    }

    public TestObject getSelectedTestObject()
    {
        //Return the test object
        return selectedTestObject;
    }

    public void setSelectedTestObject(TestObject selectedTestObject)
    {
        //Change the value of the test object
        this.selectedTestObject = selectedTestObject;
    }

    public TestElement getSelectedTestElement()
    {
        return selectedTestElement;
    }

    public void setSelectedTestElement(TestElement selectedTestElement)
    {
        this.selectedTestElement = selectedTestElement;
    }

    public IssueElement getSelectedIssueElement()
    {
        return selectedIssueElement;
    }

    public void setSelectedIssueElement(IssueElement selectedIssueElement)
    {
        //Change the property
        this.selectedIssueElement = selectedIssueElement;
    }

    public int getTestObjectOperationState()
    {
        return testObjectOperationState;
    }

    public void setTestObjectOperationState(int testObjectOperationState)
    {
        this.testObjectOperationState = testObjectOperationState;
    }

    private int testObjectOperationState;

    public void setFormButtonsClicked(int index)
    {
        //Change the value for said button and set it to true
        this.formButtonsClicked[index] = true;
    }

    public int getFromEvent()
    {
        /* Check if any button is clicked */

        //Loop through every button
        for (int i = 0; i < this.formButtonsClicked.length; i++)
        {
            //Check if the button is clicked
            if (this.formButtonsClicked[i])
            {
                //Reset ButtonValue
                this.formButtonsClicked[i] = false;

                //Return the event code
                return i;
            }
        }

        return -1;
    }

    public int getCodeState()
    {
        //Return the code State
        return codeState;
    }

    public void setCodeState(int codeState)
    {
        //Save the previous code state
        this.previousCodeState = this.codeState;

        //Set the new Value of the Code state
        this.codeState = codeState;
    }

    public void setUserAuthField(Object[] userAuthFields)
    {
        //Set the values
        this.userAuthFields = userAuthFields;
    }

    public ArrayList<TestObject> getTestObjectDisplayArrayList()
    {
        return testObjectDisplayArrayList;
    }

    public void setTestObjectDisplayArrayList(ArrayList<TestObject> testObjectDisplayArrayList)
    {
        this.testObjectDisplayArrayList = testObjectDisplayArrayList;
    }

    public ArrayList<TestElement> getTestElementDisplayArrayList()
    {
        ArrayList<TestElement> pushedTestElementDisplayArrayList = new ArrayList<TestElement>();

        //filter to get the required arrayList
        switch (this.testElementShowCode)
        {
            case 0 ->
            {
                //If the user wants to see all the tests
                pushedTestElementDisplayArrayList.addAll(this.testElementDisplayArrayList);
            }
            case 1 ->
            {
                //If the user only wants the passed tests
                for (TestElement testElement : this.testElementDisplayArrayList)
                {
                    if (testElement.getDidPass())
                    {
                        //Add it to the arrayList
                        pushedTestElementDisplayArrayList.add(testElement);
                    }
                }
            }
            case 2 ->
            {
                //If the user only wants failed tests
                for (TestElement testElement : this.testElementDisplayArrayList)
                {

                    if (!(testElement.getDidPass()))
                    {
                        //Add it to that arrayList
                        pushedTestElementDisplayArrayList.add(testElement);
                    }
                }
            }
        }

        //Return the ArrayList
        return pushedTestElementDisplayArrayList;
    }

    public void setTestElementDisplayArrayList(ArrayList<TestElement> testElementDisplayArrayList)
    {
        this.testElementDisplayArrayList = testElementDisplayArrayList;
    }

    public ArrayList<IssueElement> getIssueElementDisplayArrayList()
    {
        return issueElementDisplayArrayList;
    }

    public void setIssueElementDisplayArrayList(ArrayList<IssueElement> issueElementDisplayArrayList)
    {
        this.issueElementDisplayArrayList = issueElementDisplayArrayList;
    }

    public void setUserInput(int index, String input)
    {
        //Set up the said index in the array list to the set input
        this.userInputs[index] = input;
    }

    public String[] getAndResetUserInput()
    {
        //Make a copy of the userInputs arraylist
        String[] userInput = Arrays.copyOf(this.userInputs, this.userInputs.length);

        //Reset UseInputs
        this.userInputs = new String[4];

        //return user input
        return userInput;
    }

    public String getNameFromUserID(Object userID)
    {
        //Collect the User info
        Document userInfo = this.database.getUserInfo(String.valueOf(userID));

        return userInfo.getString("firstName") + " " + userInfo.getString("lastName");
    }

    public void setDatabase(Database database)
    {
        //set the value of the database
        this.database = database;
    }

    public synchronized void setDatabaseDateUpdated(int dataIndex, String updatedObjectID)
    {
        //fill the dataUpdate info
        this.dataUpdateInfo = new Object[] { dataIndex, updatedObjectID};

        //Set the is data Updated to false
        this.isDataUpdated = false;
    }

    public Boolean checkIfIsDataUpdated()
    {
        //Return the boolean
        return this.isDataUpdated;
    }

    public Object[] getDataUpdateInfo()
    {
        //set isDataUpdated to true
        this.isDataUpdated = true;

        //return dataUpdateInfo
        return this.dataUpdateInfo;
    }

    public ArrayList<IssueElement> getOpenIssueElementDisplayArrayList()
    {
        return openIssueElementDisplayArrayList;
    }

    public void setOpenIssueElementDisplayArrayList(ArrayList<IssueElement> openIssueElementDisplayArrayList)
    {
        //set the value of the open Issues ArrayList
        this.openIssueElementDisplayArrayList = openIssueElementDisplayArrayList;
    }

    public void getPreviousCodeState()
    {
        //Change the code state to the prevoius code state
        this.codeState = this.previousCodeState;
    }

    public int getTestElementShowCode()
    {
        return testElementShowCode;
    }

    public void setTestElementShowCode(int testElementShowCode)
    {
        this.testElementShowCode = testElementShowCode;
    }

    public int getIssueElementShowCode()
    {
        return issueElementShowCode;
    }

    public void setIssueElementShowCode(int issueElementShowCode)
    {
        this.issueElementShowCode = issueElementShowCode;
    }

    public void requestPageRefresh()
    {
        this.refreshNeeded = true;
    }

    public Boolean getRefreshNeeded()
    {
        if (this.refreshNeeded)
        {
            this.refreshNeeded = false;

            return true;
        }

        return false;
    }

    public int getOldTestPageTestComponentComboBoxSelected()
    {
        return oldTestPageTestComponentComboBoxSelected;
    }

    public void setOldTestPageTestComponentComboBoxSelected(int oldTestPageTestComponentComboBoxSelected)
    {
        this.oldTestPageTestComponentComboBoxSelected = oldTestPageTestComponentComboBoxSelected;
    }

    public int getOldTestPageIssuesComboBoxSelected()
    {
        return oldTestPageIssuesComboBoxSelected;
    }

    public void setOldTestPageIssuesComboBoxSelected(int oldTestPageIssuesComboBoxSelected)
    {
        this.oldTestPageIssuesComboBoxSelected = oldTestPageIssuesComboBoxSelected;
    }

    public int getOldTestPagePanelSelected()
    {
        return oldTestPagePanelSelected;
    }

    public void setOldTestPagePanelSelected(int oldTestPagePanelSelected)
    {
        this.oldTestPagePanelSelected = oldTestPagePanelSelected;
    }

    public void resetOldTestPageElement()
    {
        //Reset the selected elements
        this.oldTestPageIssuesComboBoxSelected = 0;
        this.oldTestPageTestComponentComboBoxSelected = 0;
        this.oldTestPagePanelSelected = 0;

        //Reset the show code
        this.testElementShowCode = 0;
        this.issueElementShowCode = 0;
    }
}
