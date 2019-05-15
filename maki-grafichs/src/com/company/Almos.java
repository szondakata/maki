package com.company;


import java.util.Random;

public class Almos extends Panda {
    int wait = 0;
    private int energy;
    private boolean isEnergic = true;
    Random rand = new Random();
    private boolean isRelaxing = false;

    public Almos() {

        energy = 0;
        //energy = rand.nextInt(4);
    }

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
