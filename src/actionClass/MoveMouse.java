package actionClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoveMouse {

    private static WebDriver wd;
    private static WebDriverWait wait;

    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        Actions action = new Actions(wd);

        /**
         * Move Mouse to Login / Signup
         */
        if (MoveMouse.presenceOfElement_ByXpath(10,"//a[contains(@id,'Login') and text()='Login / Signup']")) {
            action.moveToElement(wd.findElement(By.xpath("//a[contains(@id,'Login') and text()='Login / Signup']"))).build().perform();
            MoveMouse.Wait(2);
        }
        /**
         * Move Mouse to SpiceCash/SpiceClub Members
         */
        if(MoveMouse.presenceOfElement_ByXpath(10,"//a[contains(@id,'Login') and " +
                "text()='Login / Signup']/following-sibling::ul//a[text()='SpiceCash/SpiceClub Members']")) {
            action.moveToElement(wd.findElement(By.xpath("//a[contains(@id,'Login') " +
                    "and text()='Login / Signup']/following-sibling::ul//a[text()='SpiceCash/SpiceClub Members']"))).build().perform();
            MoveMouse.Wait(2);
        }
        /**
         * Move Mouse to Member Login
         */
        if(MoveMouse.presenceOfElement_ByXpath(10,"//a[contains(@id,'Login') and " +
                "text()='Login / Signup']/following-sibling::ul//a[text()='SpiceCash/SpiceClub Members']" +
                "/following-sibling::ul//a[text()='Member Login']")) {
            action.moveToElement(wd.findElement(By.xpath("//a[contains(@id,'Login') and " +
                    "text()='Login / Signup']/following-sibling::ul//a[text()='SpiceCash/SpiceClub Members']" +
                    "/following-sibling::ul//a[text()='Member Login']"))).build().perform();
            MoveMouse.Wait(2);
        }
        /**
         * Click to Member Login
         */
        if(MoveMouse.clickableElement(2,wd.findElement(By.xpath("//a[contains(@id,'Login') and " +
                "text()='Login / Signup']/following-sibling::ul//a[text()='SpiceCash/SpiceClub Members']" +
                "/following-sibling::ul//a[text()='Member Login']")))) {
            wd.findElement(By.xpath("//a[contains(@id,'Login') and text()='Login / Signup']/following-sibling::ul" +
                    "//a[text()='SpiceCash/SpiceClub Members']/following-sibling::ul//a[text()='Member Login']")).click();
        }
        /**
         * Enter user name and password
         */
        if (MoveMouse.presenceOfElement_ByXpath(10,"//input[contains(@id,'UserID')]") &&
        MoveMouse.clickableElement(10,wd.findElement(By.xpath("//input[contains(@id,'UserID')]")))) {
            wd.findElement(By.xpath("//input[contains(@id,'UserID')]")).sendKeys("UserName");
            wd.findElement(By.xpath("//input[contains(@id,'Password')]")).sendKeys("Password");
        }
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

    private static void Wait(int timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
