package lowLevelAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnterValueToTextBox {

    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        WebDriver wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        Thread.sleep(3000);
        wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")).sendKeys("Kolkata");

        Thread.sleep(3000);
        wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")).sendKeys("Delhi");



    }

}
