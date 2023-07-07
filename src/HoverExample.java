import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HoverExample {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Surface Pro 6\\Documents\\School\\ICS4U\\IEMS_Java_Test\\src\\TestManager\\ChromeDriver\\chromedriver.exe");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // Open the webpage
        driver.get("http://ec2-54-210-75-155.compute-1.amazonaws.com:3454/#/scenario");

        // Find button one
        WebElement buttonOne = driver.findElement(By.cssSelector("div[aria-haspopup=\"listbox\"]"));

        System.out.println("done");




        // Close the browser
        driver.quit();
    }
}
