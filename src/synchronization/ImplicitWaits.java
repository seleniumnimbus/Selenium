package synchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying
 * to find an element or elements if they are not immediately available.
 * The default setting is 0.
 * Once set, the implicit wait is set for the life of the WebDriver object instance.
 */

public class ImplicitWaits {

    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        WebDriver wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")).sendKeys("Kolkata");

        Thread.sleep(3000);
        wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")).sendKeys("Delhi");

    }

}
