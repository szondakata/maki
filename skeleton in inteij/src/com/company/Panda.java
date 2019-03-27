package com.company;


public class Panda extends Unit {

	//Orángután hívja meg a lánc kialakításához
	//Orangutan orangutan: Hívó orángután
	public void grab(Orangutan orangutan) {
		Unit p2 = orangutan.getHand2();
		setHand2(p2);
		if (p2 != null)
		{
			p2.setHand1(this);
		}
		orangutan.setHand2(this);
		this.setHand1(orangutan);
	}
	
	public void exit(Field f) {
		if(getHand1()==null)
			return;
		else{
			Field temp=getIamon();
			move(f);
			die();
			if (getHand2()!=null)
				getHand2().exit(temp);
		}

	}


	@Override
	public void action(Csoki cs) {}

	@Override
	public void action(Jatek j) {}

	@Override
	public void action(Fotel f) {}
}
