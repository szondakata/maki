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
	public void stepped(Unit p) {
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped("+p.toString()+")");
		if(p != null)
		{
			if(szekrenypar.getContain() == null)//Ha nem áll senki a szekrényen
			{
				p.move(szekrenypar);//Belép a szekrénybe
			}
		}
		logger.depthM();
	}

}
