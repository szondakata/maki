package com.company;

import java.util.Random;

/**
 *
 */
public class Csoki extends Field {

	Random rand = new Random();

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
