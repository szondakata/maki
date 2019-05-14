package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * A controlt megvalosito osztaly
 */
public class Control {
    /**
     * determinisztikus vagy nemdeterminisztikus jatekmod
     */
    public static boolean isRandom = true;
    /**
     * a jatekban levo orangutanok szama
     */
    public int orangCount = 0;
    /**
     * Logger osztaly
     */
    protected Logger logger;
    /**
     * A jatekban levo orangutanok listaja
     */
    ArrayList<Orangutan> orangutans = new ArrayList<>();
    /**
     * A jatekban levo pandak listaja
     */
    ArrayList<Panda> pandas = new ArrayList<>();
    /**
     * A jatekban levo tergyak listaja
     */
    ArrayList<Field> items = new ArrayList<>();
    /**
     * A jatekter bejarata
     */
    Entry e;
    /**
     * Elso jatekos
     */
    Player p1;
    /**
     * Masodik jatekos
     */
    Player p2;
    /**
     * A jatekban levo pandak szama
     */
    int pandaCount;


    /**
     * Letrejon az osztaly es egy logger
     */
    public Control() {
        logger = new Logger();
    }

    /** A parameterben kapott orangutant a parameterben kapott csempere mozgatja
     * @param myO orangutan
     * @param ToF csempe
     */
    public void move(Orangutan myO, Field ToF) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".move(" + myO.toString() + ", " + ToF.toString() + ")");
        if (!End()) {
            if (!myO.move(ToF)) {
                ToF.getContain().grab(myO);
            }
        } else {
            System.out.println("A játék már véget ért!");
        }
        logger.depthM();

    }

    /**
     * A jatekban levo koszalo (nem foglalt mancsu) pandakat a jatekmodtol fuggoen mozvatja (vagy helyben hagyja)
     */
    public void movePandas() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".movePandas()");
        if (!End()) {
            for (Panda panda : pandas) { //minden panda egy a vele szomszédos mezőre lép vagy ott marad
                if (panda.getHand1() != null) continue;
                if (isRandom) {
                    Random rand = new Random();
                    panda.move(panda.getIamon().getNei().get(rand.nextInt(panda.getIamon().getNei().size())));
                } else {
                    panda.move(panda.getIamon().getNei().get(2));
                }
            }
        }
        logger.depthM();
    }

    /**
     * A jatekban levo targyak a tippusuknak megfeleloen sorban sipolnak, csilingelnek vagy fotelek
     */
    public void updateItems() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".updateItems()");
        for (Field item : items) { //minden itemnek meghívódik az updateje
            if (item == null)
                items.remove(item);
            item.Update();

        }
        logger.depthM();
    }

    /**
     * TODO
     */
    public void Start() {//TODO
        logger.depthP();
        logger.writeMessage(this.toString() + ".Start()");
        logger.depthM();
    }

    /** A jatek vegez vizsgalo es azt befejezo fuggveny
     * @return true, ha vege a jateknak
     */
    public boolean End() {//TODO

        logger.depthP();
        logger.writeMessage(this.toString() + ".End()");
        pandaCount = 0;
        int aliveOrangutans = 0;

        for (Panda panda : pandas) {

            if (panda.isAlive)
                pandaCount++;
        }
        if (pandas.isEmpty())
            pandaCount = 1;

        for (Orangutan o : orangutans) {

            if (o.isAlive)
                aliveOrangutans++;
        }

        if (aliveOrangutans < orangCount || pandaCount < 1) {  //ha egy orángután meghal vagy elfogynak a pandák vége a játéknak
            System.out.println("Jatekos 1 pontszam: " + p1.getPoints() + "\nJatekos 2 pontszam: " + p2.getPoints());
            logger.depthM();
            return true;
        }
        logger.depthM();
        return false;
    }

    /** Sosem hasznalt Az orangutanok listajaval visszatero fuggveny
     * @return az orangutanok listaja
     */
    public ArrayList<Orangutan> getOrangutans() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getOrangutans()");
        logger.depthM();
        return orangutans;
    }

    /** A parameterben kapott orangutant az orangutanok listajaba helyezi
     * @param o orangutan
     */
    public void addOrangutan(Orangutan o) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".addOrangutans(" + o.toString() + ")");
        logger.depthM();
        this.orangutans.add(o);
    }

    /** A fuggveny a parameterben kapott orangutant torli az orangutanok listajabol
     * @param o orangutan
     */
    public void removeOrangutan(Orangutan o) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".removeOrangutan(" + o.toString() + ")");
        logger.depthM();
        orangutans.remove(o);
    }

    /** A pandak listajaval visszatero fuggveny
     * @return a pandak listaja
     */
    public ArrayList<Panda> getPandas() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getPandas()");
        logger.depthM();
        return pandas;
    }

    /** A fuggveny a paramtereben kapott pandat a pandak listajahoz adja
     * @param p a panda
     */
    public void addPanda(Panda p) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".addPanda(" + p.toString() + ")");
        logger.depthM();
        this.pandas.add(p);
    }

    /** A fuggveny a parameterben kapott pandat torli a pandak listajabol
     * @param p a panda
     */
    public void removePanda(Panda p) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".removePanda(" + p.toString() + ")");
        logger.depthM();
        pandas.remove(p);
    }

    /** A kijaratot megmutato fuggveny
     * @return a kijarat
     */
    public Entry getE() {
        return e;
    }


    /** A kijaratot beallito fuggveny
     * @param e a kijarat
     */
    public void setE(Entry e) {
        this.e = e;
    }

    /** Az elso jatekost megmutato fuggveny
     * @return az elso jatekos
     */
    public Player getP1() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getP1()");
        logger.depthM();
        return p1;
    }

    /** A parameterben kapott jatekost az elso jatekoskent beallito fuggveny
     * @param p1 a jatekos
     */
    public void setP1(Player p1) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setP1(" + (p1 == null ? "Null" : p1.toString()) + ")");
        logger.depthM();
        this.p1 = p1;
    }

    /** A masodik jatekost megmutato fuggveny
     * @return a masodik jatekos
     */
    public Player getP2() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getP2()");
        logger.depthM();
        return p2;
    }

    /** A parameterben kapott jatekost a masodik jatekoskent beallito fuggveny
     * @param p2 a jatekos
     */
    public void setP2(Player p2) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setP2(" + (p2 == null ? "Null" : p2.toString()) + ")");
        logger.depthM();
        this.p2 = p2;
    }
}
