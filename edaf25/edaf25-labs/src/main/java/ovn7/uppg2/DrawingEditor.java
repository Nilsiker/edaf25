package ovn7.uppg2;

import java.awt.*;

// a) DrawingEditor är beroende av Shape, Circle och Square - onödigt!

public class DrawingEditor {
    ShapeFactory factory;
    Shape create(String s){

        if(s.equals("Circle")){
            return factory.createCircle();
        } else if(s.equals("Square")){
            return factory.createSquare();
        }
        return null;
    }
}
