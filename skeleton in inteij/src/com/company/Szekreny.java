package com.company;

public class Szekreny extends Field {

	public void setSzekrenypar(Field szekrenypar) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setSzekrenypar("+szekrenypar.toString()+")");
		this.szekrenypar = szekrenypar;
		logger.depthM();
	}

	private Field szekrenypar;//A szekrény párjának egyik szomszédos mezője
	@Override
	public void stepped(Unit unit) {
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped("+unit.toString()+")");
		if(unit != null)
		{
			if(szekrenypar.getContain() == null)//Ha nem áll senki a szekrényen
			{
				unit.move(szekrenypar);//Belép a szekrénybe
			}
		}
		logger.depthM();
	}


//	public void stepped(Panda p) {
//	}

}
