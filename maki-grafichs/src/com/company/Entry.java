package com.company;

/**
 * A bejáratot jelölő mező. Singelton, mert csak egy van belőle, és több helyről el kell érni.
 */
public class Entry extends Field {

    private  static Entry instance;

    /**
     * Privát konstruktor
     */
    private Entry()
        { }

        static {
        instance = new Entry();
        }

    /** A függvény visszaadja az objektum példányát
     * @return az objektumpéldányt
     */
    public static Entry getInstance() {
        return instance;
    }
}
