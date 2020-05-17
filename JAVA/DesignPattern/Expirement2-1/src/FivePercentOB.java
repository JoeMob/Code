public class FivePercentOB implements Observer {

    private String Owner;

    FivePercentOB(String owner) {
        Owner = owner;
    }

    public String getOwner() {
        return Owner;
    }

    @Override
    public void addStock(Stock stock) {
        stocks.put(stock.getName(), stock.getSharePrice() + "");
    }

    @Override
    public void checkPrice(Stock stock) {
        double originPrice = Double.parseDouble(stocks.get(stock.getName()));
        double gap = Math.abs(originPrice - stock.getSharePrice());
        if (gap > originPrice * 0.05) {
            System.out.println("Hi " + Owner + ", " + stock.getName() + "'s price has changed over 5%");
        }
    }
}
