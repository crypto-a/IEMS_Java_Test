package GUI.MainPage;

import GUI.Event.Event;
import GUI.MainPage.MainContent.MainContent;
import GUI.MainPage.NewTestPage.NewTestPage;
import TestManager.TestManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage
{
    /* Object Properties */
    private final Event event;
    private final TestManager testManager;
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


    public MainPage(TestManager testManager, Event event)
    {
        //SetUp object Properties
        this.testManager = testManager;
        this.event = event;




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
                MainContent mainContent = new MainContent(this.testManager, this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(mainContent.requestContent(), BorderLayout.CENTER);
                break;

            case "addTestPage":
                //Create the newTest Object
                NewTestPage newTestPage = new NewTestPage(this.testManager, this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(newTestPage.requestContent(), BorderLayout.CENTER);
                break;
            case "oldTestDisplay":
                break;
        }

    }
}
