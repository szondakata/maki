package com.company;
import java.util.Random;


/**
 * A játékautomatát és az alatta lévő csempét megvalósító osztály
 */
public class Jatek extends Field {
	/**
	 * Randomszámgenerátor
	 */
	Random rand = new Random();

	/**
	 * A játékautomata egynél kisebb valószínűséggel sípol (értesíti a rajta és szomszédain
	 * álló egységeket, hogy sípol)
	 */
	public void Update() {
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		if (rand.nextInt()%4!=0) {logger.depthM(); return;}//Random sípol
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
		logger.depthM();
	}
}
