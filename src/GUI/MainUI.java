/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package GUI;

import GUI.Event.Event;

import javax.swing.*;
import java.awt.*;

public class MainUI
{
    //Properties of the UI
    private JPanel innerPanel;
    private JPanel contentPanel;
    private JButton newTestButton;
    private JButton historyButton;
    private JProgressBar progressBar1;
    private JTable testLogs;
    private String[] columnNames = {"Test Code", "Status"};

    // Data for the table (2D array)
    private final Event event;



    JFrame frame;
    public MainUI(Event event)
    {
        //Set up the object properties
        this.event = event;
        //Create the frame Object
        this.frame = new JFrame();

        // Set the title of the JFrame
        this.frame.setTitle("My JFrame");

        // Define the close operation
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the JFrame
        this.frame.setSize(800, 600);

        // Center the JFrame on the screen
        this.frame.setLocationRelativeTo(null);

        // Make the JFrame visible
        this.frame.setVisible(true);
    }

    public void updateUI()
    {
        //Update table
        this.createUIComponents();

        //Clear the main page
        this.frame.getContentPane().removeAll();

        //Show updated content on the page
        this.frame.add(this.innerPanel);

    }






    private void createUIComponents()
    {
        // TODO: place custom component creation code here

        //Don't allow user changes on the Table


        this.testLogs = new JTable(this.event.dataRead(), this.columnNames);

        this.testLogs.setRowSelectionAllowed(false);

    }
}
