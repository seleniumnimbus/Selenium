package lowLevelAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ClickOnButton {

    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        WebDriver wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        Thread.sleep(3000);
        wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")).sendKeys("Kolkata");

        Thread.sleep(3000);
        wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")).sendKeys("Delhi");

        Thread.sleep(3000);
        wd.findElement(By.xpath("//label[text()='Round Trip']/parent::td/input")).click();

        Thread.sleep(3000);
        Select noOfAdultPassenger = new Select(wd.findElement(By.xpath("//label[text()='Adult']/ancestor::*[@id='adultDropdown']/select")));
        noOfAdultPassenger.selectByVisibleText("3");

        Thread.sleep(3000);
        Select currencyType = new Select(wd.findElement(By.xpath("//label[text()='Currency']/parent::*/following-sibling::select")));
        currencyType.selectByValue("INR");

        Thread.sleep(3000);
        wd.findElement(By.xpath("//label[text()='Unaccompanied Minor']/preceding-sibling::input")).click();

        Thread.sleep(3000);
        wd.findElement(By.xpath("//input[contains(@id,'btn_FindFlights')]")).click();

        Thread.sleep(3000);

    }
}
