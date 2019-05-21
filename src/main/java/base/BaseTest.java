package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver74.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public WebDriver initDriver() {
        beforeTest();
        return driver;
    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    protected static String getTextBy(By by) {
        return driver.findElement(by).getText();
    }

    protected void click(By by) {
        driver.findElement(by).click();
    }
}
