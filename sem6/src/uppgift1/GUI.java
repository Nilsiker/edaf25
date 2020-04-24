package uppgift1;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GUI extends Frame implements Observer {
    public GUI(Model model){
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        setTitle(model.state());
    }



    public static void main(String[] arg) {
        Model model = new Model();
        GUI gui = new GUI(model);
        gui.setVisible(true);
        gui.setSize(200,1);
        model.changeState();
        model.changeState();
        model.changeState();
        model.changeState();
    }

}