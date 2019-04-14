package lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Akinator {
    private static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        By startGame = By.xpath("//*[@href='/game']");
        By question = By.xpath("//*[@class='question-text']");
        By variants = By.xpath("//*[@class='database-selection selector dialog-box']/ul//a");
        By proposalTitle = By.xpath("//*[@class='proposal-title']");
        By proposalSubtitle = By.xpath("//*[@class='proposal-subtitle']");
        By proposeYes = By.xpath("//*[@id='a_propose_yes']");
        By proposeNo = By.xpath("//*[@id='a_propose_no']");

        By cYes = By.xpath("//*[@id='a_continue_yes']");
        By cNo = By.xpath("//*[@id='a_continue_no']");

        By winSentence = By.xpath("//*[@class='win-sentence']");

        driver.get("https://ru.akinator.com");
        click(startGame);

        Scanner in = new Scanner(System.in);

        boolean solved = false;
        int questionsCount = 0;

        do {
            questionsCount++;
            driver.findElement(question);
            System.out.println(questionsCount + ". " + driver.findElement(question).getText());

            List<WebElement> variantsList = driver.findElements(variants);

            for (int i = 0; i < variantsList.size(); i++) {
                System.out.println(i + 1 + ". " + variantsList.get(i).getText());
            }
            System.out.println();
            String choice = in.nextLine();
            int varNumber = Integer.parseInt(choice);
            click(By.xpath("//*[@class='database-selection selector dialog-box']/ul/li[" + varNumber + "]"));

            if (isElementPresent(proposalTitle)) {
                System.out.println(driver.findElement(proposalTitle).getText());
                System.out.println(driver.findElement(proposalSubtitle).getText());

                if (askYN("Please type y/n:")) {
                    click(proposeYes);
                    System.out.println(driver.findElement(winSentence).getText());
                    solved = true;
                } else {
                    click(proposeNo);

                    if (askYN("Continue? y/n:")) {
                        click(cYes);
                    } else {
                        click(cNo);
                    }
                }
            }
        } while (!solved);

        driver.quit();
    }

    private static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void click(By by) {
        driver.findElement(by).click();
    }

    private static boolean askYN(String s) {
        Scanner in = new Scanner(System.in);
        boolean needYNAnswer = true;

        boolean answ = false;
        while (needYNAnswer) {
            System.out.println(s + ":");
            String res = in.next();
            if (res.toLowerCase().equals("y") || res.toLowerCase().equals("yes")) {
                answ = true;
                needYNAnswer = false;
            } else if (res.toLowerCase().equals("n") || res.toLowerCase().equals("no")) {
                needYNAnswer = false;
            } else {
                System.out.println("Incorrect choice.");
            }
        }
        return answ;
    }
}