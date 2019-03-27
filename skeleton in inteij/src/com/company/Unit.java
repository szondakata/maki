package com.company;


public abstract class Unit implements Action {

	private Field iamon;//Melyik mezőn vagyok
	private Unit hand1;//"Hátra" mutató kéz
	private Unit hand2;//"Előre" mutató kéz
	private boolean isAlive=true;


	public Field getIamon() {
		return iamon;
	}
	public void setIamon(Field iamon) {
		this.iamon = iamon;
	}

	public Unit getHand1() {
		return hand1;
	}
	public void setHand1(Unit hand1) {
		this.hand1 = hand1;
	}

	public Unit getHand2() {
		return hand2;
	}
	public void setHand2(Unit hand2) {
		this.hand2 = hand2;
	}

	
	public void grab(Unit u) {} //Üres osztály
	
	public void exit() {}
	

	//Field f: ahová mozog az egység
	//Mozgást valósít meg false-al tér vissza ha olyan mezőre próbál lépni ami foglalt
	public boolean move(Field f)
	{
		if (f.getContain()==null)//Léphetek-e oda?
		{
			f.setContain(this);
			Field temp = this.getIamon();
			temp.setContain(null);
			if (this.getHand1() != null)
			{
				this.getHand1().move(temp);
			}
			this.setIamon(f);
			return true;
		}
		else{return  false;}
	}

	//A sor felbomlásáért felelős osztály
	public void release() {
        if(getHand1() != null)
        {
            getHand1().setHand2(null);
            setHand1(null);
        }
        if(getHand2() != null)
        {
            getHand2().release();
        }
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
