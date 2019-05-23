package hw;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class KismiaTest extends BaseTest {
    String email = "cegnijetra@desoz.com";
    String pswd = "qwe1rty2";

    private By emailBy = By.xpath("//input[@name='email']");
    private By passwordBy = By.xpath("//input[@name='password']");
    private By submitBtnBy = By.xpath("//a[@class='home-page-form__submit js_submit']");

    private void login(String email, String pswd) {
        driver.get("https://kismia.com/");
        typeText(emailBy, email);
        typeText(passwordBy, pswd);
        click(submitBtnBy);
    }

    private By profilePhotoBy = By.xpath("//div[@class='new-header__photo']");
    private By profileSettingsBy = By.xpath("//a[@href='/profile/settings']");
    private By profileSettingsPersonalBy = By.xpath("//div[@data-tab='profile']");
    private By confirmDialogBy = By.xpath("//div[@class='setting-warning__btn-ctn']/a");

    private By saveBtnBy = By.xpath("//button[@class='button-FR settings__button button--flat-green js_save']");

    private void changeGender(Gender gender) {
        click(profilePhotoBy);
        click(profileSettingsBy);
        click(profileSettingsPersonalBy);
        click(By.xpath("//input[@name='gender'][@value='" + gender.value + "']/parent::li"));
        click(confirmDialogBy);
        click(saveBtnBy);
    }

    private enum Gender {
        MALE('m'), FEMALE('f');

        private final char value;
        Gender(char value) {
            this.value = value;
        }
    }

    private By suitableBtnBy = By.xpath("//ul[@class='right-side-menu']//a[@href='/lists#n=suitable']");
    private By moreBtnBy = By.xpath("//div[@class='button-FR button--navy-border button--middle button--full-size button--vertical-margin-10 js_moreOptionsTrigger']");
    private By writeMsgBtnBy = By.xpath("//ul[@class='user-action-options user-action-options--left-arrow list-user-options js_moreOptions']/li[1]");

    private By msgAreaBy = By.xpath("//textarea[@name='message']");
    private By sendMsgBtnBy = By.xpath("//button[@type='button']/parent::label");
    private By msgTextBy = By.xpath("//p[@class='chat__message chat__message--from']");

    @Test
    public void loginTest() {
        login(email, pswd);
        Assert.assertTrue(isElementPresent(profilePhotoBy));
    }

    @Test(dependsOnMethods = "loginTest")
    public void messageTest() {
        String msgText = "Hey! How are you?";
        click(suitableBtnBy);
        click(moreBtnBy);
        click(writeMsgBtnBy);
        typeText(msgAreaBy, msgText);
        click(sendMsgBtnBy);
        waitForElementPresent(msgTextBy);
        Assert.assertTrue(driver.findElement(msgTextBy).getText().equals(msgText));
    }

    @Test(dependsOnMethods = "messageTest")
    public void genderTest() {
        SoftAssert softAssert = new SoftAssert();

        changeGender(Gender.FEMALE);
        softAssert.assertFalse(isElementDisapeared(saveBtnBy));

        softAssert.assertAll();
    }

}
