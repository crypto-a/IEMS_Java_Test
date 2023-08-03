package GUI.MainPage.OpenIssueComponentPage;

import GUI.Event.Event;
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
        this.backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                backButtonClicked();
            }
        });

        this.closeIssueButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                closeButtonClicked();
            }
        });
    }

    public JPanel requestContent()
    {
        //return the main panel Object
        return this.mainPanel;
    }

    private void backButtonClicked()
    {
//        //Submit the form
//        this.event.setFormButtonPressed(1);
    }

    private void closeButtonClicked()
    {
//        //Check if the verify Button is clicked
//        if (verifyCheckBox.isSelected())
//        {
//            System.out.println(this.developerMessage.getText());
//            //Push the give test ot the event object
//            this.event.setInputValues(0, this.developerMessage.getText());
//
//            //Submit the form
//            this.event.setFormButtonPressed(0);
//
//        }
//        else
//        {
//            JOptionPane.showMessageDialog(null, "PleaseCheck the verification Box", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
}
