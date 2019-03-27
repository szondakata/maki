package com.company;


import java.util.Random;

public class Almos extends Panda {

	private int energy;
	private boolean isTired=true;
	Random rand = new Random();

	public Almos() {
		energy = rand.nextInt(4);
	}

	public void Action(Fotel f) {//Csak az Álmos panda reagál rá
		if(energy==0) {
			if (getHand1() != null) {
				release();
				move(f);
				if (energy == 0) {
					isTired = !isTired;
					energy = rand.nextInt(4);
				}
			}
		}else{
			energy--;
			if(energy==5)
				isTired=true;

		}
	}

	@Override
	public boolean move(Field f)
	{
		if (isTired) {
			energy--;
			if (f.getContain() == null)//Léphetek-e oda?
			{

				f.setContain(this);
				Field temp = this.getIamon();
				temp.setContain(null);
				if (this.getHand1() != null) {
					this.getHand1().move(temp);
				}
				this.setIamon(f);
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

}
