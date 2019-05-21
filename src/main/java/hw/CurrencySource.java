package hw;

import lesson4.BankRate;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CurrencySource extends base.BaseTest {

    public static void main(String[] args) {
        WebDriver driver = new base.BaseTest().initDriver();

        String[][] bankArr = new String[][]{
                {"pb", "https://www.privat24.ua/"},
//                {"ukrsib", "https://my.ukrsibbank.com/ru/personal/operations/currency_exchange/"},
//                {"universal", "https://www.universalbank.com.ua/"},
//                {"oshchad", "https://www.oschadbank.ua/ua"},
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
        double minBuy = bankRates.get(0).getBuy();
        double maxSell = bankRates.get(0).getSell();

        for(BankRate bankRate : bankRates) {
            sumBuy += bankRate.getBuy();
            sumSell += bankRate.getSell();

            minBuy = minBuy < bankRate.getBuy() ? minBuy : bankRate.getBuy();
            maxSell = maxSell > bankRate.getSell() ? maxSell : bankRate.getSell();
        }

        //get bank names with min/max values, include case if min/max rate matches in several banks
        List<String> minBuyBank = new ArrayList<>();
        List<String> maxSellBank = new ArrayList<>();

        System.out.println(BankRate.getHeader());
        for(BankRate bankRate : bankRates) {
            if(bankRate.getBuy() < minBuy) {
                minBuyBank.clear();
                minBuyBank.add(0, bankRate.getBankName());
            } else if(minBuy == bankRate.getBuy()) {
                minBuyBank.add(minBuyBank.size(), bankRate.getBankName());
            }

            if(bankRate.getSell() > maxSell) {
                maxSellBank.clear();
                maxSellBank.add(0, bankRate.getBankName());
            } else if(maxSell == bankRate.getSell()) {
                maxSellBank.add(maxSellBank.size(), bankRate.getBankName());
            }

            System.out.println(bankRate);
        }

        System.out.println();
        System.out.println("MinBuyBank: " + minBuyBank);
        System.out.println("MaxSellBank: " + maxSellBank);

        System.out.println("AvgBuy: " + sumBuy / bankArr.length);
        System.out.println("AvgSell: " + sumSell / bankArr.length);

        driver.quit();
    }

    private static double[] parseRate(String bank) {
        double buy = 0;
        double sell = 0;

        String source = driver.getPageSource();
//        System.out.println(source);

        switch (bank) {
            case ("pb"): {
                int indexOfUSD = source.indexOf("USD:");
                source = source.substring(indexOfUSD);

                String str = "section-content rate";
                int indexOfRate = source.indexOf(str) + str.length() + 2;

                buy = Double.parseDouble(source.substring(indexOfRate,indexOfRate+6));
                sell = Double.parseDouble(source.substring(indexOfRate+19,indexOfRate+25));
                break;
            }
        }
        return new double[]{buy, sell};
    }
}
