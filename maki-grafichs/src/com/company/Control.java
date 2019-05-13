package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Control {
    public int orangutanCount;
    public boolean isRandom=true;
    ArrayList<Orangutan> orangutans = new ArrayList<>();
    ArrayList<Panda> pandas = new ArrayList<>();
    ArrayList<Field> items = new ArrayList<>();
    Entry e;
	protected Logger logger;
    Player p1;
    Player p2;
    int pandaCount;

	public Control()
	{
		logger=new Logger();
	}

    public void move(Orangutan myO, Field ToF) {//Eltérő
		logger.depthP();
        logger.writeMessage(this.toString()+".move("+myO.toString()+", "+ToF.toString()+")");
        if (!End()) {
            if (!myO.move(ToF)) {
                ToF.getContain().grab(myO);//Szekvencia orángután-move else ág kiemelve

                Unit temp;
                temp = ToF.getContain();//Panda
                myO.getIamon().setContain(temp);//Orángután helyére a pandát
                temp.setIamon(myO.getIamon());
                myO.setIamon(ToF);//Orángután a panda helyére
                ToF.setContain(myO);//Az újra az orángutánt
            }
        } else {
            System.out.println("A játék már véget ért!");
        }
		logger.depthM();

    }

    public void movePandas() {
		logger.depthP();
        logger.writeMessage(this.toString()+".movePandas()");
        if (!End())
		{
            for (Panda panda : pandas) { //minden panda egy a vele szomszédos mezőre lép vagy ott marad
                if(isRandom) {
                    Random rand = new Random();
                    panda.move(panda.getIamon().getNei().get(rand.nextInt(panda.getIamon().getNei().size())));
                }else{
                    panda.move(panda.getIamon().getNei().get(2));
                }
            }
        }
		logger.depthM();

    }

    public void updateItems() {
		logger.depthP();
        logger.writeMessage(this.toString()+".updateItems()");
        for (Field item : items) { //minden itemnek meghívódik az updateje
            item.Update();

        }
		logger.depthM();
    }

    public void Start() {//TODO
		logger.depthP();
        logger.writeMessage(this.toString()+".Start()");
        logger.depthM();
    }

    public boolean End() {//TODO

		logger.depthP();
        logger.writeMessage(this.toString()+".End()");
        pandaCount=0;

        for (Panda panda:pandas) {

            if(panda.isAlive)
                pandaCount++;

        }

        if (orangutans.get(0).isAlive && orangutans.get(1).isAlive && pandaCount < 1) {  //ha egy orángután meghal vagy elfogynak a pandák vége a játéknak
            System.out.println("Jatekos 1 pontszam: " + p1.getPoints() + "\nJatekos 2 pontszam: " + p2.getPoints());
			logger.depthM();
            return true;
        }
		logger.depthM();
        return false;
    }

    public ArrayList<Orangutan> getOrangutans() {
		logger.depthP();
        logger.writeMessage(this.toString()+".getOrangutans()");
        logger.depthM();
        return orangutans;
    }

    public void addOrangutan(Orangutan o) {
		logger.depthP();
        logger.writeMessage(this.toString()+".addOrangutans("+o.toString()+")");
        logger.depthM();
        this.orangutans.add(o);
    }

    public void removeOrangutan(Orangutan o) {
		logger.depthP();
        logger.writeMessage(this.toString()+".removeOrangutan("+o.toString()+")");
        logger.depthM();
        orangutans.remove(o);
    }


    public ArrayList<Panda> getPandas() {
		logger.depthP();
        logger.writeMessage(this.toString()+".getPandas()");
        logger.depthM();
        return pandas;
    }

    public void addPanda(Panda p) {
		logger.depthP();
        logger.writeMessage(this.toString()+".addPanda("+p.toString()+")");
        logger.depthM();
        this.pandas.add(p);
    }

    public void removePanda(Panda p) {
		logger.depthP();
        logger.writeMessage(this.toString()+".removePanda("+p.toString()+")");
        logger.depthM();
        pandas.remove(p);
    }

    public Entry getE() {
        return e;
    }

    public void setE(Entry e) {
        this.e = e;
    }

    public Player getP1() {
		logger.depthP();
        logger.writeMessage(this.toString()+".getP1()");
        logger.depthM();
        return p1;
    }

    public void setP1(Player p1) {
		logger.depthP();
        logger.writeMessage(this.toString()+".setP1("+(p1==null ? "Null" : p1.toString())+")");
        logger.depthM();
        this.p1 = p1;
    }

    public Player getP2() {
		logger.depthP();
        logger.writeMessage(this.toString()+".getP2()");
        logger.depthM();
        return p2;
    }

    public void setP2(Player p2) {
		logger.depthP();
        logger.writeMessage(this.toString()+".setP2("+(p2==null ? "Null": p2.toString())+")");
        logger.depthM();
        this.p2 = p2;
    }
}
