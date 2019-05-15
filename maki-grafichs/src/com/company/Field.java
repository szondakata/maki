package com.company;

import java.util.ArrayList;

/**
 * A sima csepmét megvalósító osztály. Belőle származnak le a tárgyak.
 */
public class Field {
    /**
     * Logger osztály
     */
    protected Logger logger;
    /**
     * A mező ID-ja
     */
    String ID;
    /**
     * A mezőn álló egység
     */
    private Unit contain;
    /**
     * A mező szomszédainak listája
     */
    private ArrayList<Field> nei;


    /**
     * Konstruktor, ami lértehoz egy loggert és lefoglalja a szomszédok listáját
     */
    public Field() {
        logger = new Logger();
        nei = new ArrayList<>();
    }

    /** A mezőn álló egységet lekérdező függvény
     * @return a mezőn álló eggység
     */
    public Unit getContain() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getContain()");
        logger.depthM();
        return contain;
    }

    /** A függvény a paraméterben kepott egységet beállítja a mezőn állóra.
     * Értesíti a mezőt, azaz önmagát, hogy az egység rálépett.
     * @param contain az egység
     */
    public void setContain(Unit contain) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setContain(" + (contain == null ? "Null" : contain.toString()) + ")");

        this.contain = contain;
        this.stepped(contain);
        logger.depthM();
    }

    /** A függvény viassztér a csempe szomszédainal listáját
     * @return a csempe szomszédainak listája
     */
    public ArrayList<Field> getNei() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getNei()");
        logger.depthM();
        return nei;
    }

    public void setNei(ArrayList<Field> nei) {
        this.nei = nei;
    }

    /**
     * Ez egy sima csempe, ha rálép valaki nem történik semmi.
     */
    public void Update() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".Update()");
        logger.depthM();
    }//Nem csinál semmit

    /** Ez egy sima csempe, ha rálép valami, nem történik vele semmi.
     * @param u az egység, ami a csempére lépett
     */
    public void stepped(Unit u) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".Stepped(" + (u == null ? "Null" : u.toString()) + ")");
        logger.depthM();
    }//Nem csinál semmit


    /** A paraméterben kapott csempét a csempe szomszédaihoz adja
     * @param f a csempe
     */
    public void addNei(Field f) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".addNei(" + f.toString() + ")");

        nei.add(f);
        if (!f.getNei().contains(this)) f.addNei(this); //igy jo
        logger.depthM();
    }
}

