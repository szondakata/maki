package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Control {

    ArrayList<Orangutan> orangutans = new ArrayList<>();
    ArrayList<Panda> pandas = new ArrayList<>();
    ArrayList<Field> items = new ArrayList<>();
    Entry e;
    Player p1;
    Player p2;
    int pandaCount;

    public void move(Orangutan myO, Field ToF) {//Eltérő
        if (!End()) {
            if (!myO.move(ToF)) {
                ToF.getContain().grab(myO);//Szekvencia orángután-move else ág kiemelve
            }
        } else {
            System.out.println("A játék már véget ért!");
        }

    }

    public void movePandas() {
        if (End())
            return;
        else {
            for (Panda panda : pandas) { //minden panda egy a vele szomszédos mezőre lép vagy ott marad
                Random rand = new Random();
                panda.move(panda.getIamon().getNei().get(rand.nextInt(panda.getIamon().getNei().size())));

            }
        }

    }

    public void updateItems() {
        for (Field item : items) { //minden itemnek meghívódik az updateje
            item.Update();

        }
    }

    public void Start() {//TODO
    }

    public boolean End() {//TODO
<<<<<<< HEAD
        pandaCount=0;
=======
        int pandaCount=0;
>>>>>>> sárosi
        for (Panda panda:pandas) {
            if(panda.isAlive)
                pandaCount++;

        }
<<<<<<< HEAD
        if (orangutans.get(0).isAlive && orangutans.get(1).isAlive && pandaCount < 1) {  //ha egy orángután meghal vagy elfogynak a pandák vége a játéknak
=======
        if (orangutans.get(0).isAlive && orangutans.get(1).isAlive && pandas.size() < 1) {  //ha egy orángután meghal vagy elfogynak a pandák vége a játéknak
>>>>>>> sárosi
            System.out.println("Jatekos 1 pontszam: " + p1.getPoints() + "\nJatekos 2 pontszam: " + p2.getPoints());
            return true;
        }
        return false;
    }

    public ArrayList<Orangutan> getOrangutans() {
        return orangutans;
    }

    public void addOrangutan(Orangutan o) {
        this.orangutans.add(o);
    }

    public void removeOrangutan(Orangutan o) {
        orangutans.remove(o);
    }


    public ArrayList<Panda> getPandas() {
        return pandas;
    }

    public void addPanda(Panda p) {
        this.pandas.add(p);
    }

    public void removePanda(Panda p) {
        pandas.remove(p);
    }

    public Entry getE() {
        return e;
    }

    public void setE(Entry e) {
        this.e = e;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }
}
