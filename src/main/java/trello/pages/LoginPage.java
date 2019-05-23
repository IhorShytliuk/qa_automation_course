package trello.pages;

import org.openqa.selenium.By;

import static trello2.core.BrowserFactory.driver;

public class LoginPage extends BasePage {

    private By emailBy = By.xpath("//input[@name='user']");
    private By pswdBy = By.xpath("//input[@name='password']");
    private By submitBy = By.xpath("//input[@id='login']");

    public void open() {
        driver.get("https://trello.com/login");
    }

    public void login(String email, String pswd) {
        typeText(emailBy, email);
        typeText(pswdBy, pswd);
        click(submitBy);
    }

    public boolean isLoggedOut() {
        return driver.getCurrentUrl().contains("logged-out");
    }

}
