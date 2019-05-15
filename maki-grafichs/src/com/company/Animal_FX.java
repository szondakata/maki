package com.company;

import javafx.scene.shape.Circle;

/**
 * Az állatokhoz tartozó grafikus megjelnítésért felelős abstract osztály
 * tartalmazza az állathoz rendelt grafikus elemet
 */
public abstract class Animal_FX
{
    /**
     * Az állatott jelképező grafikus kör
     */
    Circle animal;

    /**
     * Getter metódus vissza adja a grafikus kört ami az állathoz tartozik
     * @return visszadaja az állathoz tartozó grafikus elemet
     */
    public Circle getAnimal() {
        return animal;
    }

    /**
     * Láthsatóvá teszi az elemet és elhelyezi a megadott koordinátkon
     * @param hova a kooordináta ahova el kell helyezni a grafikus elemet
     */
    public void place(koor hova)
    {
        animal.setVisible(true);
        animal.setCenterX(hova.x);
        animal.setCenterY(hova.y);
    }

    /**
     * Leveszi a grafikus elemet a viewról
     */
    public void kill()
    {
        animal.setVisible(false);
        animal.setCenterX(-100);
        animal.setCenterY(-100);
    }
}
