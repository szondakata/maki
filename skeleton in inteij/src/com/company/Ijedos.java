package com.company;


public class Ijedos extends Panda {
	public void Action(Jatek j) {//Ha megijed
		logger.depthP();
		logger.writeMessage(this.toString()+".Action("+j.toString()+")");
		if (getHand1() != null)
		{
			release();//Elengedi a kezeket
		}
		logger.depthM();
	}
}
