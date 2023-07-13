package GUI;

import GUI.Event.Event;
import GUI.MainPage.MainPage;
import GUI.MainPage.OldTestElement.OldTestElement;
import GUI.NewTestPage.NewTestPage;
import GUI.OldTestPage.OldTestPage;
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


        //Clear the frame
        this.frame.getContentPane().removeAll();

        switch (this.event.requestCurrentPage())
        {
            case "mainPage":
                //Create the new GUI Object
                MainPage mainPage = new MainPage(this.testManager, this.event);
                //Add the GUI content to the page
                this.frame.add(mainPage.requestPage());
                break;

            case "addTestPage":
                //Create the new GUI Object
                NewTestPage newTestPage = new NewTestPage(this.testManager, this.event);
                //Add the GUI content to the page
                this.frame.add(newTestPage.requestPage());
                break;

            case "oldTestDisplay":
                //Create the new GUI Object
                OldTestPage oldTestPage = new OldTestPage(this.event);
                //Add the GUI content to the page
                this.frame.add(oldTestPage.requestPage());
                break;

        }

        System.out.println("UI Updated");

        //Set visibility to true
        this.frame.setVisible(true);
    }
}
