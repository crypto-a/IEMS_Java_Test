package GUI;

import GUI.Event.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import GUI.MainPage.MainPage;
import GUI.UserAuthenticationPage.UserAuthenticationPage;
import TestEngine.TestEngine;
import User.User;

public class GUI extends JFrame
{
    /* Object Properties */
    private final Event event;
    private final User user;
    private final TestEngine testEngine;
    private final JFrame frame;
    private final String tittle = "IEMS TEST SOFTWARE";
    private final int[] frameSize = {1200, 800};

    public GUI(Event event, TestEngine testEngine, User user)
    {
        //SetUp Object Properties
        this.user = user;
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

        //set window listiner
        // Add a WindowAdapter with a lambda expression to the JFrame
        this.frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                endCode();

            }
        });
    }

    public void updateMainPage()
    {

        //Cech to see witch page we are on
        switch (this.event.getCodeState())
        {
            case 0:
                //Clear the frame
                this.frame.getContentPane().removeAll();

                //Create the GUI object
                UserAuthenticationPage userAuthenticationPage = new UserAuthenticationPage( this.event, this.user);

                //Add the GUI content to the page
                this.frame.add(userAuthenticationPage.getMainPanel());

                System.out.println("UI Updated");

                //Set visibility to true
                this.frame.setVisible(true);

                break;
            default:
                //Clear the frame
                this.frame.getContentPane().removeAll();

                //Create the new GUI Object
                MainPage mainPage = new MainPage(this, this.event, this.user);
                //Add the GUI content to the page
                this.frame.add(mainPage.getMainPanel());

                System.out.println("UI Updated");

                //Set visibility to true
                this.frame.setVisible(true);

                break;
        }

    }

    private void endCode()
    {
        //disconnect the database
        this.event.disconnectDatabase();

        //clear test engine data
        this.testEngine.clearData();

        //Close the JFrame
        dispose();

        //Exit code
        System.exit(0);
    }
}
