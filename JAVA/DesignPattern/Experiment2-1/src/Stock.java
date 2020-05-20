import java.util.List;
import java.util.ArrayList;

public class Stock {
    private List<Observer> observers = new ArrayList<Observer>();
    private String Name;
    private double SharePrice;

    public Stock(String name, double sharePrice) {
        Name = name;
        this.SharePrice = sharePrice;
    }

    public double getSharePrice() {
        return SharePrice;
    }

    public String getName() {
        return Name;
    }

    public void priceChange(double priceChange) {
        SharePrice += priceChange;
        notifyObserver();
    }

    public void addObserver(FivePercentOB fivePercentOB) {
        observers.add(fivePercentOB);
        fivePercentOB.addStock(this);
    }

    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.checkPrice(this);
        }
    }
}
