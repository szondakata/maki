package com.company;

/**
 * Az álmos panda a játék egy speciális panda szereplője.
 */
public class Almos extends Panda {
    /**
     * A panda energiája
     */
    private int energy;
    /**
     * A pihenés állapotának flagje
     */
    private boolean isRelaxing = false;

    /**
     * Konstruktor, a panda energiáját 0-ra inicializálja
     */
    public Almos() {
        energy = 0;
    }
    /** Ha a panda fáradt, leül a paraméterben kapott fotelbe és pihenni kezd. Ha energikus, akkor tovább megy.
     * Ha épp a fotelben ül, de még fáradt, akkor növekszik az energiája.
     * @param f Fotel
     */
    public void action(Fotel f) {//Csak az Álmos panda reagál rá
        logger.depthP();
        logger.writeMessage(this.toString() + ".Action(" + f.toString() + ")");

        if (energy<0)
        {
            release();
            move(f);
            isRelaxing = true;
            energy = 0;
        }
        if (energy>=5)
        {
            isRelaxing = false;
        }
        if (isRelaxing){energy++;}
        logger.depthM();
    }

    /** Ha a panda pihen, helyben marad. Ha a panda nem pihen, csökken az
     * energiája, és a paraméterben kapott mezőre lép.
     * @param f A mező
     * @return True, ha a mozgás megtörtént. False, ha a mozgás nem lehetséges
     */
    @Override
    public boolean move(Field f) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".move(" + f.toString() + ")");
        logger.depthM();
        if (isRelaxing) {
            return false;
        } else {
            energy--;
            return super.move(f);
        }
    }
}
