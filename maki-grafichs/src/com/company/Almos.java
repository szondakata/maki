package com.company;


import java.util.Random;

/**
 *
 */
public class Almos extends Panda {

    /**
     * Randomszámgenerátor
     */
    Random rand = new Random();
    /**
     * Jelzi a panda energiáját.
     */
    private int energy;
    /**
     * Energikusság-jelző flag
     */
    private boolean isEnergic = true;

    /**
     * Paraméter nélküli konstruktor. A panda létrejön 4 egység energiával
     */
    public Almos() {

        energy = 4;
        //energy = rand.nextInt(4);
    }

    /** A paraméterben kapott fotelbe a panda leül, ha fáradt. Ha benne ül, akkor pihen, ha kipihente magát, energikus lesz.
     * @param f fotel
     */
    public void action(Fotel f) {//Csak az Álmos panda reagál rá
        logger.depthP();
        logger.writeMessage(this.toString() + ".Action(" + f.toString() + ")");
        if (!isEnergic) { //ha fáradt leül
            if (getHand1() != null) {
                release();
                move(f);
                if (energy == 0) {
                    isEnergic = !isEnergic;
                    //energy = rand.nextInt(4);
                    energy = 4;
                    energy++; //mivel leült ezért töltődik az energiája
                    if (energy == 5) //ha kipihenti magát akkor újra energikus lesz
                        isEnergic = true;
                }
            }
        }
        logger.depthM();
    }

    /**
     * @param f csempe
     * @return
     */
    @Override
    public boolean move(Field f) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".move(" + f.toString() + ")");
        if (isEnergic) {  //ha nem fárasdt lép
            energy--;     //elkezd fáradni
            if (energy < 1) //ha elfogy az energiája akkor elfárad
                isEnergic = false;
            if (f.getContain() == null)//Léphetek-e oda?
            {
                f.setContain(this);
                Field temp = this.getIamon();
                temp.setContain(null);
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
        logger.depthM();
        return false;
    }
}
