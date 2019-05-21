package lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class Cinema extends base.BaseTest {
    @Test
    public void test() {
        driver.get("http://liniakino.com/showtimes/aladdin/");
        click(By.xpath("//*[@href='/movies/?id=7545']/../../div[@class='showtime']/div[1]//ul[@class='showtime-time']/li[1]/a"));

        WebElement iframe =  driver.findElement(By.xpath("//div[@id='vkino-widget']/iframe"));
        driver.switchTo().frame(iframe);

        List<WebElement> freeL = driver.findElements(By.xpath("//div[@id='hall-scheme-container']//div[@class='seat seat-color1']"));
        List<WebElement> boughtL = driver.findElements(By.xpath("//div[@id='hall-scheme-container']//div[@class='seat seat-occupied']"));
        List<WebElement> reservedL = driver.findElements(By.xpath("//div[@id='hall-scheme-container']//div[@class='seat seat-reserved']"));
        List<WebElement> allL = driver.findElements(By.xpath("//div[@id='hall-scheme-container']//div[contains(@class,'seat seat-')]"));

        int notFree = boughtL.size() + reservedL.size();
        int free = freeL.size();
        int all = allL.size();

        System.out.println("All places: " + all);
        System.out.println("Ordered places: " + notFree);
        System.out.println("Free places: " + free);
        System.out.println("%Ordered places: " + notFree*100.0/all);
        System.out.println("%Free places: " + free*100.0/all);
    }
}
