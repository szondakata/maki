package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * A játékot mozgató modell logikáját és adatait tartalmazó osztály
 */
public class Control {
    /**
     * A véletlen játékmód flagje
     */
    public boolean isRandom = true;
    /**
     * Logger osztály
     */
    protected Logger logger;
    /**
     * Az játékban szereplő orángutánok listája
     */
    ArrayList<Orangutan> orangutans = new ArrayList<>();
    /**
     * A játékban szereplő pandák listája
     */
    ArrayList<Panda> pandas = new ArrayList<>();
    /**
     * A pályát alkotó csempék listája
     */
    ArrayList<Field> items = new ArrayList<>();
    /**
     * A pálya bejárata
     */
    Entry e;
    /**
     * Az első játékos
     */
    Player p1;
    /**
     * A második játékos
     */
    Player p2;
    /**
     * A pandák száma
     */
    int pandaCount;
    /**
     * Konstruktor, amely létrehozza a loggert.
     */
    public Control() {
        logger = new Logger();
    }

    /** A függvény hatására a paraméterben kapott orángután a paraméterben kapott csempére kísérel meg lépni.
     * Ha az orángután a kijáratra lépne, és áll ott egység, a kijáraton álló egység meghal. Ha az orángután
     * nem tud a kívánt mezőre lépni (a mező foglalt), akkor megpróbálja elkapni a mezőn álló egységet.
     * Ha a játéknak vége szakad, a függvéyn standard outputon jelzi ezt.
     * @param myO az orángután
     * @param ToF a csempe
     */
    public void move(Orangutan myO, Field ToF) {//Eltérő
        logger.depthP();
        logger.writeMessage(this.toString() + ".move(" + myO.toString() + ", " + ToF.toString() + ")");
        if (!End()) {
            if (!myO.move(ToF)) {
                if (ToF == Entry.getInstance()) {
                    ToF.getContain().die();
                    myO.move(ToF);
                } else {
                    ToF.getContain().grab(myO);//Szekvencia orángután-move else ág kiemelve
                }
            }
        } else {
            System.out.println("A játék már véget ért!");
        }
        logger.depthM();
    }

    /**
     * A játékban szereplő összes olyan panda, amelyik nem sorban van (az első mancsa üres)
     * (1) Véletlen játékmódban egy véletlenszerű, a saját pozíciójával szomszédos mezőre kísérel meg lépni
     * (2) Nem véletlen játékmódban egy úton halad
     * Ha a játéknak vége szakad, a függvéyn standard outputon jelzi ezt.
     */
    public void movePandas() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".movePandas()");
        if (!End()) {
            for (Panda panda : pandas) { //minden panda egy a vele szomszédos mezőre lép vagy ott marad
                if (panda.getHand1() == null && panda.isAlive) {
                    if (isRandom) {
                        Random rand = new Random();
                        panda.move(panda.getIamon().getNei().get(rand.nextInt(panda.getIamon().getNei().size())));
                    } else {
                        panda.move(panda.getIamon().getNei().get(2));
                    }
                }
            }
        }
        logger.depthM();
    }

    /**
     * A játéktáblán lévő interaktív elemek interakcióba lépnek az egységekkel
     */
    public void updateItems() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".updateItems()");
        for (Field item : items) { //minden itemnek meghívódik az updateje
            item.Update();
        }
        logger.depthM();
    }

    public void Start() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".Start()");
        logger.depthM();
    }

    /** A függvény visszaadja, hogy vége van-e a játéknak
     * @return True, ha a játéknak vége. False, ha a játéknak nincs vége.
     */
    public boolean End() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".End()");
        pandaCount = 0;

        for (Panda panda : pandas) {
            if (panda.isAlive)
                pandaCount++;
        }
        if (!orangutans.get(0).isAlive || !orangutans.get(1).isAlive || pandaCount < 1) {  //ha egy orángután meghal vagy elfogynak a pandák vége a játéknak
            System.out.println("Jatekos 1 pontszam: " + p1.getPoints() + "\nJatekos 2 pontszam: " + p2.getPoints());
            logger.depthM();
            return true;
        }
        logger.depthM();
        return false;
    }

    public ArrayList<Orangutan> getOrangutans() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getOrangutans()");
        logger.depthM();
        return orangutans;
    }

    /** Az orángutánok listájához adja a paraméterben kapott orángutánt
     * @param o az orángután
     */
    public void addOrangutan(Orangutan o) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".addOrangutans(" + o.toString() + ")");
        logger.depthM();
        this.orangutans.add(o);
    }

    public void removeOrangutan(Orangutan o) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".removeOrangutan(" + o.toString() + ")");
        logger.depthM();
        orangutans.remove(o);
    }


    public ArrayList<Panda> getPandas() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getPandas()");
        logger.depthM();
        return pandas;
    }

    /** A pandák listájához adja a paraméterben kapott pandát
     * @param p a panda
     */
    public void addPanda(Panda p) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".addPanda(" + p.toString() + ")");
        logger.depthM();
        this.pandas.add(p);
    }

    public void removePanda(Panda p) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".removePanda(" + p.toString() + ")");
        logger.depthM();
        pandas.remove(p);
    }

    public Entry getE() {
        return e;
    }

    public void setE(Entry e) {
        this.e = e;
    }

    /** A függvény visszaadja az első játékost
     * @return az első játékos
     */
    public Player getP1() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getP1()");
        logger.depthM();
        return p1;
    }

    /** A függvény a paraméterben kapott játékost beállítja első játékosnak
     * @param p1 az első játékos
     */
    public void setP1(Player p1) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setP1(" + (p1 == null ? "Null" : p1.toString()) + ")");
        logger.depthM();
        this.p1 = p1;
    }

    /** A függvény visszaadja a második játékost
     * @return a második játékos
     */
    public Player getP2() {
        logger.depthP();
        logger.writeMessage(this.toString() + ".getP2()");
        logger.depthM();
        return p2;
    }

    /** A függvény a paraméterben kapott játékost beállítja második játékosnak
     * @param p2 a második játékos
     */
    public void setP2(Player p2) {
        logger.depthP();
        logger.writeMessage(this.toString() + ".setP2(" + (p2 == null ? "Null" : p2.toString()) + ")");
        logger.depthM();
        this.p2 = p2;
    }
}
