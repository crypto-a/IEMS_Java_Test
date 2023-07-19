package GUI.OldTestPage;

import GUI.Event.Event;
import TestEngine.Test.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OldTestPage
{
    private final Event event;
    private JButton newTestButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel contentPanel;
    private JButton cancelButton;
    private JTree tree1;
    private JPanel mainPanel;
    private JLabel timeOfTestLabel;
    private JLabel dateOfTestLabel;
    private JLabel testIDLabel;
    private JLabel testID;
    private JLabel dateOfTest;
    private JLabel timeOfTest;

    public OldTestPage(Event event)
    {
        //SetUp objet properties
        this.event = event;

        Test test = this.event.getSelectedTestObject();
        //Build Page content
        this.testID.setText(test.getTestID());
        this.dateOfTest.setText(test.getTestDate());
        this.timeOfTest.setText(test.getTestTime());

        //Setup Event Listeners
        this.cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                formButtonClicked(1);
            }
        });
    }

    public JPanel requestPage()
    {
        return this.mainPanel;
    }

    private void formButtonClicked(int index)
    {
        this.event.setFormButtonPressed(index);
    }

}
