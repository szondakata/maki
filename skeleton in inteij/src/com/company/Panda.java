package com.company;


public class Panda extends Unit {

	private Control pandas;//ez minek?

	public void grab(Orangutan orangutan) {//totál bullshit (a szerkesztő megjegyzése)
		Unit p2 = orangutan.getHand2();
		setHand2(p2);
		if (p2 != null)
		{
			p2.setHand1(this);
		}
		orangutan.setHand2(this);
		this.setHand1(orangutan);
	}
	
	public void exit() {
	}
	
	public void die() {
	}

	@Override
	public void action(Csoki cs) {

	}

	@Override
	public void action(Jatek j) {

	}

	@Override
	public void action(Fotel f) {

	}
}
