package com.company;


import javafx.scene.shape.Polygon;

/**
 * A kirajzoláshoz szükséges pont osztály
 */
public class koor{
    /**
     * X koordináta
     */
    public int x;
    /**
     * Y koordináta
     */
    public int y;
    /**
     * A koordinátát tartalmazó poligon
     */
    public Polygon ki;

    /**
     * Konstruktor, amely a koordinátákat -1-re inicializálja
     */
    public koor(){
        x=-1;
        y=-1;
    }
}
