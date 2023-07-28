package GUI.MainPage.OldTestPage;

import GUI.Event.Event;
import GUI.MainPage.OldTestPage.IssuesComponentElement.IssuesComponentElement;
import GUI.MainPage.OldTestPage.TestComponentElement.TestComponentElement;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;
import TestEngine.TestObject.TestObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OldTestPage
{
    private JButton returnButton;
    private JPanel mainPanel;
    private JList list1;
    private JLabel testID;
    private JLabel testIssuer;
    private JLabel targetedWebPage;
    private JLabel webPageURL;
    private JLabel testTime;
    private JLabel testDuration;
    private JLabel issuesFound;
    private JLabel testDate;
    private JPanel testComponentPanel;
    private JPanel issuesPanel;
    private JButton emailMeTheResultsButton;
    private JButton exportPDFButton;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JPanel testComponentElementsPanel;
    private JComboBox comboBox2;
    private JPanel issuesElementPanel;
    private final Event event;
    private final TestEngine testEngine;
    private final TestObject testObject;

    public OldTestPage(TestEngine testEngine, Event event, TestObject testObject)
    {
        //SetUp Object Properties
        this.testEngine = testEngine;
        this.event = event;
        this.testObject = testObject;

        //System.out.println(this.testObject.toString());

        //ToDo: Set the values the Page should Show
        this.testID.setText(this.testObject.getTestID());
        this.testIssuer.setText("this.testEngine.getUserName(this.testObject.getIssuerID())");
        this.targetedWebPage.setText(this.testObject.getTargetedWebPage());
        this.webPageURL.setText(this.testObject.getWebPageURL());
        this.testDate.setText(this.testObject.getTestDate());
        this.testTime.setText(this.testObject.getTestTime());
        this.testDuration.setText(this.testObject.getDuration());
        this.issuesFound.setText(this.testObject.getIssueNum() + " Issues found");


        //ToDo: Set Up the action listeners for the page
        this.returnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Update the event property
                formButtonClicked(1);
            }
        });

    }

    public JPanel requestContent()
    {
        //return the main Panel
        return this.mainPanel;
    }

    private void createUIComponents()
    {
        //Create the two JPanels
        this.testComponentElementsPanel = new JPanel();
        this.issuesElementPanel = new JPanel();

//        //Set Up a box Layout for both
//        this.testComponentElementsPanel.setLayout(new BoxLayout(this.testComponentElementsPanel, BoxLayout.Y_AXIS));
//        this.issuesElementPanel.setLayout(new BoxLayout(this.issuesElementPanel, BoxLayout.Y_AXIS));
//
//        //Collect the two array lists to display them
//        ArrayList<TestElement> testElements = this.testObject.getTestElements();
//        ArrayList<IssueElement> issueElements = this.testObject.getIssueElements();
//
//        //Loop though the testElements
//        for (TestElement testElement: testElements)
//        {
//            //Create the element using the UI and request the content
//            TestComponentElement testComponentElement = new TestComponentElement(this.event, testElement);
//            JPanel testElementUI = testComponentElement.requestElement();
//
//            //Create a container panel for each element
//            JPanel testElementUIBorder = new JPanel();
//
//            //Create a box for testElementUIBorder
//            testElementUIBorder.setLayout(new BoxLayout(testElementUIBorder, BoxLayout.Y_AXIS));
//
//            //Add the element to the Border
//            testElementUIBorder.add(testElementUI);
//
//            //Set maximum height for the testElementUIBorder
//            testElementUIBorder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//
//            //Add the testElementUIBorder to the
//            this.testComponentElementsPanel.add(testElementUIBorder);
//
//            //Add Vertical Space between Elements
//            this.testComponentElementsPanel.add(Box.createVerticalStrut(5));
//
//        }
//
//        //Loop through the IssueElements
//        for (IssueElement issueElement: issueElements)
//        {
//            //Create the UI Element
//            IssuesComponentElement issuesComponentElement = new IssuesComponentElement(issueElement, this.event);
//            JPanel issueElementUI = issuesComponentElement.requestElement();
//
//            //Create a container panel for each element
//            JPanel issueElementUIBorder = new JPanel();
//
//            //Create a box for testElementUIBorder
//            issueElementUIBorder.setLayout(new BoxLayout(issueElementUIBorder, BoxLayout.Y_AXIS));
//
//            //Add the element to the Border
//            issueElementUIBorder.add(issueElementUI);
//
//            //Set maximum height for the testElementUIBorder
//            issueElementUIBorder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//
//            //Add it to the issueComponentPanel
//            this.issuesElementPanel.add(issueElementUIBorder);
//
//            //Add Vertical Space between Elements
//            this.issuesElementPanel.add(Box.createVerticalStrut(5));
//        }

        //Set Up margins for elements
        this.testComponentElementsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.issuesElementPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


    }

    private void formButtonClicked(int index)
    {
//        //Update Event
//        this.event.setFormButtonPressed(index);
    }

    private void fillIssuesPanel()
    {
        this.issuesPanel = new JPanel();
        this.issuesPanel.setLayout(new BoxLayout(this.testComponentPanel, BoxLayout.Y_AXIS));

//        /* Add the Issue Elements */
//        //Collect the array List of TestElements form the test Object
//        ArrayList<IssueElement> issueElements = this.testObject.getIssueElements();
//
//        //Loop though all the elements
//        for (int i = 0; i < issueElements.size(); i++)
//        {
//            //Create the elements
//            IssuesComponentElement issuesComponentElement = new IssuesComponentElement(issueElements.get(i), this.event);
//
//            //Collect the JPanel element
//            JPanel issueElementPanel = issuesComponentElement.requestElement();
//
//            // Create a container panel for each element
//            JPanel issuesContainerPanel = new JPanel();
//            issuesContainerPanel.setLayout(new BoxLayout(issuesContainerPanel, BoxLayout.Y_AXIS));
//
//            // Add the element panel to the container panel
//            issuesContainerPanel.add(issueElementPanel);
//
//            // Set the maximum height for the container panel
//            issuesContainerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//
//            // Add the container panel to the history panel
//            this.issuesPanel.add(issuesContainerPanel);
//
//            // Add vertical spacing between elements
//            if (i < issueElements.size() - 1)
//            {
//                this.issuesPanel.add(Box.createVerticalStrut(5));
//            }
//
//            this.issuesPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//        }
    }

    private void fillTestComponentPanel() {
//        // Create the JPanel Object
//        this.testComponentPanel = new JPanel();
//
//        // Set Up the Border Properties
//        this.testComponentPanel.setLayout(new BoxLayout(this.testComponentPanel, BoxLayout.Y_AXIS));
//
//
//
//        //Collect the array List of TestElements form the test Object
//        ArrayList<TestElement> testElements = this.testObject.getTestElements();
//
//        //Loop though all the elements
//        for (int i = 0; i < testElements.size(); i++)
//        {
//            //Create the elements
//            TestComponentElement testComponentElement = new TestComponentElement(this.event, testElements.get(i));
//
//            //Collect the JPanel element
//            JPanel testElementPanel = testComponentElement.requestElement();
//
//            // Create a container panel for each element
//            JPanel testsContainerPanel = new JPanel();
//            testsContainerPanel.setLayout(new BoxLayout(testsContainerPanel, BoxLayout.Y_AXIS));
//
//            // Add the element panel to the container panel
//            testsContainerPanel.add(testElementPanel);
//
//            // Set the maximum height for the container panel
//            testsContainerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//
//            // Add the container panel to the history panel
//            this.testComponentPanel.add(testsContainerPanel);
//
//            // Add vertical spacing between elements
//            if (i < testElements.size() - 1) {
//                this.testComponentPanel.add(Box.createVerticalStrut(5));
//            }
//
//        }
//
//        //setUp a margin for elements
//        this.testComponentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//
    }
}
