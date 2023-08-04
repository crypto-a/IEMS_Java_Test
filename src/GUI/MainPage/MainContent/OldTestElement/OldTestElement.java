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

    public OldTestElement(TestObject testObject, Event event)
    {
        this.testObject = testObject;
        this.event = event;

        this.TestID.setText(this.testObject.getTestID());

        this.testDate.setText(this.testObject.getTestDate());

        this.testTime.setText(this.testObject.getTestStartTime());

        this.detailsButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                //Call the function for the related button
                detailsButtonClicked();
            }
        });

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
