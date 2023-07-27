package GUI.Event;

import TestEngine.IssueElement.IssueElement;
import TestEngine.Test.Test;
import TestEngine.TestElement.TestElement;
import TestEngine.TestObject.TestObject;
import org.openqa.selenium.WebDriver;

public class Event
{
    private String currentPage;
    private Boolean[] controlPanelButtons;
    private Boolean[] formButtons;
    private Boolean isEventHappening;
    private TestObject selectedTestObject;
    private IssueElement selectedIssueElement;

    private String[] userInput;

    private Test[] testHistoryDisplay;

    private TestElement selectedTestElement;

    private WebDriver driver;
    public Event ()
    {
        //SetUp properties
        this.currentPage = "SignInPage";
        this.isEventHappening = false;
        this.userInput = new String[] {"", "", "", "", "", ""};

        //Set Up the button Panel [0]: newTest, [1]: testHistoryDetails button
        this.controlPanelButtons = new Boolean[] {false, false, false, false, false};

        //Set Up the for buttons [0]: Submit, [1] : Back
        this.formButtons = new Boolean[] {false, false};

    }

    public int getEvent()
    {
        /* Check if any button is clicked */

        //Loop through every button
        for (int i = 0; i < this.controlPanelButtons.length; i++)
        {
            //Check if the button is clicked
            if (this.controlPanelButtons[i])
            {
                //Start the event
                this.startEvent();

                //set button value to false
                this.controlPanelButtons[i] = false;

                //Return the event code
                return i;
            }
        }

        return -1;


    }

    public String requestCurrentPage()
    {
        //return the current page
        return this.currentPage;
    }

    public void changeCurrentPage(String newCurrentPage)
    {
        //set the new value for the current page
        this.currentPage = newCurrentPage;
    }

    /*****************************************
     /*Method Name: startEvent
     /*Programmer Name: Ali Rahbar
     /*Method Date: January 21, 2023
     /*Method Description: This method sets the value for the isEventHappening to true
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    private void startEvent ()
    {
        //Set is event to True
        this.isEventHappening = true;
    }

    /*****************************************
     /*Method Name: endEvent
     /*Programmer Name: Ali Rahbar
     /*Method Date: January 21, 2023
     /*Method Description: This method sets the value for the isEventHappening to false
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public int endEvent ()
    {
        //Set isEventHappening to False
        this.isEventHappening = false;

        //Return 0 for closing the page
        return 2;
    }

    /*****************************************
     /*Method Name: buttonPressed
     /*Programmer Name: Ali Rahbar
     /*Method Date: January 21, 2023
     /*Method Description: This method sets the value of the given button to true so the getEvent can detect it
     /*Method Inputs: index
     /*Method Outputs: None
     ******************************************/
    public void setControlPanelButtonPressed (int index)
    {
        //Set the Chose buttons value to true
        this.controlPanelButtons[index] = true;
    }

    public void setFormButtonPressed(int index)
    {
        //Change the value for said button and set it to true
        this.formButtons[index] = true;
    }

    public int getFromEvent()
    {
        /* Check if any button is clicked */

        //Loop through every button
        for (int i = 0; i < this.formButtons.length; i++)
        {
            //Check if the button is clicked
            if (this.formButtons[i])
            {
                //Reset ButtonValue
                this.formButtons[i] = false;

                //Return the event code
                return i;
            }
        }

        return -1;
    }

    /*****************************************
     /*Method Name: setInputValues
     /*Programmer Name: Ali Rahbar
     /*Method Date: January 21, 2023
     /*Method Description: This method saves the input typed by the user
     /*Method Inputs: index, input
     /*Method Outputs: None
     ******************************************/
    public void setInputValues (int index, String input)
    {
        //Set the given index of the input array to the given value
        this.userInput[index] = input;
    }

    /*****************************************
     /*Method Name: getInputValues
     /*Programmer Name: Ali Rahbar
     /*Method Date: January 21, 2023
     /*Method Description: This method returns the input array
     /*Method Inputs: None
     /*Method Outputs: inputValues array
     ******************************************/
    public String[] getInputValues()
    {
        //Return the inputValues array
        return this.userInput;
    }

    /*****************************************
     /*Method Name: resetInput
     /*Programmer Name: Ali Rahbar
     /*Method Date: January 21, 2023
     /*Method Description: This method assigns the default value for the inputValues array
     /*Method Inputs: None
     /*Method Outputs: None
     ******************************************/
    public void resetInput()
    {
        //Set the inputValues property back to defuel value
        this.userInput = new String[] {"", "", "", ""};
    }
    public void setSelectedTestObject(TestObject selectedTestObject)
    {
        this.selectedTestObject = selectedTestObject;
    }

    public TestObject getSelectedTestObject()
    {
        //return selected test object
        return this.selectedTestObject;
    }

    public void setTestHistoryDisplay(Test[] testHistory)
    {
        this.testHistoryDisplay = testHistory;
    }

    public Test[] getTestHistoryDisplay()
    {
        return this.testHistoryDisplay;
    }

    public void setSelectedTestElement(TestElement testElement)
    {
        //set the value to the given test element
        this.selectedTestElement = testElement;
    }

    public TestElement getSelectedTestElement()
    {
        //return the selected Test Element
        return this.selectedTestElement;
    }

    public IssueElement getSelectedIssueElement()
    {
        return this.selectedIssueElement;
    }

    public void setSelectedIssueElement(IssueElement issueElement)
    {
        this.selectedIssueElement = issueElement;
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }
}

