package GUI.Event;

import Database.Database;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
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
    private Boolean[] formButtonsClicked;
    private ArrayList<TestObject> testObjectDisplayArrayList;
    private ArrayList<TestElement> testElementDisplayArrayList;
    private ArrayList<IssueElement> issueElementDisplayArrayList;
    private ArrayList<IssueElement> openIssueElementDisplayArrayList;
    private ArrayList<String> testLogsDisplayArrayList;
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
    private int mainPagePanelSelected;
    private int mainPageTestObjectSortComboBoxSelect;
    public Boolean refreshNeeded;
    private Boolean testObjectSortRequested;
    private Boolean openIssuesSortRequested;
    private ArrayList<String[]> usersList;

    private int testHistoryPageIssuerComboBoxSelected;
    private int openIssuesPageSortComboBoxSelected;

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

        //Set the initial value of the mainPagePanelSelected to zero
        this.mainPagePanelSelected = 0;
        this.testObjectSortRequested = false;
        this.openIssuesSortRequested = false;

        //Set the initial values for the filters
        this.testHistoryPageIssuerComboBoxSelected = 0;
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
        //Create the pushtestaArrayLst
        ArrayList<TestObject> pushTetsObjectArrayList = new ArrayList<>();

        //Depending on the issue code
        switch (this.testHistoryPageIssuerComboBoxSelected)
        {
            case 0 ->
            {
                pushTetsObjectArrayList.addAll(this.testObjectDisplayArrayList);
            }
            default ->
            {
                //get the users object ID
               String userID = this.usersList.get(this.testHistoryPageIssuerComboBoxSelected - 1)[1];

               //Loop through every element
                for (TestObject testObject: this.testObjectDisplayArrayList)
                {
                    //check if it's made by that user
                    if (testObject.getIssuerID().toString().equals(userID))
                    {
                        //add it to the push arrayList
                        pushTetsObjectArrayList.add(testObject);
                    }
                }
            }

        }

        return testObjectDisplayArrayList;

    }

    public void setTestObjectDisplayArrayList(ArrayList<TestObject> testObjectDisplayArrayList)
    {
        this.testObjectDisplayArrayList = testObjectDisplayArrayList;
    }

    public ArrayList<TestElement> getTestElementDisplayArrayList()
    {
        ArrayList<TestElement> pushedTestElementDisplayArrayList = new ArrayList<>();

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
        //Create an Array list in witch the related files will be put
        ArrayList<IssueElement> pushedIssueElementArrayList = new ArrayList<>();

        switch (this.issueElementShowCode)
        {
            case 0 ->
            {
                //add all the object to the array list
                pushedIssueElementArrayList.addAll(this.issueElementDisplayArrayList);
            }

            case 1 ->
            {
                /* If they only want to see open issues */

                //loop through all the elements
                for (IssueElement issueElement: this.issueElementDisplayArrayList)
                {
                    //Check if the issue sis open
                    if (issueElement.getIsIssueOpen())
                    {
                        //Add it to the push arraylist
                        pushedIssueElementArrayList.add(issueElement);
                    }
                }
            }

            case 2 ->
            {
                /* If they only want to see the closed Issues */

                //loop through all the elements
                for (IssueElement issueElement: this.issueElementDisplayArrayList)
                {
                    //Check if the issue sis Closed
                    if (!(issueElement.getIsIssueOpen()))
                    {
                        //Add it to the push arraylist
                        pushedIssueElementArrayList.add(issueElement);
                    }
                }
            }
        }

        //Return the arrayList
        return pushedIssueElementArrayList;
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
        //Return the openIssues ArrayList
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

    public String[] getTestLogsArrayList()
    {
        //Initialize the arrayList
        this.testLogsDisplayArrayList = new ArrayList<>();

        //Add the first statement to it
        this.testLogsDisplayArrayList.add("Tests Started - Time: " + this.selectedTestObject.getTestStartTime());

        //loop through all the issue elements
        for (TestElement testElement: this.testElementDisplayArrayList)
        {
            //add the test log of that element to the arraylist
            this.testLogsDisplayArrayList.add(testElement.getTestLog());
        }

        //Add Final Statement
        this.testLogsDisplayArrayList.add("Tests Ended - Time: " + this.selectedTestObject.getTestEndTime());

        //Retunr the arraylist
        return this.testLogsDisplayArrayList.toArray(new String[0]);
    }

    public int getMainPagePanelSelected()
    {
        return mainPagePanelSelected;
    }

    public void setMainPagePanelSelected(int mainPagePanelSelected)
    {
        this.mainPagePanelSelected = mainPagePanelSelected;
    }

    public int getMainPageTestObjectSortComboBoxSelect()
    {
        return mainPageTestObjectSortComboBoxSelect;
    }

    public void setMainPageTestObjectSortComboBoxSelect(int mainPageTestObjectSortComboBoxSelect)
    {
        this.mainPageTestObjectSortComboBoxSelect = mainPageTestObjectSortComboBoxSelect;
    }

    public void requestTestObjectSort()
    {
        this.testObjectSortRequested = true;
    }

    public Boolean isTestObjectSortRequested()
    {
        //Check if a sort is requested
        if(this.testObjectSortRequested)
        {
            //set the value to false
            this.testObjectSortRequested = false;

            //Return true to initialize sort
            return true;
        }
        else
        {
            //Return false to not do an action
            return false;
        }
    }

    public ArrayList<String[]> getUsersList()
    {
        return usersList;
    }

    public void setUsersList(ArrayList<String[]> usersList)
    {
        this.usersList = usersList;
    }

    public void setPreviousCodeState(int previousCodeState)
    {
        this.previousCodeState = previousCodeState;
    }

    public int getTestHistoryPageIssuerComboBoxSelected()
    {
        return testHistoryPageIssuerComboBoxSelected;
    }

    public void setTestHistoryPageIssuerComboBoxSelected(int testHistoryPageIssuerComboBoxSelected)
    {
        this.testHistoryPageIssuerComboBoxSelected = testHistoryPageIssuerComboBoxSelected;
    }

    public int getOpenIssuesPageSortComboBoxSelected()
    {
        return openIssuesPageSortComboBoxSelected;
    }

    public void setOpenIssuesPageSortComboBoxSelected(int openIssuesPageSortComboBoxSelected)
    {
        this.openIssuesPageSortComboBoxSelected = openIssuesPageSortComboBoxSelected;
    }

    public void requestOpenIssuesSort()
    {
        this.openIssuesSortRequested = true;
    }

    public Boolean isOpenIssuesSortRequested()
    {
        //Check if a sort is requested
        if(this.openIssuesSortRequested)
        {
            //set the value to false
            this.openIssuesSortRequested = false;

            //Return true to initialize sort
            return true;
        }
        else
        {
            //Return false to not do an action
            return false;
        }
    }

}
