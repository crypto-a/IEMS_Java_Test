package GUI.MainPage;

import GUI.Event.Event;
import GUI.GUI;
import GUI.MainPage.AddUserPage.AddUserPage;
import GUI.MainPage.ClosedIssueComponentPage.ClosedIssueComponentPage;
import GUI.MainPage.MainContent.MainContent;
import GUI.MainPage.NewTestPage.NewTestPage;
import GUI.MainPage.OldTestPage.OldTestPage;
import GUI.MainPage.OpenIssueComponentPage.OpenIssueComponentPage;
import GUI.MainPage.TestComponentPage.TestComponentPage;
import GUI.MainPage.UsersPage.UsersPage;
import User.User;

import javax.swing.*;
import java.awt.*;

public class MainPage
{
    private JPanel mainPanel;
    private JButton newTestButton;
    private JButton usersButton;
    private JButton settingsButton;
    private JButton logOutButton;
    private JPanel contentPanel;
    private JLabel nameField;
    private JButton refreshUIButton;
    private JButton mainButton;

    private final User user;
    private final Event event;
    private final GUI gui;

    public MainPage(GUI gui, Event event, User user)
    {
        //Set Up object properties
        this.gui = gui;
        this.event = event;
        this.user = user;

        //Setup action listeners for the buttons in the control panel
        this.newTestButton.addActionListener(e -> newTestButtonClicked());
        this.logOutButton.addActionListener(e -> logOutButtonClicked());
        this.refreshUIButton.addActionListener(e -> refreshButtonCLicked());
        this.usersButton.addActionListener(e -> usersButtonClicked());
        this.mainButton.addActionListener(e -> mainButtonClicked());
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
        switch (this.event.getCodeState())
        {
            case 1 ->
            {
                // Create the class of the content page
                MainContent mainContent = new MainContent(this.event, this.user);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(mainContent.requestContent(), BorderLayout.CENTER);
            }
            case 2 ->
            {
                //Create the newTest Object
                NewTestPage newTestPage = new NewTestPage(this.event);

                // Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(newTestPage.requestContent(), BorderLayout.CENTER);
            }
            case 3 ->
            {
                //Create the old test display object
                OldTestPage oldTestPage = new OldTestPage(this.event);

                //Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(oldTestPage.requestContent(), BorderLayout.CENTER);
            }
            case 4 ->
            {
                //Create the testElement display page
                TestComponentPage testComponentPage = new TestComponentPage(this.event);

                //Add it to the contentPanel, and specify the region to fill (CENTER in this case)
                this.contentPanel.add(testComponentPage.requestContent(), BorderLayout.CENTER);
            }
            case 5 ->
            {
                if (this.event.getSelectedIssueElement().getIsIssueOpen())
                {
                    //Create the open page request
                    OpenIssueComponentPage openIssueComponentPage = new OpenIssueComponentPage(this.event);

                    //Collect and implement it in the UI
                    this.contentPanel.add(openIssueComponentPage.requestContent());

                }
                else
                {
                    //Create the closed request Page
                    ClosedIssueComponentPage closedIssueComponentPage = new ClosedIssueComponentPage(this.event);
                    //Collect and implement it in the UI
                    this.contentPanel.add(closedIssueComponentPage.requestContent());
                }
            }
            case 6 ->
            {

            }
            case 7 ->
            {
                //Create the UserPage
                UsersPage usersPage = new UsersPage(this.user, this.event);

                //Request the main content
                this.contentPanel.add(usersPage.requestContent());
            }
            case 8 ->
            {
                //Create the addUser page
                AddUserPage addUserPage = new AddUserPage(this.event, this.user);

                //Request the main content
                this.contentPanel.add(addUserPage.requestContent());
            }
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

    private void refreshButtonCLicked()
    {
        this.gui.updateMainPage();
    }

    private void usersButtonClicked()
    {
        //Change code state
        this.event.setCodeState(7);
    }

    private void mainButtonClicked()
    {
        //reset the panel in the main page
        this.event.setMainPagePanelSelected(0);


        //change the code state
        this.event.setCodeState(1);
    }
}
