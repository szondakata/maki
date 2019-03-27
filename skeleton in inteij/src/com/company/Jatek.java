package com.company;
import java.util.Random;



public class Jatek extends Field {

	Random rand = new Random();
	public void Update() {
		if (rand.nextBoolean()) return;//Random sípol
		for (Field field : getNei()) {//A mező szomszédjaira
			if (field.getContain()!=null)
			{
				field.getContain().action(this);//Sípol
			}
		}
		if (getContain() != null)//A mezőre
		{
			getContain().action(this);//Sípol
		}
	}
}
