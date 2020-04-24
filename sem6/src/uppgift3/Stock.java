package uppgift3;

import java.util.Observable;
import java.util.Observer;

public class Stock extends Observable {
    private double value;

    public void update(double value){
        this.value = value;
        setChanged();
        notifyObservers();
    }

    public double value(){
        return value;
    }
}
