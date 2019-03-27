package com.company;

import java.util.Random;

public class Csoki extends Field {

	Random rand = new Random();

	public void Update() {
		if (rand.nextBoolean()) return;//Random sípol
			for (Field field : getNei()) {
				if (field.getContain()!=null)
				{
					field.getContain().action(this);
				}
			}
			if (getContain() != null)
			{
				getContain().action(this);
			}
	}
}
