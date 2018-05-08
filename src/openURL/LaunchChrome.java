package openURL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchChrome {

    public static void main(String args[]){

        System.setProperty("webdriver.chrome.driver","./lib/driver/chromedriver");
        WebDriver wd = new ChromeDriver();
        wd.get("http://www.spicejet.com/");
        wd.manage().window().maximize();

    }

}
