package com.company;


public class Panda extends Unit {

	//Orángután hívja meg a lánc kialakításához
	//Orangutan orangutan: Hívó orángután
	public void grab(Orangutan orangutan) {
		logger.depthP();
		logger.writeMessage(this.toString()+".grab("+orangutan.toString()+")");
		Unit p2 = orangutan.getHand2();
		setHand2(p2);
		if (p2 != null)
		{
			p2.setHand1(this);
		}
		orangutan.setHand2(this);
		this.setHand1(orangutan);
		logger.depthM()
	}
	
	public void exit(Field f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".exit()");

		if(getHand1()==null)
			return;
		else{
			Field temp=getIamon();
			move(f);
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
