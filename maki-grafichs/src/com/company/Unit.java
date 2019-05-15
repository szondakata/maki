package com.company;


/**
 * A játékban szereplő egységek absztrakt ősosztálya
 */
public abstract class Unit implements Action {

    /**
     * Az egység ID-ja
     */
    public String ID;
    /**
     * Logger osztály
     */
    protected Logger logger;
    /**
     * Az egység életben van-e flag
     */
    boolean isAlive = true;
    /**
     * Az egység jelenlegi csempéje, amin áll
     */
    private Field iamon;//Melyik mezőn vagyok
    /**
     * Az egység előre mutató mancsa
     */
    private Unit hand1;//"Hátra" mutató kéz
    /**
     * Az egység hátra mutató mancsa
     */
    private Unit hand2;//"Előre" mutató kéz
    /**
     * Konstruktor, amely létrehoz egy loggert
     */
    public Unit() {
        logger = new Logger();
    }

    /** Az egység jelenlegi csempéjét lekérdező függvény
     * @return az egység jelenlegi csempéje
     */
    public Field getIamon() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getIamon()");
        logger.depthM();
        return iamon;
    }

    /** Az egység jelenlegi csempéjét a paraméterben kapottra beállító függvény.
     * @param iamon a csempe
     */
    public void setIamon(Field iamon) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setIamon(" + (iamon == null ? "null" : iamon.toString()) + ")");
        logger.depthM();
        this.iamon = iamon;
    }

    /** Az egység első mancsában lévő egységet visszaadó függvény
     * @return az egység előtt menő egység
     */
    public Unit getHand1() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getHand1()");
        logger.depthM();
        return hand1;
    }

    /** Az egység első mancsába a paraméterben kapott egységet helyezi.
     * @param hand1 az egység
     */
    public void setHand1(Unit hand1) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setHand1(" + hand1 + ")");
        logger.depthM();
        this.hand1 = hand1;
    }

    /**
     * @return
     */
    public Unit getHand2() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getHand2()");
        logger.depthM();
        return hand2;
    }

    /**
     * @param hand2
     */
    public void setHand2(Unit hand2) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setHand2(" + hand2 + ")");
        logger.depthM();
        this.hand2 = hand2;
    }

    /**
     * @param u
     */
    public void grab(Unit u) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".grab(" + u.toString() + ")");
        logger.depthM();
    } //Üres osztály

    /**
     * @param field
     */
    public void exit(Field field) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".exit(" + (field == null ? "Null" : field.toString()) + ")");
        logger.depthM();
    }

    /**
     *
     */
    public void die() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".die()");
        logger.depthM();
        if (getIamon() != null) {
            getIamon().setContain(null);
        }
        setIamon(null);
        this.release();
        this.setAlive(false);
    }


    /**
     * @param f
     * @return
     */
    //Field f: ahová mozog az egység
    //Mozgást valósít meg false-al tér vissza ha olyan mezőre próbál lépni ami foglalt
    public boolean move(Field f) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".move(" + f.toString() + ")");
        if (f.getContain() == null)//Léphetek-e oda?
        {
            boolean flag;
            Field temp = this.getIamon();
            f.setContain(this);//Teleportnál stepp meghívása ami átállítja az IAMONT
            flag = temp == this.getIamon();//ha nem teleportált
            temp.setContain(null);
            if (this.getHand2() != null) {
                this.getHand2().move(temp);
            }
            logger.depthM();
            if (flag) {
                this.setIamon(f);
            }

            return true;
        } else {
            logger.depthM();
            return false;
        }
    }


    /**
     *
     */
    //A sor felbomlásáért felelős osztály
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

    /**
     * @return
     */
    public boolean isAlive() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".isAlive()" + ")");
        logger.depthM();
        return isAlive;
    }

    /**
     * @param alive
     */
    public void setAlive(boolean alive) {
        logger.depthP();
        logger.writeMessage(this.toString() + "setAlive(" + alive + ")");
        logger.depthM();
        isAlive = alive;
    }
}
