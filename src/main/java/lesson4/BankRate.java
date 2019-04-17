package lesson4;

public class BankRate {
    private double buy;
    private double sell;
    private String bankName;

    public BankRate(String bankName, double buy, double sell) {
        this.buy = buy;
        this.sell = sell;
        this.bankName = bankName;
    }

    public double getBuy() {
        return buy;
    }

    public double getSell() {
        return sell;
    }

    public String getBankName() {
        return bankName;
    }

    @Override
    public String toString() {
        return String.format("%10s", bankName)
                + "\t" + String.format("%8f", buy)
                + "\t" + String.format("%8f", sell);
    }

    public static String getHeader() {
        return String.format("%10s", "bankName")
                + "\t" + String.format("%9s", "Buy")
                + "\t" + String.format("%9s", "Sell");
    }
}
