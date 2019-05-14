package com.company;

import java.util.ArrayList;

/**
 * A kijaratot megvalosito osztaly
 */
public class Exit extends Field {

    /**
     * A palyat epp elhagyni keszulok gyujtesere szolgalo lista
     */
    ArrayList<Unit> talon = new ArrayList<>();

    /** A parameterekent kapott egyseget a talonba helyezi es meghivja az exit fuggvenyet, majd leveszi sajat magarol
     * @param p az egyseg
     */
    @Override
    public void stepped(Unit p) {
		logger.depthP();
        logger.writeMessage(this.toString()+".stepped("+(p==null ? "Null" : p.toString())+")");
        if (p != null) {
            talon.add(getContain());
            p.exit(this);
            setContain(null);
        }
		logger.depthM();
    }


    /** A parameterkent kapott egyseget a csempere helyezo fuggveny
     * @param contain az egyseg
     */
    public void setContain(Unit contain) {
        logger.depthP();
	    logger.writeMessage(this.toString()+".setContain("+(contain==null ? "Null" : contain.toString())+")");
        this.stepped(contain);
        logger.depthM();
    }

}
