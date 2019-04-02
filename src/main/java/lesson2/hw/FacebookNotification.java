package lesson2.hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FacebookNotification {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver.exe");
        String url = "https://www.facebook.com/";

        By loginInput = By.xpath("//input[@name='email']");
        By passwordInput = By.xpath("//input[@name='pass']");
        By submitBtn = By.xpath("//input[@type='submit']");

        By notifications = By.xpath("//*[@name='notifications']");
        By firstNotification = By.xpath("//li[1]//ul/li[1]//div[1]/div/span");

        Scanner in = new Scanner(System.in);

        System.out.println("Email: ");
        String email = in.next();

        System.out.println("Password: ");
        String pswd = in.next();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(7000, TimeUnit.SECONDS);
        driver.get(url);

        type(loginInput, email);
        type(passwordInput, pswd);
        click(submitBtn);
        click(notifications);

        String firstNotifText = driver.findElement(firstNotification).getText();
        System.out.println(firstNotifText);

        driver.quit();
    }

    private static void click(By by) {
        driver.findElement(by).click();
    }

    private static void type(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }
}
