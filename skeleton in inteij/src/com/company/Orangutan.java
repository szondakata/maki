package com.company;


public class Orangutan extends Unit {
	private Player myPlayer;//Melyik játékos kapja a pontokat

	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}


	public void exit(Field f) {
		if (getHand2()!= null)
		{
			Field temp=getIamon();
			setIamon(null);
			move(f);
			getHand2().exit(temp);
			f.getContain().die();
			move(f);

			myPlayer.givePoints(6);//6 pont cause whynot
		}
	}


	@Override
	public void action(Csoki cs) {}

	@Override
	public void action(Jatek j) {}

	@Override
	public void action(Fotel f) {}
}
