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


public class GUI
{
    private final Event event;
    private final MainUI ui;
    public GUI(Event event)
    {
        //SetUp object property
        this.event = event;

        //Open the GUI
        this.ui = new MainUI(this.event);
        this.ui.updateUI();


    }

    public void updateUI()
    {
        this.ui.updateUI();
    }
}
