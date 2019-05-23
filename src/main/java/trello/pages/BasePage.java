package trello.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static trello2.core.BrowserFactory.driver;

public class BasePage {
    protected void typeText(By by, String text) {
        driver.findElement(by).clear();;
        driver.findElement(by).sendKeys(text);
    }

    protected void click(By by) {
        driver.findElement(by).click();
    }

    protected boolean isElementPresent(By by) {
        try {
            waitForElementPresent(by);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
