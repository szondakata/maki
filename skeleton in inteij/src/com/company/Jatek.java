package com.company;
import java.util.Random;



public class Jatek extends Field {

	Random rand = new Random();
	public void Update() {
		if (rand.nextBoolean())return;

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
