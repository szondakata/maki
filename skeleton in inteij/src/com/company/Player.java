package com.company;

public class Player {
	private int points;
	private Orangutan myOran;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Orangutan getMyOran() {
		return myOran;
	}

	public void setMyOran(Orangutan myOran) {
		this.myOran = myOran;
	}

	public void givePoints(int i) {
		points += i;
	}//Pontok hozzáadása
}
