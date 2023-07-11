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
import TestManager.TestManager;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame
{
    private Event event;
    private String title;

    int[] frameSize;

    private JFrame frame;

    private TestManager testManager;


    public GUI(String title, int[] frameSize, Event event, TestManager testManager) {
        //Set the values to the properties
        this.title = title;
        this.frameSize = frameSize;
        this.event = event;
        this.testManager = testManager;

        // Initializes the frame and puts "Window Name" in the top bar
        this.frame = new JFrame(this.title);

        // Makes the frame the maximum size on repl.it
        this.frame.setSize(this.frameSize[0], this.frameSize[1]);

        //Set display to the center of the screen
        frame.setLocationRelativeTo(null);

        // Makes the background of the frame grey
        this.frame.setLayout(new BorderLayout());
    }

}
