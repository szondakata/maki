package com.company;


public class Almos extends Panda {
	public void Action(Fotel f) {
		if (getHand1() != null)
		{
			release();
			move(f);
		}
	}
}
