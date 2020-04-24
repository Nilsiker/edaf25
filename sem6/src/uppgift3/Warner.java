package uppgift3;

import java.util.Observable;
import java.util.Observer;

public class Warner implements Observer {
    private double yieldLimit;

    public Warner(double yieldLimit){
        this.yieldLimit = yieldLimit;
    }

    public void add(Portfolio p){
        p.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Portfolio p = (Portfolio) o;
        if(p.currentYield() < yieldLimit){
            System.out.println("WARNING! Portfolio under yield limit!");
        }
    }
}
