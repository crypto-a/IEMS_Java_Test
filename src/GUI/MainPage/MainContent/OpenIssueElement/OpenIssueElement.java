package GUI.MainPage.MainContent.OpenIssueElement;

import GUI.Event.Event;
import TestEngine.IssueElement.IssueElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenIssueElement
{
    private JPanel mainPanel;
    private JButton closeButton;
    private JButton detailsButton;
    private JLabel issueID;
    private JLabel issueDate;

    private IssueElement issueElement;
    private Event event;

    public OpenIssueElement(IssueElement issueElement, Event event)
    {
        //SetUp object Properties
        this.issueElement = issueElement;
        this.event = event;

        //set text values
        this.issueID.setText(this.issueElement.getIssueID());
        this.issueDate.setText(this.issueElement.getOccurringDate());

        //setUp action Listeners for buttons
        this.detailsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //run the function related to the button
                detailsButtonClicked();
            }
        });
    }

    public JPanel requestElement()
    {
        //return the mainPanel
        return this.mainPanel;
    }

    private void detailsButtonClicked()
    {
        //push the selectedIssueElement
        this.event.setSelectedIssueElement(this.issueElement);

        //Change code state
        this.event.setCodeState(5);
    }

}
