package GUI.MainPage.OldTestPage;

import GUI.Event.Event;
import GUI.MainPage.OldTestPage.TestComponentElement.TestComponentElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class OldTestPage
{
    private JButton backButton;
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JList list1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton searchButton;
    private JLabel testID;
    private JLabel testIssuer;
    private JLabel targetedWebPage;
    private JLabel webPageURL;
    private JLabel testTime;
    private JLabel testDuration;
    private JLabel issuesFound;
    private JLabel testDate;
    private JPanel testComponentPanel;
    private JTextField textField2;
    private JButton sendButton;
    private final Event event;
    private final TestEngine testEngine;
    private final TestObject testObject;

    public OldTestPage(TestEngine testEngine, Event event)
    {
        //SetUp Object Properties
        this.testEngine = testEngine;
        this.event = event;

        //Collect the test Object
        this.testObject = this.event.getSelectedTestObject();

        System.out.println(this.testObject.toString());

        //ToDo: Set the values the Page should Show
        this.testID.setText(this.testObject.getTestID());
        this.testIssuer.setText(this.testEngine.getUserName(this.testObject.getIssuerID()));
        this.targetedWebPage.setText(this.testObject.getTargetedWebPage());
        this.webPageURL.setText(this.testObject.getWebPageURL());
        this.testDate.setText(this.testObject.getTestDate());
        this.testTime.setText(this.testObject.getTestTime());
        this.testDuration.setText(this.testObject.getDuration());
        this.issuesFound.setText(this.testObject.getIssueNum() + " Issues found");

        //Set Up the page lists
        this.setUpLists();


        //ToDo: Set Up the action listeners for the page


    }

    public JPanel requestContent()
    {
        //return the main Panel
        return this.mainPanel;
    }

    private void createUIComponents()
    {
        // Create the JPanel Object
        this.testComponentPanel = new JPanel();

        // Set Up the Border Properties
        this.testComponentPanel.setLayout(new BoxLayout(this.testComponentPanel, BoxLayout.Y_AXIS));


    }

    private void setUpLists()
    {
        //Collect the array List of TestElements form the test Object
        ArrayList<TestElement> testElements = this.testObject.getTestElements();

        //Loop though all the elements
        for (int i = 0; i < testElements.size(); i++)
        {
            //Create the elements
            TestComponentElement testComponentElement = new TestComponentElement(this.event, testElements.get(i));

            //Collect the JPanel element
            JPanel elementPanel = testComponentElement.requestElement();

            // Create a container panel for each element
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

            // Add the element panel to the container panel
            containerPanel.add(elementPanel);

            // Set the maximum height for the container panel
            containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            // Add the container panel to the history panel
            this.testComponentPanel.add(containerPanel);

            // Add vertical spacing between elements
            if (i < testElements.size() - 1) {
                this.testComponentPanel.add(Box.createVerticalStrut(5));
            }
        }

        // Add margin to the historyPanel
        this.testComponentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
