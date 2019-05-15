package com.company;

public class ImageMap
{
    /**
     * Szín mátrixot tároló tömb.
     */
    int[][] kep;//értéke a szín

    /**
     * A konstruktor a megadott méretek alapján létrehozza a megfelelő méretű mátrixot
     *
     * @param x_meret A mátrix x tengely beli mérete.
     * @param y_meret A mátrix y tengely beli mérete.
     */
    ImageMap(int x_meret,int y_meret)
    {
        kep = new int[x_meret][y_meret];
    }

    /**
     * A függvény a megadott paraméterek alapján beállítja egy egység színét.
     *
     * @param x Melyik elem színét változtatod az x tengelyen.
     * @param y Melyik elem színét változtatod az y tengelyen.
     * @param c Milyen színűre változtatod.
     */
    public void setRGB(int x, int y, int c) {
        kep[x][y] = c;
    }

    /**
     * A függvényel a paraméterek által kért elem színét kérheted le.
     *
     * @param x Melyik elem színét szeretnéd lekérdezni az x tengelyen.
     * @param y Melyik elem színét szeretnéd lekérdezni az y tengelyen.
     * @return  A kért elem színe.
     */
    public int getRGB(int x, int y) {
        return kep[x][y];
    }
}
