package lesson5;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ShawshankRedemption extends BaseTest {
    @Test
    public void getFilmInfo() {
        driver.get("https://www.imdb.com/title/tt0111161/");

        By name = By.xpath("//*[@class='title_wrapper']/h1");
        By date = By.xpath("//*[@title='See more release dates']");
        By duration = By.xpath("//*[@id='titleDetails']//time");
        By rating = By.xpath("//*[@class='ratingValue']/strong");
        By genre = By.xpath("//*[@class='title_wrapper']//*[contains(@href,'genre')]");
        By trailer = By.xpath("//*[@class='slate']/a"); //href
        By poster = By.xpath("//*[@class='poster']//img"); // src
        By director = By.xpath("//h4[.='Director:']/parent::div/a");
//        By actors = By.xpath("//h4[.='Stars:']/parent::div/a");
//        By actors = By.xpath("//table[@class='cast_list']//td[2]/a");
        By actors = By.xpath("//table[@class='cast_list']//a[contains(@href, 'name')]");

        By metacsore = By.xpath("//*[@class='titleReviewBarItem']//div[contains(@class,'metacriticScore')]/span");
        By reviewCount = By.xpath("//*[@class='user-comments']//*[contains(@href,'reviews')]");
        By similarFilms = By.xpath("//*[@id='relatedListsWidget']//*[contains(@class,'list-preview')]//div[@class='list_name']//a");

        System.out.println(getTextBy(name));
        System.out.println(getTextBy(date));

        String durationStr = getTextBy(duration);
        int durationMin = Integer.parseInt(durationStr.substring(0, durationStr.indexOf(" ")));
        int durationSec = durationMin * 60;
        System.out.println(durationMin);
        System.out.println(durationSec);

        System.out.println(getTextBy(rating));
        System.out.println(getTextBy(genre));

        System.out.println(driver.findElement(trailer).getAttribute("href"));
        System.out.println(driver.findElement(poster).getAttribute("src"));

        System.out.println(getTextBy(director));

        List<WebElement> actorsArr = driver.findElements(actors);
        for (int i = 0; i < 5; i++) {
            System.out.println(actorsArr.get(i).getText());
        }

        System.out.println(getTextBy(metacsore));
        System.out.println(getTextBy(reviewCount));

        List<WebElement> similarArr = driver.findElements(similarFilms);
        for (int i = 0; i < 3; i++) {
            System.out.println(similarArr.get(i).getText());
        }
    }

    protected static String getTextBy(By by) {
        return driver.findElement(by).getText();
    }
}
