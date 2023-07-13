package GUI.MainPage;

import GUI.Event.Event;
import GUI.MainPage.OldTestElement.OldTestElement;
import TestManager.Test.Test;
import TestManager.TestManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JTabbedPane tabbedPane1;
    private JProgressBar progressBar1;
    private JButton button5;
    private JPanel testHistoryPanel;
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
        this.historyPanel = new JPanel();
        this.historyPanel.setLayout(new BoxLayout(this.historyPanel, BoxLayout.Y_AXIS));

        ArrayList<Test> testsList = this.testManager.getTestArrayList();

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
