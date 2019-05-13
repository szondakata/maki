package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ornagutan_FX extends Animal_FX{
    public Ornagutan_FX(boolean egy_jatekos) {
        animal = new Circle(0,0,15, Color.GRAY);
        animal.setVisible(false);
        if (egy_jatekos){animal.setStroke(Color.BLUE);}
        else {animal.setStroke(Color.RED);}
        animal.setStrokeWidth(3);
    }
}
