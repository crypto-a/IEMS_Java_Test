package GUI.MainPage.OldTestPage.TestComponentElement;

import GUI.Event.Event;
import TestEngine.TestElement.TestElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestComponentElement
{
    private JPanel panel1;
    private JPanel mainPanel;
    private JButton detailsButton;
    private JLabel statusField;
    private JLabel testID;
    private final Event event;
    private final TestElement testElement;


    public TestComponentElement(Event event, TestElement testElement)
    {
        this.event = event;

        //Collect the selected test element
        this.testElement = testElement;

        //Set Up action Listener
        this.detailsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute the related method
                detailsButtonClicked();
            }
        });
    }


    public JPanel requestElement()
    {
        //return mainPanel
        return this.mainPanel;
    }

    private void detailsButtonClicked()
    {
        this.event.setControlPanelButtonPressed(2);
        this.event.setFormButtonPressed(1);
        this.event.setSelectedTestElement(this.testElement);
    }
}
