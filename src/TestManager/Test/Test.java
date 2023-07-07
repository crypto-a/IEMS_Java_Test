package TestManager.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import TestManager.UserInterfaceScenarioTester.UserInterfaceScenarioTester;

public class Test
{
    private int testAccuracy;
    private String url;

    public Test(int Accuracy, String url)
    {
        //Setup Properties
        this.testAccuracy = Accuracy;
        this.url = url;

    }

    public void UITest()
    {
        /* Scenario Tab Test */
        UserInterfaceScenarioTester scenarioTester = new UserInterfaceScenarioTester(this.url);
    }
}
