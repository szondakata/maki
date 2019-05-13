package com.company;

import javafx.scene.shape.Circle;

public abstract class Animal_FX
{
    Circle animal;

    public Circle getAnimal() {
        return animal;
    }

    public void place(koor hova)
    {
        animal.setVisible(true);
        animal.setCenterX(hova.x);
        animal.setCenterY(hova.y);
    }

    public void kill()
    {
        animal.setVisible(false);
        animal.setCenterX(-100);
        animal.setCenterY(-100);
    }
}
