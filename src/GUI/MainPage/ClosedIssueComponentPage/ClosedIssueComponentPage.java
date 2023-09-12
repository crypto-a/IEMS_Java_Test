package GUI.MainPage.ClosedIssueComponentPage;

import GUI.Event.Event;
import TestAutomations.DERMS.DERMS;
import TestAutomations.DLCDemo.DLCDemo;
import TestAutomations.Test.Test;
import TestEngine.IssueElement.IssueElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClosedIssueComponentPage
{
    private JPanel panel1;
    private JPanel mainPanel;
    private JButton returnButton;
    private JTextPane developerMessage;
    private JLabel issueID;
    private JLabel occuringDate;
    private JLabel occuringTime;
    private JLabel targetedWebPage;
    private JLabel scenario;
    private JLabel actualValue;
    private JLabel expectedValue;
    private JLabel errorMessage;
    private JLabel closedStatment;
    private JButton loadScenarioButton;
    private JLabel targetedWebElement;

    private Event event;
    private IssueElement issueElement;

    public ClosedIssueComponentPage(Event event)
    {
        //SetUp objects properties
        this.event = event;

        //Pull the issue Element
        this.issueElement = this.event.getSelectedIssueElement();

        //Set Up the text for each element
        this.issueID.setText(this.issueElement.getIssueID().toString());
        this.occuringDate.setText(this.issueElement.getOccurringDate());
        this.occuringTime.setText(this.issueElement.getOccurringTime());
        this.targetedWebPage.setText((this.issueElement.getTargetedWebPage()));
        this.targetedWebElement.setText(this.issueElement.getTargetedWebElement());

        this.actualValue.setText(this.issueElement.getActualValue());
        this.expectedValue.setText(this.issueElement.getExpectedValue());
        this.errorMessage.setText(this.issueElement.getErrorMessage());

        this.closedStatment.setText("This Issue was Closed By " + this.event.getNameFromUserID(this.issueElement.getIssueCloser()) + " on " + this.issueElement.getClosedDate()+ " at " + this.issueElement.getClosedTime());
        this.developerMessage.setText(this.issueElement.getDeveloperMessage());

        //Set AUp action Listener
        this.returnButton.addActionListener(e -> backButtonClicked());
        this.loadScenarioButton.addActionListener(e -> loadScenarioButtonClicked());

    }

    public JPanel requestContent()
    {
        //return the main panel
        return this.mainPanel;
    }

    private void backButtonClicked()
    {
        //Go back to the previous page
        this.event.getPreviousCodeState();
    }

    private void loadScenarioButtonClicked()
    {
        //Check to see witch test was requested
        switch (this.issueElement.getTargetedWebPage())
        {
            case "DLC Demo" ->
            {

                //Set Up the Thread
                Thread thread = new Thread((Runnable) new DLCDemo(this.event.getSelectedTestObject(), Test.Load_A_Scenario, this.issueElement.getScenario()));


                //Start the thread
                thread.start();
            }
            case "DERMS" ->
            {
                //Set Up the Thread
                Thread thread = new Thread((Runnable) new DERMS(this.event.getSelectedTestObject(), Test.Load_A_Scenario, this.issueElement.getScenario()));


                //Start the thread
                thread.start();


            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
