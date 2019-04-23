package com.company;


public class Orangutan extends Unit {
	private Player myPlayer;//Melyik játékos kapja a pontokat

	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}


	public void exit(Field f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".exit()");
		if (getHand2()!= null)
		{
			Field temp=getIamon();
			getHand2().exit(temp);
			Entry entry = Entry.getInstance();
			entry.getContain().die();
			move(entry);

			myPlayer.givePoints(1);//6 pont cause whynot
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
