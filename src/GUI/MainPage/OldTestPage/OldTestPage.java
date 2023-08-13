package GUI.MainPage.OldTestPage;

import GUI.Event.Event;
import GUI.MainPage.OldTestPage.IssuesComponentElement.IssuesComponentElement;
import GUI.MainPage.OldTestPage.TestComponentElement.TestComponentElement;
import TestEngine.IssueElement.IssueElement;
import TestEngine.TestElement.TestElement;
import TestEngine.TestObject.TestObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class OldTestPage
{
    private JButton returnButton;
    private JPanel mainPanel;
    private JList testLogs;
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
    private JScrollPane testLogsScrollPanel;
    private JTextArea textArea1;
    private JButton openWebsiteButton;
    private JScrollPane testElementScrollPanel;
    private JScrollPane issueElementScrollPanel;
    private final Event event;
    private final TestObject testObject;

    public OldTestPage(Event event)
    {
        //SetUp Object Properties
        this.event = event;
        this.testObject = this.event.getSelectedTestObject();

        //System.out.println(this.testObject.toString());

        this.testID.setText(this.testObject.getTestID());
        this.testIssuer.setText(this.event.getNameFromUserID(this.testObject.getIssuerID()));
        this.targetedWebPage.setText(this.testObject.getTargetedWebPage());
        this.testDate.setText(this.testObject.getTestDate());
        this.testTime.setText(this.testObject.getTestStartTime());
        this.testDuration.setText(this.testObject.getDuration());
        this.issuesFound.setText(this.testObject.getIssueNum() + " Issues found");


        //Set the selected tab and combobox
        this.comboBox1.setSelectedIndex(this.event.getOldTestPageTestComponentComboBoxSelected());
        this.comboBox2.setSelectedIndex(this.event.getOldTestPageIssuesComboBoxSelected());
        this.tabbedPane1.setSelectedIndex(this.event.getOldTestPagePanelSelected());

        //write the test description ot the text area
        this.textArea1.setText(this.testObject.getTestDescription());

        //Clear the test logs
        this.testLogs.setListData(this.event.getTestLogsArrayList());

        JScrollBar testLogsVerticalScrollBar = this.testLogsScrollPanel.getVerticalScrollBar();
        JScrollBar testElementsVerticalScrollBar = this.testElementScrollPanel.getVerticalScrollBar();
        JScrollBar issueElementsVerticalScrollBar = this.issueElementScrollPanel.getVerticalScrollBar();

        testLogsVerticalScrollBar.setValue(this.event.getSavedVerticalPositionTestLogs()[0]);
        testElementsVerticalScrollBar.setValue(this.event.getSavedVerticalPositionTestElements()[0]);
        issueElementsVerticalScrollBar.setValue(this.event.getSavedVerticalPositionIssueElements()[0]);

        //Set Up the action listeners
        this.returnButton.addActionListener(e -> formButtonClicked());
        this.comboBox1.addActionListener(e -> testElementComboBoxChange());
        this.comboBox2.addActionListener(e -> issueElementComboBoxChange());
        this.tabbedPane1.addChangeListener(e -> tabbedPanelChange());
        testLogsVerticalScrollBar.addAdjustmentListener(e -> this.event.setSavedVerticalPositionTestLogs(new int[]{testLogsVerticalScrollBar.getValue()}));
        testElementsVerticalScrollBar.addAdjustmentListener(e -> this.event.setSavedVerticalPositionTestElements(new int[]{testElementsVerticalScrollBar.getValue()}));
        issueElementsVerticalScrollBar.addAdjustmentListener(e -> this.event.setSavedVerticalPositionIssueElements(new int[]{issueElementsVerticalScrollBar.getValue()}));

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

        //Set Up a box Layout for both
        this.testComponentElementsPanel.setLayout(new BoxLayout(this.testComponentElementsPanel, BoxLayout.Y_AXIS));
        this.issuesElementPanel.setLayout(new BoxLayout(this.issuesElementPanel, BoxLayout.Y_AXIS));

        //Collect the two array lists to display them
        ArrayList<TestElement> testElements = this.event.getTestElementDisplayArrayList();
        ArrayList<IssueElement> issueElements = this.event.getIssueElementDisplayArrayList();

        //Loop though the testElements
        for (TestElement testElement: testElements)
        {
            //Create the element using the UI and request the content
            TestComponentElement testComponentElement = new TestComponentElement(this.event, testElement);
            JPanel testElementUI = testComponentElement.requestElement();

            //Create a container panel for each element
            JPanel testElementUIBorder = new JPanel();

            //Create a box for testElementUIBorder
            testElementUIBorder.setLayout(new BoxLayout(testElementUIBorder, BoxLayout.Y_AXIS));

            //Add the element to the Border
            testElementUIBorder.add(testElementUI);

            //Set maximum height for the testElementUIBorder
            testElementUIBorder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            //Add the testElementUIBorder to the
            this.testComponentElementsPanel.add(testElementUIBorder);

            //Add Vertical Space between Elements
            this.testComponentElementsPanel.add(Box.createVerticalStrut(5));

        }

        //Loop through the IssueElements
        for (IssueElement issueElement: issueElements)
        {
            //Create the UI Element
            IssuesComponentElement issuesComponentElement = new IssuesComponentElement(issueElement, this.event);
            JPanel issueElementUI = issuesComponentElement.requestElement();

            //Create a container panel for each element
            JPanel issueElementUIBorder = new JPanel();

            //Create a box for testElementUIBorder
            issueElementUIBorder.setLayout(new BoxLayout(issueElementUIBorder, BoxLayout.Y_AXIS));

            //Add the element to the Border
            issueElementUIBorder.add(issueElementUI);

            //Set maximum height for the testElementUIBorder
            issueElementUIBorder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            //Add it to the issueComponentPanel
            this.issuesElementPanel.add(issueElementUIBorder);

            //Add Vertical Space between Elements
            this.issuesElementPanel.add(Box.createVerticalStrut(5));
        }

        //Set Up margins for elements
        this.testComponentElementsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.issuesElementPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    private void formButtonClicked()
    {
        //Reset the states
        this.event.resetOldTestPageElement();

        //reset the scroll panel values
        this.event.setSavedVerticalPositionIssueElements(new int[]{0});
        this.event.setSavedVerticalPositionTestLogs(new int[]{0});
        this.event.setSavedVerticalPositionTestElements(new int[]{0});

        //Update Event
        this.event.setCodeState(1);
    }

    private void testElementComboBoxChange()
    {
        //Change the display state in the event object
        this.event.setTestElementShowCode(this.comboBox1.getSelectedIndex());

        //change the selected event object
        this.event.setOldTestPageTestComponentComboBoxSelected(this.comboBox1.getSelectedIndex());

        //Update the page again
        this.event.requestPageRefresh();
    }

    private void issueElementComboBoxChange()
    {
        //Change the display state in the event object
        this.event.setIssueElementShowCode(this.comboBox2.getSelectedIndex());

        //change the selected event object
        this.event.setOldTestPageIssuesComboBoxSelected(this.comboBox2.getSelectedIndex());

        //Update the page again
        this.event.requestPageRefresh();
    }

    private void tabbedPanelChange()
    {
        //Record the change
        this.event.setOldTestPagePanelSelected(this.tabbedPane1.getSelectedIndex());
    }


}
