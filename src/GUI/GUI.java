package GUI;

import GUI.Event.Event;
import TestManager.TestManager;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    /* Object Properties */
    private final Event event;
    private final TestManager testManager;
    private final JFrame frame;
    private final String tittle = "IEMS TEST SOFTWARE";
    private final int[] frameSize = {800, 600};

    public GUI(Event event, TestManager testManager)
    {
        //SetUp Object Properties
        this.event = event;
        this.testManager = testManager;

        //SetUp main Frame
        this.frame = new JFrame(this.tittle);

        // Makes the frame the maximum size on repl.it
        this.frame.setSize(this.frameSize[0], this.frameSize[1]);

        //Set display to the center of the screen
        frame.setLocationRelativeTo(null);

        // Makes the background of the frame grey
        this.frame.setLayout(new BorderLayout());
    }

    public void updateMainPage()
    {
        //Create the new GUI Object
        MainPage mainPage = new MainPage(this.testManager, this.event);

        //Clear the frame
        this.frame.getContentPane().removeAll();

        //Add the GUI content to the page
        this.frame.add(mainPage.requestPage());

        //Set visibility to true
        this.frame.setVisible(true);
    }
}
