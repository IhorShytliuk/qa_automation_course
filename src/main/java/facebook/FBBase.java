package facebook;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FBBase extends BaseTest {

    @Override
    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver74.exe");
        String url = "https://www.facebook.com/";

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(7000, TimeUnit.SECONDS);
        driver.get(url);

        By loginInput = By.xpath("//input[@name='email']");
        By passwordInput = By.xpath("//input[@name='pass']");
        By submitBtn = By.xpath("//input[@type='submit']");

//        Scanner in = new Scanner(System.in);
        System.out.println("Email: ");
        String email = "yaknuzamli@desoz.com"; //in.next();

        System.out.println("Password: ");
        String pswd = "qwe1rty2"; //in.next();


        typeText(loginInput, email);
        typeText(passwordInput, pswd);
        click(submitBtn);
    }
}
