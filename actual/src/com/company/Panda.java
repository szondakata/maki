package com.company;


/**
 * A pandat megvalosito osztaly
 */
public class Panda extends Unit {

	/** A fuggveny megfogja a parametereben kapott egyseg hatso mancsat.
	 * @param unit az egyseg
	 */
	public void grab(Unit unit) {
		logger.depthP();
		logger.writeMessage(this.toString()+".grab("+unit.toString()+")");
		Unit p2 = unit.getHand2();
		setHand2(p2);
		if (p2 != null)
		{
			p2.setHand1(this);
		}
		unit.setHand2(this);
		this.setHand1(unit);
		logger.depthM();
	}

	/** A fuggveny hatasara a panda elhagyja a palyat
	 * @param f a kijarat
	 */
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

	/** A parameterben kapott csokiautomata sipolasara az nem ugralos nem csinal semmit
	 * @param cs a csokiautomata
	 */
	@Override
	public void action(Csoki cs) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+cs.toString()+")");
		logger.depthM();
	}

	/** A parameterben kapott jatekautomata csilingelesere a nem ijedos nem csinal semmit
	 * @param j a jatekgep
	 */
	@Override
	public void action(Jatek j) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+j.toString()+")");
		logger.depthM();
	}


	/** A parameterben kapott fotel hatasara nem faradt panda nem csinal semmit sem
	 * @param f a fotel
	 */
	@Override
	public void action(Fotel f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+f.toString()+")");
		logger.depthM();
	}
}
