package GUI;

import GUI.Event.Event;
import GUI.MainPage.MainPage;
import GUI.UserAuthenticationPage.UserAuthenticationPage;
import User.User;
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
    private final int[] frameSize = {1200, 800};

    private final User user;

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
    }

    public void updateMainPage()
    {

        //Cech to see witch page we are on
        switch (this.event.requestCurrentPage())
        {
            case "SignInPage":
                //Clear the frame
                this.frame.getContentPane().removeAll();

                //Create the GUI object
                UserAuthenticationPage userAuthenticationPage = new UserAuthenticationPage(this, this.event, this.user);

                //Add the GUI content to the page
                this.frame.add(userAuthenticationPage.requestPage());

                System.out.println("UI Updated");

                //Set visibility to true
                this.frame.setVisible(true);

                break;
            default:
                //Clear the frame
                this.frame.getContentPane().removeAll();

                //Create the new GUI Object
                MainPage mainPage = new MainPage(this.testEngine, this.event, this.user);
                //Add the GUI content to the page
                this.frame.add(mainPage.requestPage());

                System.out.println("UI Updated");

                //Set visibility to true
                this.frame.setVisible(true);
        }

    }
}
