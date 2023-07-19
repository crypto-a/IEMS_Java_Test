package GUI.MainPage.MainContent;

import GUI.Event.Event;
import GUI.MainPage.OldTestElement.OldTestElement;
import TestEngine.Test.Test;
import TestEngine.TestEngine;

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

    public MainContent(TestEngine testEngine, Event event)
    {
        //SetUp object Properties
        this.testEngine = testEngine;
        this.event = event;
    }

    public JPanel requestContent()
    {
        return this.MainPanel;
    }

    private void createUIComponents()
    {
        this.historyPanel = new JPanel();
        this.historyPanel.setLayout(new BoxLayout(this.historyPanel, BoxLayout.Y_AXIS));

        ArrayList<Test> testsList = this.testEngine.getTestArrayList();

        /* Fill the History page with elements */
        for (int i = 0; i < testsList.size(); i++) {
            OldTestElement oldTestElement = new OldTestElement(testsList.get(i), this.event);
            JPanel elementPanel = oldTestElement.getElementPanel();

            // Create a container panel for each element
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

            // Add the element panel to the container panel
            containerPanel.add(elementPanel);

            // Set the maximum height for the container panel
            containerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            // Add the container panel to the history panel
            this.historyPanel.add(containerPanel);

            // Add vertical spacing between elements
            if (i < testsList.size() - 1) {
                this.historyPanel.add(Box.createVerticalStrut(5));
            }
        }

        // Add margin to the historyPanel
        historyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}
