package com.company;

public class Breakable extends Field {
	private int remainLifetime=5;//Hátralévő élet
	public int getRemainLifetime() {
		return remainLifetime;
	}

	public void setRemainLifetime(int remainLifetime) {
		this.remainLifetime = remainLifetime;
	}

	@Override
	public void stepped(Unit u) {//Ha rálépnek meghívódik Unit u:aki rá lépett
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped(+"+(u==null ? "null" : u.toString()) +"  ");
		if (u!= null)
		{
			remainLifetime--;
			if (remainLifetime<1)//Ha eltörik, aki rajta áll meghal
			{
				u.die();
				for (Field f:getNei()) {		//Törli a hozzátartozó szomszédi kapcsolatokat a vele szomszédos mezőkben
					f.getNei().remove(this);
				}
				setNei(null);

			}
		}
		logger.depthM();
	}

	@Override
	public void Update() {
		if(remainLifetime<=0)
			stepped(new Panda());
	}
}
