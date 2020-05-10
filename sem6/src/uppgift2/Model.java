package uppgift2;


import java.util.Observable;

public class Model extends Observable {
    private boolean state;

    public Model(){
        state = false;
    }

    public void flip(){
        state = !state;
        setChanged();
        notifyObservers();
    }

    public String state(){
        return state ? "ON" : "OFF";
    }
}