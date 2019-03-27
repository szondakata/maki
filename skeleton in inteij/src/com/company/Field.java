package com.company;
import java.util.ArrayList;

public class Field {
	private Control map;

	public Unit getContain() {
		return contain;
	}

	public void setContain(Unit contain) {
		this.stepped(contain);
		this.contain = contain;
	}

	private Unit contain;
	private ArrayList<Field> nei;
	public void Update() {
	}//üres done
	
	public void stepped(Unit u) {}//üres done

	public Control getMap() {
		return map;
	}

	public void setMap(Control map) {
		this.map = map;
	}

	public Unit getFieldOn() {
		return contain;
	}

	public void setFieldOn(Unit fieldOn) {
		this.contain = fieldOn;
	}

	public ArrayList<Field> getNei() {
		return nei;
	}

	public void setNei(ArrayList<Field> nei) {
		this.nei = nei;
	}
}
