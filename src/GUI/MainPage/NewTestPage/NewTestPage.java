package GUI.MainPage.NewTestPage;

import GUI.Event.Event;
import TestEngine.TestEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewTestPage
{
    private final TestEngine testEngine;
    private final Event event;
    private JButton backButton;
    private JButton startTestButton;
    private JTextField urlField;
    private JCheckBox UITestCheckBox;
    private JPanel mainPanel;

    public NewTestPage(TestEngine testEngine, Event event)
    {
        //SetUp object Properties
        this.testEngine = testEngine;
        this.event = event;
        //Set Up the action Listiners
        //form submit button
        this.startTestButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                formButtonClicked(0);
            }
        });

        this.backButton.addActionListener(new ActionListener()
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

    public JPanel requestContent()
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
