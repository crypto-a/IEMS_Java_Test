package GUI;

import GUI.Event.Event;
import GUI.MainPage.MainPage;
import TestEngine.TestEngine;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    /* Object Properties */
    private final Event event;
    private final TestEngine testEngine;
    private final JFrame frame;
    private final String tittle = "IEMS TEST SOFTWARE";
    private final int[] frameSize = {800, 600};

    public GUI(Event event, TestEngine testEngine)
    {
        //SetUp Object Properties
        this.event = event;
        this.testEngine = testEngine;

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


        //Clear the frame
        this.frame.getContentPane().removeAll();

        //Create the new GUI Object
        MainPage mainPage = new MainPage(this.testEngine, this.event);
        //Add the GUI content to the page
        this.frame.add(mainPage.requestPage());

        System.out.println("UI Updated");

        //Set visibility to true
        this.frame.setVisible(true);
    }
}
