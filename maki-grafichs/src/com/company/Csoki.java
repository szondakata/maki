package com.company;

import java.util.Random;

/**
 * A csokiautomata működéséért felelős osztály
 */
public class Csoki extends Field {

	/**
	 * Randomszámgenerátor
	 */
	Random rand = new Random();

	/**
	 * A függvény 1-nél kisebb valószínűséggel értesíti a csempe szomszédait, hogy a csokiautomata sípolt,
	 * azaz meghívja paraméterként saját magával a szomszédos csempéken álló egységek action függvényét
	 */
	public void Update() {
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		if (rand.nextInt()%4!=0){logger.depthM(); return;}//Random sípol
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
