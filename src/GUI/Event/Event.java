package GUI.Event;

import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;

import java.util.ArrayList;

public class Event
{
    private int codeState;

    private Object[] userAuthFields;
    private final Boolean[] formButtonsClicked;

    private ArrayList<TestObject> testObjectDisplayArrayList;
    private ArrayList<TestElement> testElementDisplayArrayList;
    private ArrayList<IssueElement> issueElementDisplayArrayList;

    private TestElement selectedTestElement;
    private IssueElement selectedIssueElement;

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


    public Event()
    {
        //Set Up the Event properties
        this.formButtonsClicked = new Boolean[] {false, false};

        //Set the Initial Value of the Code State to Zero
        this.codeState = 0;
    }

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
        return testElementDisplayArrayList;
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
}
