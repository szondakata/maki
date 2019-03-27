package com.company;


import java.util.Random;

public class Almos extends Panda {

	private int energy;
	private boolean isEnergic =true;
	Random rand = new Random();

	public Almos() {
		energy = rand.nextInt(4);
	}

	public void Action(Fotel f) {//Csak az Álmos panda reagál rá
		if(!isEnergic) { //ha fáradt leül

			if (getHand1() != null) {
				release();
				move(f);
				if (energy == 0) {
					isEnergic = !isEnergic;
					energy = rand.nextInt(4);
					energy++; //mivel leült ezért töltődik az energiája
					if(energy==5) //ha kipihenti magát akkor újra energikus lesz
						isEnergic =true;
				}
			}
		}
	}

	@Override
	public boolean move(Field f)
	{
		if (isEnergic) {  //ha nem fárasdt lép
			energy--;     //elkezd fáradni
			if(energy<1) //ha elfogy az energiája akkor elfárad
				isEnergic=false;
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
