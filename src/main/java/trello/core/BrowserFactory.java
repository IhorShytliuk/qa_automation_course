package trello2.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BrowserFactory {
    public static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver74.exe");
        driver = new ChromeDriver();
//        driver = new EventFiringWebDriver(driver).
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
