package GUI.MainPage.OldTestPage.IssuesComponentElement;

import GUI.Event.Event;
import TestEngine.IssueElement.IssueElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssuesComponentElement
{
    private JLabel IssueID;
    private JPanel mainPanel;
    private JButton detailsButton;
    private JButton closeButton;

    private Event event;
    private IssueElement issueElement;

    public IssuesComponentElement(IssueElement issueElement, Event event)
    {
        //Set up object properties
        this.event = event;
        this.issueElement = issueElement;

        //Set up the display text
        this.IssueID.setText(this.issueElement.getIssueID().toString());

        //Set Up event Listeners
        this.detailsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                detailsButtonClicked();
            }
        });

        this.closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                closeButtonClick();
            }
        });
    }

    public JPanel requestElement()
    {
        //return the main Panel
        return this.mainPanel;
    }

    private void detailsButtonClicked()
    {

    }

    private void closeButtonClick()
    {

    }
}
