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
        this.issueID.setText("this.issueElement.getIssueID().toString()");
        this.issueDate.setText("this.issueElement.getOccurringDate()");

        //setUp action Listeners for buttons
        this.detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
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
        //Push the element to the event object
//        this.event.setSelectedIssueElement(this.issueElement);
//
//        //Set the click command
//        this.event.setControlPanelButtonPressed(3);
    }

}
