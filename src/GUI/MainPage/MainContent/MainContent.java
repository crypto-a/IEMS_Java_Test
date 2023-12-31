package GUI.MainPage.MainContent;

import GUI.Event.Event;
import GUI.MainPage.MainContent.OldTestElement.OldTestElement;
import GUI.MainPage.MainContent.OpenIssueElement.OpenIssueElement;
import TestEngine.IssueElement.IssueElement;
import User.User;
import TestEngine.TestObject.TestObject;
import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainContent
{
    private final Event event;
    private final User user;

    private JPanel MainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel historyPanel;
    private JTextField textField1;
    private JButton searchButton;
    private JButton resetButton;
    private JButton clearButton;
    private JComboBox comboBox1;
    private JComboBox testHistoryIssuerComboBox;
    private JPanel issuesPanel;
    private JComboBox comboBox2;
    private JSpinner spinner1;
    private JButton loadButton;
    private JComboBox issuesIssuerComboBox;

    public MainContent(Event event, User user)
    {
        //SetUp object Properties
        this.event = event;
        this.user = user;

        //Set Up the values for the filters
        ArrayList<String[]> userList = this.event.getUsersList();

        //Create the userList array
        String[] userNamesList = new String[userList.size() + 1];

        //Add the first element
        userNamesList[0] = " <Select An Option> ";

        //Populate the userNameList
        for (int i = 0; i < userList.size(); i++)
        {
            //add the names to the arraylist
            userNamesList[i + 1] = userList.get(i)[0];
        }

        //Add the ArrayList to the issuer filters
        this.testHistoryIssuerComboBox.setModel(new DefaultComboBoxModel(userNamesList));

        //Set up the panel selected
        this.tabbedPane1.setSelectedIndex(this.event.getMainPagePanelSelected());
        this.comboBox1.setSelectedIndex(this.event.getMainPageTestObjectSortComboBoxSelect());
        this.comboBox2.setSelectedIndex(this.event.getOpenIssuesPageSortComboBoxSelected());
        this.testHistoryIssuerComboBox.setSelectedIndex(this.event.getTestHistoryPageIssuerComboBoxSelected());
        this.textField1.setText(this.event.getSearchQuery());
        this.spinner1.setValue(this.event.getDatabaseShowNumber());

        //Set up an event listener for the changes in the panels
        this.tabbedPane1.addChangeListener(e -> mainPagePanelChange());
        this.comboBox1.addActionListener(e -> sortRequest());
        this.testHistoryIssuerComboBox.addActionListener(e -> testSFilterRequest());
        this.resetButton.addActionListener(e -> resetButtonClicked());
        this.comboBox2.addActionListener(e -> openIssuesSortRequest());
        this.searchButton.addActionListener(e -> searchRequest());
        this.clearButton.addActionListener(e -> clearSearch());
        this.loadButton.addActionListener(e -> databaseLoadChange());

    }

    public JPanel requestContent()
    {
        //return the main panel
        return this.MainPanel;
    }

    private void createUIComponents()
    {
        /* Fill the History page with elements */
        this.historyPanel = new JPanel();
        this.historyPanel.setLayout(new BoxLayout(this.historyPanel, BoxLayout.Y_AXIS));

        ArrayList<TestObject> testsList = this.event.getTestObjectDisplayArrayList();


        for (int i = 0; i < testsList.size(); i++)
        {
            OldTestElement oldTestElement = new OldTestElement(testsList.get(i), this.event);
            JPanel elementPanel = oldTestElement.getElementPanel();

            // Create a container panel for each element
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

            // Add the element panel to the container panel
            containerPanel.add(elementPanel);

            // Set the maximum height for the container panel
            containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

            // Add the container panel to the history panel
            this.historyPanel.add(containerPanel);

            // Add vertical spacing between elements
            if (i < testsList.size() - 1) {
                this.historyPanel.add(Box.createVerticalStrut(5));
            }
        }

        // Add margin to the historyPanel
        this.historyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        /* Fill the Open Issue panel */
        this.issuesPanel = new JPanel();
        this.issuesPanel.setLayout(new BoxLayout(this.issuesPanel, BoxLayout.Y_AXIS));

        ArrayList<IssueElement> issuesList = this.event.getOpenIssueElementDisplayArrayList();

        //Loop through all the issues
        for (int i = 0; i < issuesList.size(); i++)
        {
            OpenIssueElement openIssueElement = new OpenIssueElement(issuesList.get(i), this.event);
            JPanel elementPanel = openIssueElement.requestElement();

            // Create a container panel for each element
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

            // Add the element panel to the container panel
            containerPanel.add(elementPanel);

            // Set the maximum height for the container panel
            containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

            // Add the container panel to the issues panel
            this.issuesPanel.add(containerPanel);

            // Add vertical spacing between elements
            if (i < testsList.size() - 1)
            {
                this.issuesPanel.add(Box.createVerticalStrut(5));
            }


        }

        // Add margin to the issuesPanel
        this.issuesPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

    }

    private void mainPagePanelChange()
    {
        //Save the change to the event object
        this.event.setMainPagePanelSelected(this.tabbedPane1.getSelectedIndex());
    }

    private void sortRequest()
    {
        //Push the sort index to the event
        this.event.setMainPageTestObjectSortComboBoxSelect(this.comboBox1.getSelectedIndex());

        //request a sort
        this.event.requestTestObjectSort();

        //RequestUI Update
        this.event.requestPageRefresh();
    }

    private void testSFilterRequest()
    {
        //push the item selected to the event object
        this.event.setTestHistoryPageIssuerComboBoxSelected(this.testHistoryIssuerComboBox.getSelectedIndex());

        //request a refresh
        this.event.requestPageRefresh();
    }

    private void resetButtonClicked()
    {
        //reset the filter
        this.testHistoryIssuerComboBox.setSelectedIndex(0);
    }

    private void openIssuesSortRequest()
    {
        //Push the sort index to the event
        this.event.setOpenIssuesPageSortComboBoxSelected(this.comboBox2.getSelectedIndex());

        //request a sort
        this.event.requestOpenIssuesSort();

        //RequestUI Update
        this.event.requestPageRefresh();
    }

    private void searchRequest()
    {
        //Get the search object form the event and run the query
        this.event.searchTestObject(this.textField1.getText());

        //request update for mage
        this.event.requestPageRefresh();
    }

    private void clearSearch()
    {
        //Reset the search
        this.event.searchTestObject("");

        //refresh page
        this.event.requestPageRefresh();
    }

    private void databaseLoadChange()
    {
        if ((int) this.spinner1.getValue() < 0)
        {
            this.spinner1.setValue(0);

            //Show error
            JOptionPane.showMessageDialog(null, "Sorry, You are not allowed to put in Numbers less than 0", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!this.event.getIsTestRunning())
        {
            //push the number to the event
            this.event.setDatabaseShowNumber((int) this.spinner1.getValue());

            //request to reset data
            this.event.requestDataReset();

            //request Page refresh
            this.event.requestPageRefresh();


        }
        else
        {
            //reset value
            this.spinner1.setValue(this.event.getDatabaseShowNumber());

            //Show error
            JOptionPane.showMessageDialog(null, "Oh No. You can't change the loading limit during a test! Try again later!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
