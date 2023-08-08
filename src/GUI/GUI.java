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
                // Perform cleanup or termination actions here
                // For example, close database connections, save data, etc.

                //ToDo
                // Terminate the application gracefully
                System.exit(0);
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

    public void loadingDialog()
    {
        // Create a custom JDialog to act as the non-closable JOptionPane
        JDialog dialog = new JDialog((JFrame) null, "Please wait...", true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent closing by clicking the close button
        dialog.setSize(200, 100);

        // Create a panel with custom background color
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(240, 240, 240)); // Light gray background color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Create a label with custom font and alignment
        JLabel label = new JLabel("Loading...");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        dialog.add(panel);

        // Show the dialog in the center of the screen
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // Simulate data loading process (e.g., fetching data from a database)
        // In this example, we use a timer to represent the loading process.
        Timer timer = new Timer(3000, e -> {
            // Data loading is completed, close the non-closable dialog
            dialog.dispose();
        });
    }
}
