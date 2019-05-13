package com.company;


/**
 * Az orangutant megvalosito osztaly
 */
public class Orangutan extends Unit {
	/**
	 * Az orangutant iranyito, un. sajat jatekos
	 */
	private Player myPlayer;

	/** A parameterben kapott jatekost beallitja a sajat jatekosnak
	 * @param myPlayer a jatekos
	 */
	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}

	/** A parameterben levo egyseget megprobalja megfogni a panda
	 * @param u az egyseg
	 */
	@Override
	public void grab(Unit u) {
		System.out.println("A megadott lépés nem lehetséges: a megadott mezőn orángután tartózkodik");
	}


	/** A fuggveny hatasara az orangutan elhagyja a palyat,
	 * egy pontot kap es maga utan huzza a mancsaban levoket
	 * ezt kovetoen megjelenik a bejaraton
	 *  @param f
	 */
	public void exit(Field f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".exit()");
		if (getHand2()!= null)
		{
			Field temp = getIamon();
			getHand2().exit(temp);
			Entry entry = Entry.getInstance();
			entry.getContain().die();
			move(entry);

			myPlayer.givePoints(1);//6 pont cause whynot
		}
		logger.depthM();
	}


	/** A parameterben kapott csokiautomata sipolasara az orangutan nem csinal semmit
	 * @param cs a csokiautomata
	 */
	@Override
	public void action(Csoki cs) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+cs.toString()+")");
		logger.depthM();
	}

	/** A parameterben kapott jatekautomata csilingelesere az orengutan nem csinal semmit
	 * @param j a jatekgep
	 */
	@Override
	public void action(Jatek j) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+j.toString()+")");
		logger.depthM();
	}


	/** A parameterben kapott fotel hatasara nem tortenik semmi
	 * @param f a fotel
	 */
	@Override
	public void action(Fotel f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".action("+f.toString()+")");
		logger.depthM();
	}
}
