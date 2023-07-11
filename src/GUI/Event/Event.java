/*****************************************
 * Program Name: Main
 * Programmer Name: Ali Rahbar
 * Program Date: July 7, 2023
 * Program Description: This program runs the script
 * Inputs: None
 * Outputs: None
 ******************************************/

package GUI.Event;


import graphql.org.antlr.v4.runtime.misc.ObjectEqualityComparator;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public class Event
{
    //Object properties
    private WebDriver driver;

    private Object[][] data = {};

    public Event()
    {

    }

    public Object[][] dataRead()
    {
        return this.data;
    }

    public void dataWrite (String[] newRow)
    {
        System.out.println(Arrays.toString(newRow));
        this.data = Arrays.copyOf(this.data, data.length + 1);
        this.data[data.length - 1] = newRow;
    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver readDriver()
    {
        return this.driver;
    }

}
