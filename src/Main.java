import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import TestManager.Test.Test;

public class Main
{
    public static void main(String[] args)
    {

        Test test = new Test(50, "http://ec2-54-210-75-155.compute-1.amazonaws.com:3452/");

        test.UITest();




    }
}
