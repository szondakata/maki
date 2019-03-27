package com.company;

import java.util.ArrayList;

public  class Control {

	ArrayList<Orangutan> orangutans=new ArrayList<>();
	ArrayList<Panda> pandas=new ArrayList<>();
	Entry e;
	Player p1;
	Player p2;



	public void init() {//TODO
	}
	
	public void move(Orangutan myO, Field ToF) {//Eltérő
		if(!myO.move(ToF))
		{
			ToF.getContain().grab(myO);//Szekvencia orángután-move else ág kiemelve
		}
	}
	
	public void Start() {//TODO
	}
	
	public void End() {//TODO

	}

	public ArrayList<Orangutan> getOrangutans() {
		return orangutans;
	}

	public void addOrangutan(Orangutan o) {
		this.orangutans.add(o);
	}

	public void removeOrangutan(Orangutan o){
		orangutans.remove(o);
	}


	public ArrayList<Panda> getPandas() {
		return pandas;
	}

	public void addPanda(Panda p) {
		this.pandas.add(p);
	}

	public void removePanda (Panda p) {
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
