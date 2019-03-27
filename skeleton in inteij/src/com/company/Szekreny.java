package com.company;

public class Szekreny extends Field {

	public void setSzekrenypar(Field szekrenypar) {
		this.szekrenypar = szekrenypar;
	}

	private Field szekrenypar;//A szekrény párjának egyik szomszédos mezője
	@Override
	public void stepped(Unit unit) {
		if(unit != null)
		{
			if(szekrenypar.getContain() == null)//Ha nem áll senki a szekrényen
			{
				unit.move(szekrenypar);//Belép a szekrénybe
			}
		}
	}

	public void stepped(Panda p) {
	}
}
