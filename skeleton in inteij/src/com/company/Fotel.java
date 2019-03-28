package com.company;


public class Fotel extends Field {
	public void Update() {//Fotel hirdeti magát hogy itt van
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		if (getContain()==null)
		{
			for (Field field : getNei()) {//A szomszédain
				if (field.getContain()!=null)
				{
					field.getContain().action(this);
				}
			}
		}
		else
		{
			getContain().action(this); //A fáradtság csökkentéséhez
		}
		logger.depthM();
	}
}
