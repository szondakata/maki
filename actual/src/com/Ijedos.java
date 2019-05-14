package com.company;


/**
 * Az ijedos pandat megvalosito osztaly
 */
public class Ijedos extends Panda {

	/** A panda reakciojat a parameterben kapott jatekautomataval leiro fuggveny
	 * @param j a jatekautomata
	 */
	public void action(Jatek j) {
		logger.depthP();
		logger.writeMessage(this.toString()+".Action("+j.toString()+")");
		if (getHand1() != null)
		{
			release();
		}
		logger.depthM();
	}
}
