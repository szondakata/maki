package com.company;


/**
 * Ijedős pandát megvalósító osztály
 */
public class Ijedos extends Panda {
	/** A paraméterben kapott játékautomata sípolására a panda megijed és elengedi a sort.
	 * @param j a játékautomata
	 */
	public void action(Jatek j) {//Ha megijed
		logger.depthP();
		logger.writeMessage(this.toString()+".Action("+j.toString()+")");
		if (getHand1() != null)
		{
			release();//Elengedi a kezeket
		}
		logger.depthM();
	}
}
