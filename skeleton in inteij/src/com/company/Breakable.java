package com.company;

public class Breakable extends Field {
	private int remainLifetime;//Hátralévő élet

	@Override
	public void stepped(Orangutan u) {//Ha rálépnek meghívódik Unit u:aki rá lépett
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped(+"+u.toString()+")");
		if (u!= null)
		{
			remainLifetime--;
			if (remainLifetime<1)//Ha eltörik, aki rajta áll meghal
			{
				u.die();
			}
		}
		logger.depthM();
	}
	@Override
	public void stepped(Panda u) {//Ha rálépnek meghívódik Unit u:aki rá lépett
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped(+"+u.toString()+")");
		if (u!= null)
		{
			remainLifetime--;
			if (remainLifetime<1)//Ha eltörik, aki rajta áll meghal
			{
				u.die();
			}
		}
		logger.depthM();
	}
}
