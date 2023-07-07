/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package GUI;

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
    private JTable statusTable;


    JFrame frame;
    public MainUI()
    {
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
        // Create the panel to display the web browser
        JPanel webPanel = new JPanel(new BorderLayout());


        this.frame.add(this.innerPanel);

    }






    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
