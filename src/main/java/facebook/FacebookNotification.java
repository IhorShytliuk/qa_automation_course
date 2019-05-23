package facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FacebookNotification extends FBBase{

    @Test
    public void testNotification() {
        By notifications = By.xpath("//*[@name='notifications']");
        By firstNotification = By.xpath("//li[1]//ul/li[1]//div[1]/div/span");

        click(notifications);

        String firstNotifText = driver.findElement(firstNotification).getText();
        System.out.println(firstNotifText);

    }

    @Test
    public void zeorpo(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }


}
