package trello.pages;

import org.openqa.selenium.By;

public class HeaderPage extends BasePage {
    private By profileBtnBy = By.xpath("//a[@class='header-btn header-avatar js-open-header-member-menu']");
    private By logoutBtnBy = By.xpath("//a[@class='js-logout']");

    public void logout() {
        click(profileBtnBy);
        click(logoutBtnBy);
    }

    public boolean isHeaderPresent() {
        return isElementPresent(profileBtnBy);
    }

}
