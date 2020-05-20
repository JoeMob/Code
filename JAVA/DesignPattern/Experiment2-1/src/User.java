public class User {
    static void buyStock(Stock stock, FivePercentOB fivePercentOB) {
        stock.addObserver(fivePercentOB);
        System.out.println("Stock bought by " + fivePercentOB.getOwner() + "\nName: " + stock.getName() + " Price:" + stock.getSharePrice());
    }

    public static void main(String[] args) {
        FivePercentOB JoeMobOB = new FivePercentOB("JoeMob");
        FivePercentOB AsukaOB = new FivePercentOB("Asuka");
        Stock stock1 = new Stock("Apple", 11);
        Stock stock2 = new Stock("Google", 8);
        buyStock(stock1, JoeMobOB);
        buyStock(stock2, JoeMobOB);
        buyStock(stock2, AsukaOB);
        stock1.priceChange(0.1);
        stock2.priceChange(5);
    }

}
