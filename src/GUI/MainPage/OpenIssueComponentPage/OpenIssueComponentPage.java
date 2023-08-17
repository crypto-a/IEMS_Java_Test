package GUI.MainPage.OpenIssueComponentPage;

import GUI.Event.Event;
import TestAutomations.DERMS.DERMS;
import TestAutomations.DLCDemo.DLCDemo;
import TestAutomations.Test.Test;
import TestEngine.IssueElement.IssueElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenIssueComponentPage
{
    private JPanel panel1;
    private JPanel mainPanel;
    private JButton backButton;
    private JCheckBox verifyCheckBox;
    private JButton closeIssueButton;
    private JTextArea developerMessage;
    private JLabel issueID;
    private JLabel occuringDate;
    private JLabel occuringTime;
    private JLabel targetedWebApp;
    private JLabel scenario;
    private JLabel actualValue;
    private JLabel expectedValue;
    private JLabel errorMessage;
    private JButton loadScenarioButton;
    private Event event;
    private IssueElement issueElement;

    public OpenIssueComponentPage(Event event)
    {
        //Set up object properties
        this.event = event;

        //call the selected issues for the event
        this.issueElement = this.event.getSelectedIssueElement();

        //Set Up the test
        this.issueID.setText(this.issueElement.getIssueID().toString());
        this.occuringDate.setText(this.issueElement.getOccurringDate());
        this.occuringTime.setText(this.issueElement.getOccurringTime());
        this.targetedWebApp.setText(this.issueElement.getTargetedWebPage());

        this.actualValue.setText(this.issueElement.getActualValue());
        this.expectedValue.setText(this.issueElement.getExpectedValue());
        this.errorMessage.setText(this.issueElement.getErrorMessage());

        //SetAUp action Listiners
        this.backButton.addActionListener(e -> backButtonClicked());

        this.closeIssueButton.addActionListener(e -> closeButtonClicked());
        this.loadScenarioButton.addActionListener(e -> loadScenarioButtonClicked());
    }

    public JPanel requestContent()
    {
        //return the main panel Object
        return this.mainPanel;
    }

    private void backButtonClicked()
    {
        //Change the code state
        this.event.getPreviousCodeState();
    }

    private void closeButtonClicked()
    {
        //Check if the verified Button is clicked
        if (verifyCheckBox.isSelected())
        {
            //Push the developer message to the event
            this.event.setUserInput(0, this.developerMessage.getText());

           //submit page
            this.event.setFormButtonsClicked(0);

        }
        else
        {
            JOptionPane.showMessageDialog(null, "PleaseCheck the verification Box", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
            //ToDo
        }
    }
}
