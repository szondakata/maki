package com.company;
import java.util.Random;

/**
 * Az almos pandat megvalosito osztaly
 */
public class Almos extends Panda {

	/**
	 * A panda energiaja
	 */
	private int energy;
	/**
	 * A panda energikus-e
	 */
	private boolean isEnergic = true;
	Random rand = new Random();

	/**
	 * Alapesetben a pandanak 4 az ebersege
	 */
	public Almos() {
		energy=4;
		//energy = rand.nextInt(4);
	}

	/** A panda reakciojat a parameterben kapott fotellel leiro fuggveny
	 * @param f a fotel
	 */
	public void action(Fotel f) {//Only sleepy panda reacts
		logger.depthP();
		logger.writeMessage(this.toString()+".Action("+f.toString()+")");
		if(!isEnergic) {
			if (getHand1() != null) {
				release();
				move(f);
				if (energy == 0) {
					isEnergic = !isEnergic;
					//energy = rand.nextInt(4);
					energy=4;
					energy++;
					if(energy==5)
						isEnergic =true;
				}
			}
		}
		logger.depthM();
	}

	/** A fuggveny a parameterben kapott csempere mozgatja a pandat
	 * @param f a kivant csempe
	 * @return sikeres volt a mozgas
	 */
	@Override
	public boolean move(Field f)
	{
		logger.depthP();
		logger.writeMessage(this.toString()+".move("+f.toString()+")");
		if (isEnergic) {
			energy--;
			if(energy<1)
				isEnergic=false;
			if (f.getContain() == null)
			{

				f.setContain(this);
				Field temp = this.getIamon();
				temp.setContain(null);
				if (this.getHand1() != null) {
					this.getHand1().move(temp);
				}
				this.setIamon(f);
				logger.depthM();
				return true;
			} else {
				logger.depthM();
				return false;
			}
		}
		logger.depthM();
		return false;
	}

}
