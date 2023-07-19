package GUI.MainPage.OldTestElement;

import GUI.Event.Event;
import TestEngine.Test.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OldTestElement
{
    private final Event event;
    private final Test test;
    private JButton detailsButton;
    private JLabel TestID;
    private JLabel testDate;
    private JLabel testTime;
    private JPanel elementPanel;

    public OldTestElement(Test test, Event event)
    {
        this.test = test;
        this.event = event;

        this.TestID.setText(this.test.getTestID());

        this.testDate.setText(this.test.getTestDate());

        this.testTime.setText(this.test.getTestTime());

        this.detailsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
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
        this.event.setControlPanelButtonPressed(1);
        this.event.setSelectedTestObject(this.test);
    }
}
