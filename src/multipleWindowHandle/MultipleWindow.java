package multipleWindowHandle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultipleWindow {

    private static WebDriver wd;
    private static WebDriverWait wait;

    public static void main(String args[]){

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        /**
         * Create Chrome profile to stop notification
         */
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        wd = new ChromeDriver(options);
        wd.get("https://www.online.citibank.co.in/");
        wd.manage().window().maximize();
        MultipleWindow.Wait(2);

        /**
         * close notification popup
         */
        if(MultipleWindow.presenceOfElement_ByXpath(2,"//iframe[contains(@id,'notification-template')]")){
            wd.switchTo().frame(wd.findElement(By.xpath("//iframe[contains(@id,'notification-template')]")));
            if(MultipleWindow.presenceOfElement_ByXpath(2,"//div[contains(@id,'close')]")){
                wd.findElement(By.xpath("//div[contains(@id,'close')]")).click();
                MultipleWindow.Wait(2);
            }
            wd.switchTo().defaultContent();
        }

        /**
         * Click on Login button
         */
        if(MultipleWindow.presenceOfElement_ByXpath(2,"//a[@title='Login']")){
            wd.findElement(By.xpath("//a[@title='Login']")).click();
            MultipleWindow.Wait(3);
        }

        /**
         * get id for all open windows and switch to new open window
         */
        String parentWindowID = wd.getWindowHandle();
        Set<String> allwindowIDS = wd.getWindowHandles();

        for(String wid : allwindowIDS){
            if(! wid.equalsIgnoreCase(parentWindowID)){
                wd.switchTo().window(wid);
            }
        }

        /**
         * Enter user id and pwd
         */
        if(MultipleWindow.presenceOfElement_ByXpath(30,"//input[@id='User_Id']")){
            if(MultipleWindow.clickableElement(10,wd.findElement(By.xpath("//input[@id='User_Id']")))) {
                wd.findElement(By.xpath("//input[@id='User_Id']")).sendKeys("UserName");
            }
        }
        if(MultipleWindow.clickableElement(2,wd.findElement(By.xpath("//img[@title=" +
                "'Enter Your Password using standard keyboard']")))){
            wd.findElement(By.xpath("//img[@title='Enter Your Password using standard keyboard']")).click();
            MultipleWindow.Wait(2);
            wd.findElement(By.xpath("//div[@id='withoutKeyboard']//input[@id='password']")).sendKeys("Password");
        }

        /**
         * switch driver to parent window
         */
        wd.switchTo().window(parentWindowID);



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
