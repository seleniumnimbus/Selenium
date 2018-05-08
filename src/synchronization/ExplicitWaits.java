package synchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaits {

    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        WebDriver wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(wd,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[contains(@id,'originStation1')])[1]")));
        wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")).sendKeys("Kolkata");

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]"))));
        wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")).sendKeys("Delhi");


    }
}
