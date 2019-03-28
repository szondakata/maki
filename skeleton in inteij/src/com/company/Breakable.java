package com.company;

public class Breakable extends Field {
	private int remainLifetime;//Hátralévő élet


	public void stepped(Unit u) {//Ha rálépnek meghívódik Unit u:aki rá lépett
		if (u!= null)
		{
			remainLifetime--;
			if (remainLifetime<1)//Ha eltörik, aki rajta áll meghal
			{
				u.die();
			}
		}
	}
}
