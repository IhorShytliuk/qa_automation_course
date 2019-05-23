package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    protected String getTextBy(By by) {
        return driver.findElement(by).getText();
    }

    protected void click(By by) {
        waitForElementPresent(by);
        driver.findElement(by).click();
    }

    protected static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected static boolean isElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected static boolean isElementDisapeared(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(by)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    protected void typeText(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


}
