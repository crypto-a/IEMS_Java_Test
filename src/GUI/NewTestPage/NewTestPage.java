package GUI.NewTestPage;

import GUI.Event.Event;
import TestManager.TestManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewTestPage
{
    private final Event event;
    private final TestManager testManager;
    private JButton newTestButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel contentPanel;
    private JPanel mainPanel;
    private JTextField urlField;
    private JButton cancelButton;
    private JCheckBox UITestsCheckBox;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JButton submitButton;

    public NewTestPage(TestManager testManager, Event event)
    {
        //SetUp object Properties
        this.testManager = testManager;
        this.event = event;
        //Set Up the action Listiners
        //form submit button
        this.submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    //Update the event property
                    formButtonClicked(0);
                }
        });

        this.cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                formButtonClicked(1);
            }
        });

        this.urlField.addKeyListener(new KeyListener()
    {
        public void keyTyped(KeyEvent e)
        {
        }

        public void keyPressed(KeyEvent e)
        {
        }

        public void keyReleased(KeyEvent e)
        {
            pushText(0);
        }
    });

    }

    public JPanel requestPage()
    {
        //Create the new MainContent Panel
        return this.mainPanel;
    }

    private void formButtonClicked(int index)
    {
        this.event.setFormButtonPressed(index);
    }

    private void pushText(int index)
    {
        this.event.setInputValues(0, this.urlField.getText());
    }
}
