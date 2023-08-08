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
    private final Event event;
    private JButton backButton;
    private JButton startTestButton;
    private JTextField urlField;
    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JPanel newTestForm;
    private JTextArea textArea1;

    public NewTestPage(Event event)
    {
        //SetUp object Properties
        this.event = event;
        //Set Up the action Listiners
        //form submit button
        this.startTestButton.addActionListener(e ->
        {
            //Push the text
            pushText();

            //Update the event property
            formButtonClicked(0);
        });

        this.backButton.addActionListener(e -> formButtonClicked(1));

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
                //pushText(0);
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
        this.event.setFormButtonsClicked(index);

        this.event.setCodeState(1);
    }

    private void pushText()
    {
        //Push the selected web page
        this.event.setUserInput(0, this.comboBox1.getSelectedItem().toString());

        //Push the URL
        this.event.setUserInput(1, this.urlField.getText());

        //push the username
        this.event.setUserInput(2, this.textField1.getText());

        //Push the password field
        this.event.setUserInput(3, this.passwordField1.getText());

        //Push the test description
        this.event.setUserInput(4, this.textArea1.getText());
    }
}
