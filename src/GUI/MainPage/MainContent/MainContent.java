package GUI.MainPage.MainContent;

import GUI.Event.Event;
import GUI.MainPage.MainContent.OldTestElement.OldTestElement;
import GUI.MainPage.MainContent.OpenIssueElement.OpenIssueElement;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainContent
{
    private final TestEngine testEngine;
    private final Event event;

    private JPanel MainPanel;
    private JTabbedPane tabbedPane1;
    private JProgressBar progressBar1;
    private JPanel historyPanel;
    private JTextField textField1;
    private JButton searchButton;
    private JButton resetButton;
    private JButton clearButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel issuesPanel;

    public MainContent(TestEngine testEngine, Event event)
    {
        //SetUp object Properties
        this.testEngine = testEngine;
        this.event = event;
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

        ArrayList<TestObject> testsList = this.testEngine.getTestArrayList();


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

        ArrayList<IssueElement> issuesList = this.testEngine.getIssueArrayList();

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
            if (i < testsList.size() - 1) {
                this.issuesPanel.add(Box.createVerticalStrut(5));
            }


        }

        // Add margin to the issuesPanel
        this.issuesPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

    }
}
