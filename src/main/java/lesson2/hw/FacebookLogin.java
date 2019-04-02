package lesson2.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class FacebookLogin {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver.exe");
        String url = "https://www.facebook.com/";
        String email = "vvin@i.ua";

        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        Console console = System.console();
//        char[] pswdArr = b.readPassword("Please type pswd here:");
        String pswd = new String(console.readPassword());

        By loginInput = By.xpath("//input[@name='email']");
        By passwordInput = By.xpath("//input[@name='pass']");
        By submitBtn = By.xpath("//input[@type='submit']");

        By notifications = By.xpath("//*[@name='notifications']");
        By firstNotification = By.xpath("//li//ul/li[1]//div[1]/div/span']");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.get(url);

        driver.findElement(loginInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(pswd);
        click(submitBtn);
        click(notifications);

        String firstNotifText = driver.findElement(firstNotification).getText();
        System.out.println(firstNotifText);

        driver.quit();
    }

    private static void click(By by) {
        driver.findElement(by).click();
    }
}
