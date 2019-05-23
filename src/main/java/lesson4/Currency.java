package lesson4;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Currency extends base.BaseTest {

    @Test
    public void testCurrency() {
        WebDriver driver = new base.BaseTest().initDriver();

        String[][] bankArr = new String[][]{
                {"pb", "https://www.privat24.ua/"},
                {"ukrsib", "https://my.ukrsibbank.com/ru/personal/operations/currency_exchange/"},
                {"universal", "https://www.universalbank.com.ua/"},
                {"oshchad", "https://www.oschadbank.ua/ua"},
//                {"nbu", "https://www.bank.gov.ua/control/uk/curmetal/detail/currency?period=daily"},
        };

        //put rates to list
        List<BankRate> bankRates = new ArrayList<>();
        for (String[] bank : bankArr) {
            String bankName = bank[0];
            driver.get(bank[1]);
            double[] rates = parseRate(bankName);
            bankRates.add(new BankRate(bank[0], rates[0], rates[1]));
        }

        //calc rates sum and min/max values
        double sumBuy = 0;
        double sumSell = 0;
        double minSell = bankRates.get(0).getSell();
        double maxBuy = bankRates.get(0).getBuy();

        for(BankRate bankRate : bankRates) {
            sumBuy += bankRate.getBuy();
            sumSell += bankRate.getSell();

            minSell = minSell < bankRate.getSell() ? minSell : bankRate.getSell();
            maxBuy = maxBuy > bankRate.getBuy() ? maxBuy : bankRate.getBuy();
        }

        //get bank names with min/max values, include case if min/max rate matches in several banks
        List<String> minSellBank = new ArrayList<>();
        List<String> maxBuyBank = new ArrayList<>();

        System.out.println(BankRate.getHeader());
        for(BankRate bankRate : bankRates) {
            if(bankRate.getSell() < minSell) {
                minSellBank.clear();
                minSellBank.add(0, bankRate.getBankName());
            } else if(minSell == bankRate.getSell()) {
                minSellBank.add(minSellBank.size(), bankRate.getBankName());
            }

            if(bankRate.getBuy() > maxBuy) {
                maxBuyBank.clear();
                maxBuyBank.add(0, bankRate.getBankName());
            } else if(maxBuy == bankRate.getBuy()) {
                maxBuyBank.add(maxBuyBank.size(), bankRate.getBankName());
            }

            System.out.println(bankRate);
        }

        System.out.println();
        System.out.println("MinBuyBank: " + minSellBank);
        System.out.println("MaxSellBank: " + maxBuyBank);

        System.out.println("AvgBuy: " + sumBuy / bankArr.length);
        System.out.println("AvgSell: " + sumSell / bankArr.length);

        driver.quit();
    }

    private double[] parseRate(String bank) {
        double buy = 0;
        double sell = 0;

        switch (bank) {
            case ("pb"): {
                String rateStr = getTextBy(By.xpath("//div[@class='exchange-rate-module']/div[1]"));
                rateStr = rateStr.replace("USD:", "").replace(" ", "");

                buy = Double.parseDouble(rateStr.substring(0, rateStr.indexOf("/")));
                sell = Double.parseDouble(rateStr.substring(rateStr.indexOf("/") + 1));
                break;
            }
            case ("ukrsib"): {
                buy = Double.parseDouble(getTextBy(By.xpath("//td[contains(text(),'USD')]/parent::tr/td[2]")));
                sell = Double.parseDouble(getTextBy(By.xpath("//td[contains(text(),'USD')]/parent::tr/td[3]")));
                break;
            }
            case ("universal"): {
                buy = Double.parseDouble(getTextBy(By.xpath("//div[contains(@class,'currencies-list')]/div[1]//td[contains(text(),'USD')]/parent::tr/td[2]")));
                sell = Double.parseDouble(getTextBy(By.xpath("//div[contains(@class,'currencies-list')]/div[1]//td[contains(text(),'USD')]/parent::tr/td[3]")));
                break;
            }
            case ("oshchad"): {
                buy = Double.parseDouble(getTextBy(By.xpath("//strong[@class='buy-USD']")));
                sell = Double.parseDouble(getTextBy(By.xpath("//strong[@class='sell-USD']")));
                break;
            }
            case ("nbu"): {
                buy = Double.parseDouble(getTextBy(By.xpath("//td[contains(text(),'USD')]/parent::tr/td[5]"))) / 100;
                sell = Double.parseDouble(getTextBy(By.xpath("//td[contains(text(),'USD')]/parent::tr/td[5]"))) / 100;
                break;
            }
        }
        return new double[]{buy, sell};
    }
}
