package com.company;

import java.util.ArrayList;

/**
 * A pálya kijáratát megvalósító osztály
 */
public class Exit extends Field {

    /**
     * Ide rakjuk ki a kilépett pandákat
     */
    ArrayList<Unit> talon = new ArrayList<>();

    /**
     * Paraméterben kapott egységet a pálya elhagyására kényszerítő függvény
     * @param p az egység
     */
    @Override
    public void stepped(Unit p) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".stepped(" + (p == null ? "Null" : p.toString()) + ")");
        if (p != null) {
            talon.add(getContain());
            p.exit(this);
            setContain(null);
        }
        logger.depthM();
    }

    /** Beállítja a csempén álló egységet a paraméterben kapottra, illetve jelzi saját magának,
     * hogy az egység a csempére lépett.
     * @param contain
     */
    public void setContain(Unit contain) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setContain(" + (contain == null ? "Null" : contain.toString()) + ")");
        this.stepped(contain);
        logger.depthM();
    }

}
