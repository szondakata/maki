package com.company;
import com.company.Logger;
import com.company.Unit;

import java.util.ArrayList;

/**
 * A csempet megvalosito osztaly
 */
public class Field {
    /**
     * A csempe ID-ja
     */
    public String ID;
    /**
     * Logger osztaly
     */
    protected Logger logger;
    /**
     * A csempen allo egyseg
     */
    private Unit contain;
    /**
     * A csempe szomszeds csempeinek listaja
     */
    private ArrayList<Field> nei = new ArrayList<>();//A mező szomszédai


    /**
     * Letrejon a csempe es egy logger
     */
    public Field() {
        logger = new Logger();
    }

    /** A csepmen allo egyseget megmutato fuggveny
     * @return a csempen allo egyseg
     */
    public Unit getContain() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getContain()");
        logger.depthM();
        return contain;
    }

    /** A parameterben kapott egyseget a csempere helyezo fuggveny
     * @param contain az egyseg
     */
    public void setContain(Unit contain) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setContain(" + (contain == null ? "Null" : contain.toString()) + ")");

        this.stepped(contain);
        this.contain = contain;
        logger.depthM();
    }

    /** A csempe szomszedait megmutato fuggveny
     * @return a csempe szomszedainak listaja
     */
    public ArrayList<Field> getNei() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getNei()");
        logger.depthM();
        return nei;
    }

    /** A fuggveny a csempe szomszedait a paramtereben kapott csempelistara csereli
     * @param nei a csempek listaja
     */
    public void setNei(ArrayList<Field> nei) {
        this.nei = nei;
    }

    /**
     * A sima cesmpere lepve nem tortnik semmi; a fuggveny nem csinal semmit
     */
    public void Update() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".Update()");
        logger.depthM();
    }

    /**
     * A sima cesmpere lepve nem tortnik semmi; a fuggveny nem csinal semmit
     */
    public void stepped(Unit u) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".Stepped(" + (u == null ? "Null" : u.toString()) + ")");
        logger.depthM();
    }

    public void addNei(Field f) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".addNei(" + f.toString() + ")");

        nei.add(f);
        if (!f.getNei().contains(this)) f.addNei(this); //igy jo
        logger.depthM();
    }


    /** A csempe reszletes adatait leiro fuggveny
     * @return a csempe adatai
     */
    public ArrayList<String> kiir() {
        ArrayList<String> s = new ArrayList<>();
        s.add("  Azonosító: " + ID + "\n");
        if (getContain() != null)
            s.add("  Tartalmazott egység: " + getContain().ID + "\n");
        else
            s.add("  Tartalmazott egység: Nincs\n");
        if (nei.isEmpty()) {
            s.add("     Szomszédok: Nincs\n");
        }else
            s.add("    Szomszédok:\n");
        for (Field f : nei) {
            s.add("		" + f.ID + "\n");
        }

        return s;
    }
}

