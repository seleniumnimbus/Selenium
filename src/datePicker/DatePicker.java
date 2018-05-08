package datePicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;

public class DatePicker {

    private static WebDriver wd;
    private static WebDriverWait wait;

    public static void main(String args[]){

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

        if(DatePicker.clickableElement(10,wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")))){
            wd.findElement(By.xpath("(//input[contains(@id,'originStation1')])[1]")).sendKeys("Kolkata");
            DatePicker.Wait(2);
        }

        if(DatePicker.clickableElement(10,wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")))){
            wd.findElement(By.xpath("(//input[contains(@id,'destinationStation1')])[1]")).sendKeys("Delhi");
            DatePicker.Wait(2);
        }

        /**
         * Click on Round Trip button
         */
        if(DatePicker.presenceOfElement_ByXpath(10,"//label[text()='Round Trip']/parent::td/input")){
            wd.findElement(By.xpath("//label[text()='Round Trip']/parent::td/input")).click();
        }

        /**
         * Select Depart date
         */
        if(DatePicker.clickableElement(10,wd.findElement(By.xpath("//div[contains(@class,'picker-first')]" +
                "//input[contains(@id,'view_date1')]")))){
            wd.findElement(By.xpath("//div[contains(@class,'picker-first')]//input[contains(@id,'view_date1')]")).click();
            DatePicker.Wait(1);
            DatePicker.datePicker("15-June-2018");
        }

        /**
         * Select Return date
         */
        if(DatePicker.clickableElement(10,wd.findElement(By.xpath("//div[contains(@class,'picker-second')]" +
                "//input[contains(@id,'view_date2')]")))){
            wd.findElement(By.xpath("//div[contains(@class,'picker-second')]//input[contains(@id,'view_date2')]")).click();
            DatePicker.Wait(1);
            DatePicker.datePicker("15-July-2018");
        }





    }

    private static void datePicker(String date){

        String[] arrDate = date.split("-");
        String xpath_Cal = "//div[@id='ui-datepicker-div']/div[contains(@class,'ui-datepicker-group')]";

        if(DatePicker.presenceOfElement_ByXpath(10,"//div[@id='ui-datepicker-div']")){
            List<WebElement> cal = wd.findElements(By.xpath(xpath_Cal));
            for(int i = 0 ; i<cal.size(); i++){
                String runJourneyMonth = wd.findElement(By.xpath(xpath_Cal + "[" + (i+1) + "]" + "//span[@class='ui-datepicker-month']")).getText();
                String runJourneyYear = wd.findElement(By.xpath(xpath_Cal + "[" + (i+1) + "]" + "//span[@class='ui-datepicker-year']")).getText();
                if(runJourneyMonth.equalsIgnoreCase(arrDate[1]) && runJourneyYear.equalsIgnoreCase(arrDate[2])){
                    if(DatePicker.clickableElement(1,wd.findElement(By.xpath(xpath_Cal + "[" + (i+1) + "]" +
                            "//a[text()='" + arrDate[0] + "']")))){
                        wd.findElement(By.xpath(xpath_Cal + "[" + (i+1) + "]" + "//a[text()='" + arrDate[0] + "']")).click();
                        DatePicker.Wait(1);
                    } else {
                        System.out.println(arrDate[0] + " is not a valid active date");
                    }
                    return;
                }
            }
            wd.findElement(By.xpath("//a[@title='Next']")).click();
            DatePicker.Wait(1);
            wd.findElement(By.xpath("//a[@title='Next']")).click();
            DatePicker.Wait(1);
            datePicker(date);
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
