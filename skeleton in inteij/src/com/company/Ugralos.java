package com.company;



public class Ugralos extends Panda {
	public void Action(Csoki cs) {
		logger.depthP();
		logger.writeMessage(this.toString()+".Action("+cs.toString()+")");
		move(cs);
		logger.depthM();
	}//Ha meghallja ugrik
}
