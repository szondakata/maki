package com.company;

/**
 * Az uhralos pandat magvalosito osztaly
 */
public class Ugralos extends Panda {
	/** A panda reakciojat a parameterben kapott csokiautomataval leiro fuggveny
	 * @param cs a csokiautomata
	 */
	public void action(Csoki cs) {
		logger.depthP();
		logger.writeMessage(this.toString()+".Action("+cs.toString()+")");

		move(cs);
		logger.depthM();
	}
}
