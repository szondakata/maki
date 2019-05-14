package com.company;


public abstract class Unit implements Action {

	public String ID;
	boolean isAlive = true;
	protected Logger logger;
	private Field iamon;//Melyik mezőn vagyok
	private Unit hand1;//"Hátra" mutató kéz
	private Unit hand2;//"Előre" mutató kéz

	public Unit()
	{
		logger=new Logger();
	}
public Field getIamon() {
		logger.depthP();
		logger.writeMessage(this.toString()+".getIamon()");
		logger.depthM();
		return iamon;

	}
	public void setIamon(Field iamon) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setIamon("+(iamon == null ? "null" : iamon.toString())+")");
		logger.depthM();
		this.iamon = iamon;
	}

	public Unit getHand1() {

		logger.depthP();
		logger.writeMessage(this.toString()+".getHand1()");
		logger.depthM();
		return hand1;
	}
	public void setHand1(Unit hand1) {

		logger.depthP();
		logger.writeMessage(this.toString()+".setHand1("+hand1+")");
		logger.depthM();
		this.hand1 = hand1;
	}

	public Unit getHand2() {
		logger.depthP();
		logger.writeMessage(this.toString()+".getHand2()");
		logger.depthM();
		return hand2;
	}
	public void setHand2(Unit hand2) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setHand2("+hand2+")");
		logger.depthM();
		this.hand2 = hand2;
	}

	
	public void grab(Unit u) {
		logger.depthP();
		logger.writeMessage(this.toString()+".grab("+u.toString()+")");
		logger.depthM();
	} //Üres osztály
	
	public void exit(Field field) {
		logger.depthP();
		logger.writeMessage(this.toString()+".exit("+(field==null ? "Null": field.toString())+")");
		logger.depthM();
	}
	
	public void die() {
		logger.depthP();
		logger.writeMessage(this.toString()+".die()");
		logger.depthM();
		if(getIamon()!=null) {
            getIamon().setContain(null);
        }
		setIamon(null);

		this.release();

		this.setAlive(false);

	}
	
	

	//Field f: ahová mozog az egység
	//Mozgást valósít meg false-al tér vissza ha olyan mezőre próbál lépni ami foglalt
	public boolean move(Field f)
	{
		logger.depthP();
		logger.writeMessage(this.toString()+".move("+f.toString()+")");
		if (f.getContain()==null)//Léphetek-e oda?
		{
			boolean flag;
			Field temp = this.getIamon();
			f.setContain(this);//Teleportnál stepp meghívása ami átállítja az IAMONT
			flag = temp == this.getIamon();//ha nem teleportált
			temp.setContain(null);
			if (this.getHand2() != null)
			{
				this.getHand2().move(temp);
			}
			logger.depthM();
			if (flag) {this.setIamon(f);}

			return true;
		}
		else{logger.depthM();return  false;}
	}
	

	//A sor felbomlásáért felelős osztály
	public void release() {
		logger.depthP();
		logger.writeMessage(this.toString()+".release()");
        if(getHand1() != null)
        {
            getHand1().setHand2(null);
            setHand1(null);
        }
        if(getHand2() != null)
        {
            getHand2().release();
        }
        logger.depthM();
	}

	public boolean isAlive() {
		logger.depthP();
		logger.writeMessage(this.toString()+".isAlive()"+")");
		logger.depthM();
		return isAlive;
	}

	public void setAlive(boolean alive) {
		logger.depthP();
		logger.writeMessage(this.toString()+"setAlive("+alive+")");
		logger.depthM();
		isAlive = alive;
	}
}
