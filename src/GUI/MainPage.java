package GUI;

import GUI.Event.Event;
import TestManager.TestManager;

import javax.swing.*;

public class MainPage
{
    /* Object Properties */
    private final Event event;
    private final TestManager testManager;
    private JPanel mainPanel;


    public MainPage(TestManager testManager, Event event)
    {
        //SetUp object Properties
        this.testManager = testManager;
        this.event = event;


    }

    public JPanel requestPage()
    {
        //Return the mainPanel Property
        return this.mainPanel;
    }
}
