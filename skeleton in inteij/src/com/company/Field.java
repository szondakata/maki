package com.company;
import java.util.ArrayList;

public class Field {
	private Unit contain;//A mezőn lévő unit
	protected Logger logger;
	private ArrayList<Field> nei;//A mező szomszédai


	public Field()
	{
		logger=new Logger();
	}

	public Unit getContain() {
		logger.depthP();
		logger.writeMessage(this.toString()+".getContain()");
		logger.depthM();
		return contain;
	}
	public void setContain(Unit contain) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setContain("+(contain==null ? "Null" : contain.toString() )+")");

		this.stepped(contain);
		this.contain = contain;
		logger.depthM();
	}

	public void setNei(ArrayList<Field> nei) {		
		this.nei = nei;
	}
	public ArrayList<Field> getNei() {
		logger.depthP();
		logger.writeMessage(this.toString()+".getNei()");
		logger.depthM();
		return nei;
	}



	public void Update() {
		logger.depthP();
		logger.writeMessage(this.toString()+".Update()");
		logger.depthM();
	}//Nem csinál semmit

	public void stepped(Orangutan u) {
		logger.depthP();
		logger.writeMessage(this.toString()+".Stepped("+(u==null ? "Null":u.toString())+")");
		logger.depthM();
	}//Nem csinál semmit

	public void stepped(Panda p) {
		logger.depthP();
		logger.writeMessage(this.toString()+".Stepped("+(p==null ? "Null":p.toString())+")");
		logger.depthM();
	}//Nem csinál semmit



	public void addNei(Field f){
		logger.depthP();
		logger.writeMessage(this.toString()+".addNei("+f.toString()+")");

		nei.add(f);
		if(!f.getNei().contains(this)) f.addNei(this); //igy jo
		logger.depthM();
	}

}
