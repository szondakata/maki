package com.company;
import java.util.Random;


/**
 * A jatekautomatat leiro osztaly
 */
public class Jatek extends Field {

	Random rand = new Random();

	/**
	 * A jatekautomata szol a szomszedain es onmagan allo egysegeknek, ha sipol
	 * A sipolas egynel kisebb valoszinuseggel tortenik meg
	 */
	public void Update() {
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		if (Control.isRandom==true?rand.nextBoolean():false) {logger.depthM(); return;}//Random sípol
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
