package com.company;

/**
 *
 */
public class Player {
	private int points;
	protected Logger logger;
	private Orangutan myOran;

	public Player()
	{
		logger=new Logger();
	}

	public int getPoints() {
		logger.depthP();
		logger.writeMessage(this.toString()+".getPoints()");
		logger.depthM();
		return points;
	}

	public void setPoints(int points) {

		logger.depthP();
		logger.writeMessage(this.toString()+".setPoints("+points+")");
		this.points = points;
		logger.depthM();
	}

	public Orangutan getMyOran() {
		logger.depthP();
		logger.writeMessage(this.toString()+".getMyOran()");
		logger.depthM();
		return myOran;

	}

	public void setMyOran(Orangutan myOran) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setMyOran()");
		logger.depthM();
		this.myOran = myOran;
	}

	public void givePoints(int i) {
		logger.depthP();
		logger.writeMessage(this.toString()+".givePoints("+i+")");
		points += i;
		logger.depthM();
	}//Pontok hozzáadása
}
