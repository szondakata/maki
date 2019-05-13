package com.company;


import java.util.ArrayList;

/**
 * Az egyseget megvalosito osztaly
 */
public abstract class Unit implements Action {

    /**
     * Az eygseg ID-ja
     */
    public String ID;
    protected Logger logger;
    /**
     * Eletben van-e az egyseg
     */
    boolean isAlive = true;

    /**
     * Az egyseg pozicioja
     */
    private Field iamon;

    /**
     * Az egyseg elso mancsa
     */
    private Unit hand1;

    /**
     * Az egyseg masodik mancsa
     */
    private Unit hand2;

    public Unit() {
        logger = new Logger();
    }

    public Field getIamon() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getIamon()");
        logger.depthM();
        return iamon;

    }

    public void setIamon(Field iamon) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setIamon(" + (iamon == null ? "null" : iamon.toString()) + ")");
        logger.depthM();
        this.iamon = iamon;
    }

    public Unit getHand1() {

        logger.depthP();
        logger.writeMessage(this.toString() + ".getHand1()");
        logger.depthM();
        return hand1;
    }

    public void setHand1(Unit hand1) {

        logger.depthP();
        logger.writeMessage(this.toString() + ".setHand1(" + hand1 + ")");
        logger.depthM();
        this.hand1 = hand1;
    }

    public Unit getHand2() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getHand2()");
        logger.depthM();
        return hand2;
    }

    public void setHand2(Unit hand2) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setHand2(" + hand2 + ")");
        logger.depthM();
        this.hand2 = hand2;
    }


    public void grab(Unit u) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".grab(" + u.toString() + ")");
        logger.depthM();
    }

    public void exit(Field field) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".exit(" + (field == null ? "Null" : field.toString()) + ")");
        logger.depthM();
    }

    /**
     * Az egyseg megszunteteset vegzo fuggveny
     */
    public void die() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".die()");
        logger.depthM();
        if (getIamon().getContain() != null)
            getIamon().setContain(null);

        if (this.getHand1() != null)
            this.getHand1().setHand2(null);
        setHand1(null);

        if (this.getHand2() != null)
            this.getHand2().setHand1(null);
        setHand2(null);

        setIamon(null);

        this.setAlive(false);

    }


    /** Az egyseg mozgatasat vegzo fuggveny
     * @param f a kivant mezo
     * @return false, ha a mezo foglalt
     */
    public boolean move(Field f) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".move(" + f.toString() + ")");
        if (f.getContain() == null)//Léphetek-e oda?
        {
            Field temp = this.getIamon();
            f.setContain(this);

            if (!temp.equals(iamon)) temp.setContain(null);
            if (this.getHand1() != null) {
                this.getHand1().move(temp);
            }
            this.setIamon(f);
            logger.depthM();

            return true;
        } else {
            logger.depthM();
            return false;
        }
    }


    /**
     * Az egyseg mogotti sor felbontasat vegzo fuggveny
     */
    public void release() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".release()");
        if (getHand1() != null) {
            getHand1().setHand2(null);
            setHand1(null);
        }
        if (getHand2() != null) {
            getHand2().release();
        }
        logger.depthM();
    }

    public boolean isAlive() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".isAlive()" + ")");
        logger.depthM();
        return isAlive;
    }

    public void setAlive(boolean alive) {
        logger.depthP();
        logger.writeMessage(this.toString() + "setAlive(" + alive + ")");
        logger.depthM();
        isAlive = alive;
    }

    /** Az egyseg adatait stringkent tovabbito fuggveny
     * @return az egyseg adatai
     */
    public ArrayList<String> kiir() {
        ArrayList<String> s = new ArrayList<>();
        s.add("  Azonosító: " + ID + "\n");
        if (getHand1() != null)
            s.add("  Kéz1 : " + getHand1().ID + "\n");
        else
            s.add("  Kéz1 : Nincs\n");
        if (getHand2() != null)
            s.add("  Kéz2 : " + getHand2().ID + "\n");
        else
            s.add("  Kéz2 : Nincs\n");
        return s;
    }
}
