package com.company;


public class Orangutan extends Unit {
	private Player myPlayer;//Melyik játékos kapja a pontokat

	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}


	public void exit(Field f) {
		logger.depthP();
		logger.writeMessage(this.toString()+".exit()");
		Entry entry = Entry.getInstance();
		if (entry.getContain() != null){
			entry.getContain().die();
		}
		if (getHand2()!= null)
		{
			Field temp=getIamon();
			int pontok = 0;
			for(Unit u = this;u.getHand2() != null;u = u.getHand2() )
			{
				pontok++;
			}
			myPlayer.givePoints(pontok);//1 pont cause whynot
			getHand2().exit(temp);
		}
		this.release();
		this.move(entry);
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
