package com.company;
import java.util.ArrayList;

public class Field {
	private Unit contain;//A mezőn lévő unit
	private ArrayList<Field> nei;//A mező szomszédai



	public Unit getContain() {
		return contain;
	}
	public void setContain(Unit contain) {
		this.stepped(contain);
		this.contain = contain;
	}

	public void setNei(ArrayList<Field> nei) {
		this.nei = nei;
	}
	public ArrayList<Field> getNei() {
		return nei;
	}

<<<<<<< HEAD

	public void Update() {
	}//Nem csinál semmit
	public void stepped(Unit u) {}//Nem csinál semmit
=======
	public void setNei(ArrayList<Field> nei) {
		this.nei = nei;
	}
<<<<<<< HEAD
	public void addNei(Field f){
		nei.add(f);
		f.addNei(this);
	}
>>>>>>> sárosi
=======
>>>>>>> parent of 68ac3cb... exit kills
}
