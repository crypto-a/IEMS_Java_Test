package GUI.MainPage.TestComponentPage;

import GUI.Event.Event;
import TestEngine.TestElement.TestElement;
import TestEngine.TestEngine;

import javax.swing.*;

public class TestComponentPage
{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JTree tree1;
    private JButton returnButton;
    private JList list2;
    private JLabel testID;
    private JLabel testStatus;
    private JLabel testErrors;
    private JPanel testLogs;
    private JPanel testDetails;
    private JPanel testInfo;
    private JLabel testScnario;

    private final Event event;
    private final TestEngine testEngine;
    private final TestElement testElement;

    public TestComponentPage(TestEngine testEngine, Event event)
    {
        //SetUp object properties
        this.event = event;
        this.testEngine = testEngine;

        //Collect the test element form event
        this.testElement = this.event.getSelectedTestElement();

        System.out.println(this.testElement.toString());

        //set Up test properties
        this.testID.setText(this.testElement.getTestID());
        this.testStatus.setText(this.testElement.getStatus());
        this.testErrors.setText(this.testElement.getErrorsNum());
        this.testScnario.setText(this.testElement.getScenario());
    }

    public JPanel requestContent()
    {
        //return the main panel
        return this.mainPanel;
    }

}
