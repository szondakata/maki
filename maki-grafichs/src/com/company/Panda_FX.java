package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Panda_FX extends Animal_FX
{
    public Panda_FX(int type) {//0 sima fekete, 1 ijedős sárga, 2 ugrálós barna, 3 álmos fehér
        int graphics_ID;
        animal = new Circle(0,0,15, Color.BLACK);
        animal.setVisible(false);
        switch (type)
        {
            case 0:
                animal.setStroke(Color.BLACK);
                break;
            case 1:
                animal.setStroke(Color.YELLOW);
                break;
            case 2:
                animal.setStroke(Color.BROWN);
                break;
            case 3:
                animal.setStroke(Color.WHITE);
                break;
            default:
                animal.setStroke(Color.BLACK);
        }
        animal.setStrokeWidth(3);
    }
}
