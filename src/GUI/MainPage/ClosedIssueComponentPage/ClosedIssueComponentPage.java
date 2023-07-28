package GUI.MainPage.ClosedIssueComponentPage;

import GUI.Event.Event;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestEngine;

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

    private Event event;
    private IssueElement issueElement;

    public ClosedIssueComponentPage(Event event, TestEngine testEngine)
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
        this.scenario.setText(this.issueElement.getScenario().toString());

        this.actualValue.setText(this.issueElement.getActualValue());
        this.expectedValue.setText(this.issueElement.getExpectedValue());
        this.errorMessage.setText(this.issueElement.getErrorMessage());

        this.closedStatment.setText("This Issue was Closed By " + "testEngine.getUserName(this.issueElement.getIssueCloser())" + " on " + this.issueElement.getClosedDate()+ " at " + this.issueElement.getClosedTime());
        this.developerMessage.setText(this.issueElement.getDeveloperMessage());

        //Set AUp action Listener
        this.returnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                backButtonClicked();
            }
        });

    }

    public JPanel requestContent()
    {
        //return the main panel
        return this.mainPanel;
    }

    private void backButtonClicked()
    {
//        //Submit the form
//        this.event.setFormButtonPressed(1);
    }
}
