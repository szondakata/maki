package com.company;


/**
 * A fotelt megvalosito osztaly
 */
public class Fotel extends Field {


	/**
	 * A fotel ertesiti a rajta es a szomszedain allokat, hogyha ures,
	 * es bele lehet ulni ha pedig nem ures, akkor a benne ulo valaki kevesbe lesz faradt.
	 */
	public void Update() {
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		if (getContain()==null)
		{
			for (Field field : getNei()) {
				if (field.getContain()!=null)
				{
					field.getContain().action(this);
				}
			}
		}
		else
		{
			getContain().action(this);
		}
		logger.depthM();
	}
}
