package com.company;

public class Szekreny extends Field {

	public void setSzekrenypar(Field szekrenypar_in) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setSzekrenypar("+szekrenypar_in.toString()+")");
		this.szekrenypar = szekrenypar_in.getNei().get(0);
		logger.depthM();
	}

	private Field szekrenypar;//A szekrény párjának egyik szomszédos mezője


	@Override
	public void stepped(Unit p) {
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped("+ (p==null ? "null" : p.toString())+")");
		if(p != null)
		{
			if(szekrenypar.getContain() == null)//Ha nem áll senki a szekrényen
			{
				p.move(szekrenypar);//Belép a szekrénybe
				this.setContain(null);
			}
		}
		logger.depthM();
	}

}
