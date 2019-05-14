package com.company;


public class Panda extends Unit {

	//Orángután hívja meg a lánc kialakításához
	//Orangutan animal: Hívó orángután
	public void grab(Unit unit) {
		logger.depthP();
		logger.writeMessage(this.toString()+".grab("+unit.toString()+")");
		this.release();
		Unit p2 = unit.getHand2();
		setHand2(p2);
		if (p2 != null)
		{
			p2.setHand1(this);
		}
		unit.setHand2(this);
		this.setHand1(unit);


		Unit temp,myO;
		myO = unit;
		Field ToF;
		ToF = this.getIamon();
		temp = ToF.getContain();//Panda
		myO.getIamon().setContain(temp);//Orángután helyére a pandát
		temp.setIamon(myO.getIamon());
		myO.setIamon(ToF);//Orángután a panda helyére
		ToF.setContain(myO);//Az újra az orángutánt


		logger.depthM();
	}
	
	public void exit(Field f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".exit()");
		if(getHand1()==null)
			return;
		else{
			move(f);
			Field temp=getIamon();
			die();
			if (getHand2()!=null)
				getHand2().exit(temp);
		}
		logger.depthM();

	}


	@Override
	public void action(Csoki cs) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+cs.toString()+")");
		logger.depthM();
	}

	@Override
	public void action(Jatek j) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+j.toString()+")");
		logger.depthM();
	}

	@Override
	public void action(Fotel f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+f.toString()+")");
		logger.depthM();
	}
}
