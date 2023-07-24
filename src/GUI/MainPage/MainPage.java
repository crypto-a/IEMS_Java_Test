package GUI.MainPage;

import GUI.Event.Event;
import GUI.MainPage.MainContent.MainContent;
import GUI.MainPage.NewTestPage.NewTestPage;
import GUI.MainPage.OldTestPage.OldTestPage;
import GUI.MainPage.OldTestPage.TestComponentElement.TestComponentElement;
import GUI.MainPage.TestComponentPage.TestComponentPage;
import User.User;
import TestEngine.TestEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage
{
    /* Object Properties */
    private final Event event;
    private final TestEngine testEngine;
    private final User user;
    private JPanel mainPanel;
    private JButton newTestButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel contentPanel;
    private JButton logOutButton;
    private JLabel userName;
    private JPanel userContent;
    private JPanel historyPanel;


    public MainPage(TestEngine testEngine, Event event, User user)
    {
        //SetUp object Properties
        this.testEngine = testEngine;
        this.event = event;
        this.user = user;

        //set up persons name
        this.userName.setText(this.user.getName());

        this.newTestButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                clickedButton(0);
            }
        });

    }

    public JPanel requestPage()
    {
        //Create the new MainContent Panel
        return this.mainPanel;
    }


    private void clickedButton(int index)
    {
        //Set uo the new value
        this.event.setControlPanelButtonPressed(index);
    }

    private void createUIComponents()
    {
        // Create the JPanel with BorderLayout
        this.contentPanel = new JPanel(new BorderLayout());

        // Check what content is being displayed
        switch (this.event.requestCurrentPage()) {
            case "mainPage":
                // Create the class of the content page
                MainContent mainContent = new MainContent(this.testEngine, this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(mainContent.requestContent(), BorderLayout.CENTER);
                break;

            case "addTestPage":
                //Create the newTest Object
                NewTestPage newTestPage = new NewTestPage(this.testEngine, this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(newTestPage.requestContent(), BorderLayout.CENTER);
                break;
            case "oldTestDisplay":
                //create the test Object
                OldTestPage oldTestPage = new OldTestPage(this.testEngine, this.event);

                //Add it to the content panel in the UI
                this.contentPanel.add(oldTestPage.requestContent(), BorderLayout.CENTER);
                break;

            case "oldTestUnitDisplay":
                 //Create the Page Object
                TestComponentPage testComponentPage = new TestComponentPage(this.testEngine, this.event);

                //collect empanelling form, the UI
                this.contentPanel.add(testComponentPage.requestContent());
                break;
        }

    }
}
