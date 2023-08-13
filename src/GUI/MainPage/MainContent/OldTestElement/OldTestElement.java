package GUI.MainPage.MainContent.OldTestElement;

import GUI.Event.Event;
import TestEngine.TestObject.TestObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OldTestElement
{
    private final Event event;
    private final TestObject testObject;
    private JButton detailsButton;
    private JLabel TestID;
    private JLabel testDate;
    private JLabel testTime;
    private JPanel elementPanel;
    private JProgressBar progressBar1;
    private JLabel ongoingText;

    public OldTestElement(TestObject testObject, Event event)
    {
        this.testObject = testObject;
        this.event = event;

        this.TestID.setText(this.testObject.getTestID());

        this.testDate.setText(this.testObject.getTestDate());

        this.testTime.setText(this.testObject.getTestStartTime());

        if (this.testObject.getDuration().equals("Test is currently Running"))
        {

        }
        else
        {
            this.progressBar1.setVisible(false);
            this.ongoingText.setVisible(false);
        }

        this.detailsButton.addActionListener(e -> detailsButtonClicked());


    }

    public JPanel getElementPanel()
    {
        //return element panel
        return this.elementPanel;
    }

    private void detailsButtonClicked()
    {
        //Set the selected test object
        this.event.setSelectedTestObject(this.testObject);

        //change the code state
        this.event.setCodeState(3);
    }
}
