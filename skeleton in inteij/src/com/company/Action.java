package com.company;

public interface Action { //Interface visitor pattern-hez
	public void action(Csoki cs);//Csak a Csokiautómata (Csoki) valósítja meg
	
	public void action(Jatek j);//Csak a Játékgép (Jatek) valósítja meg
	
	public void action(Fotel f);//Csak a Fotel (Fotel) valósítja meg
}
