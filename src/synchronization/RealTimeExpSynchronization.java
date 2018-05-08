package synchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RealTimeExpSynchronization {

    private static WebDriver wd;
    private static WebDriverWait wait;


    public static void main(String args[]){

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        if (RealTimeExpSynchronization.visibilityOfElement(10,
                wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")))) {
            wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")).sendKeys("Kolkata");
            RealTimeExpSynchronization.Wait(3);
        }

        if(RealTimeExpSynchronization.presenceOfElement_ByXpath(10,
                "(//input[contains(@id,'originStation1')])[1]")) {
            wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")).sendKeys("Delhi");
        }
    }

    private static void Wait(int timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void implicitwait(int timeInSecond){
        wd.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
    }

    private static boolean visibilityOfElement(int timeInsecond,WebElement we){
        wait = new WebDriverWait(wd,timeInsecond);
        try {
            wait.until(ExpectedConditions.visibilityOf(we));
            wait = null;
            return true;
        } catch(Exception e){
            System.out.println("Object is not visible");
            wait = null;
            return false;
        }

    }

    private static boolean clickableElement(int timeInsecond,WebElement we){
        wait = new WebDriverWait(wd,timeInsecond);
        try{
            wait.until(ExpectedConditions.elementToBeClickable(we));
            wait = null;
            return true;
        } catch(Exception e){
            System.out.println("Object is not clickable");
            wait = null;
            return false;
        }
    }

    private static boolean presenceOfElement_ByXpath(int timeInsecond,String xPathExp){
        wait = new WebDriverWait(wd,timeInsecond);
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathExp)));
            wait = null;
            return true;
        } catch(Exception e){
            System.out.println("Object is not present in the application");
            wait = null;
            return false;
        }
    }



}
