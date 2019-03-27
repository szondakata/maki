package com.company;

public class Szekreny extends Field {

	private Szekreny szekrenypar;

	public void stepped(Orangutan o) {//ebből mért van kettő
		//ésmegint totál értelmetlen a szekvencia
		if(o != null)
		{
			if(szekrenypar.getContain() == null)
			{
				o.move(szekrenypar);
				setContain(o);
			}
		}
	}
	
	public void stepped(Panda p) {
	}
}
