package GUI.MainPage;

import GUI.Event.Event;
import GUI.MainPage.MainContent.MainContent;
import GUI.MainPage.NewTestPage.NewTestPage;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage
{
    private JPanel mainPanel;
    private JButton newTestButton;
    private JButton Refresh;
    private JButton settingsButton;
    private JButton logOutButton;
    private JPanel contentPanel;
    private JLabel nameField;
    private JButton button1;
    private JButton refreshUIButton;

    private final User user;
    private final Event event;

    public MainPage(Event event, User user)
    {
        //Set Up object properties
        this.event = event;
        this.user = user;

        //Setup action listeners for the buttons in the control panel
        this.newTestButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                newTestButtonClicked();
            }
        });

        this.logOutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logOutButtonClicked();
            }
        });
    }

    public JPanel getMainPanel()
    {
        //Return the main panel
        return mainPanel;
    }

    private void createUIComponents()
    {
        // Create the JPanel with BorderLayout
        this.contentPanel = new JPanel(new BorderLayout());

        // Check what content is being displayed
        switch (this.event.getCodeState()) {
            case 1:
                // Create the class of the content page
                MainContent mainContent = new MainContent(this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(mainContent.requestContent(), BorderLayout.CENTER);
                break;

            case 2:
                //Create the newTest Object
                NewTestPage newTestPage = new NewTestPage(this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(newTestPage.requestContent(), BorderLayout.CENTER);
                break;
                    }

    }

    private void newTestButtonClicked()
    {
        this.event.setCodeState(2);
    }

    private void logOutButtonClicked()
    {
        this.event.setCodeState(0);
    }
}
