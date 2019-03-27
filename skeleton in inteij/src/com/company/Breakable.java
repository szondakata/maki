package com.company;

public class Breakable extends Field {
	private int remainLifetime;


	public void stepped(Unit u) {
		if (u!= null)
		{
			remainLifetime--;
			if (remainLifetime<1)
			{
				u.die();
			}
		}
	}
}
