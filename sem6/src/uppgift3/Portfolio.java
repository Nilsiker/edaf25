package uppgift3;

import java.util.*;

public class Portfolio extends Observable implements Observer {
    private double initialValue;
    List<Stock> stocks;

    public Portfolio() {
        initialValue = 0;
        stocks = new ArrayList<>();
    }

    public void add(Stock stock) {
        initialValue += stock.value();
        stocks.add(stock);
        stock.addObserver(this);
    }

    public double currentYield() {
        double currentValue = 0;
        for (Stock stock : stocks) {
            currentValue += stock.value();
        }
        return currentValue / initialValue;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
