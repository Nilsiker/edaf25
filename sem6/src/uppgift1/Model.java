package uppgift1;

import java.util.Observable;

public class Model extends Observable {
    private int state;

    public Model (){
        state = 0;
    }

    public void changeState(){
        state++;
        setChanged();
        notifyObservers();
    }

    public String state(){
        return String.valueOf(state);
    }
}
