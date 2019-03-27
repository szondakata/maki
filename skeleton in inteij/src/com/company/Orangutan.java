package com.company;


public class Orangutan extends Unit {
	private Player myPlayer;//Melyik játékos kapja a pontokat

	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}


	public void exit() {
		if (getHand2()!= null)
		{
			getHand2().exit();
			myPlayer.givePoints(6);//6 pont cause whynot
		}
	}
	
	public void die() {
	}

	@Override
	public void action(Csoki cs) {}

	@Override
	public void action(Jatek j) {}

	@Override
	public void action(Fotel f) {}
}
