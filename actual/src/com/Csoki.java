package com.company;
import java.util.Random;

/**
 * A csokiautomatat megvalosito osztaly
 */
public class Csoki extends Field {

	Random rand = new Random();

	/**
	 * A szomszedos csempeken es a csempen allo egysegeket egynel kisebb valoszinuseggel
	 * ertesiti a fuggveny arrol, hogy ez egy csokiautomata
	 */
	public void Update() {
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		if (Control.isRandom==true?rand.nextBoolean():false){logger.depthM(); return;}
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
		logger.depthM();
	}
}
