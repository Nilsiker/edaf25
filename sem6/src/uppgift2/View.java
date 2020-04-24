package uppgift2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View extends JButton implements Observer, ActionListener {
    private Controller controller;

    public View(Model model){
        super("OFF");
        addActionListener(this);
        model.addObserver(this);
        controller = new Controller(model);
    }

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        setText(model.state());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.flip();
    }




    public static void main(String[] args) {
        Frame frame = new Frame();
        View view = new View(new Model());
        frame.add(view);
        frame.setVisible(true);
        frame.setSize(400,400);
        view.setFont(new Font("Arial", Font.PLAIN, 40));
    }
}
