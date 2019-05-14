package com.company;

/**
 * A szekrenyt megvalosito osztaly
 */
public class Szekreny extends Field {

	/** Beallitja a szekreny parjat
	 * @param szekrenypar masik szekreny
	 */
	public void setSzekrenypar(Field szekrenypar) {
		logger.depthP();
		logger.writeMessage(this.toString()+".setSzekrenypar("+szekrenypar.toString()+")");
		this.szekrenypar = szekrenypar;
		logger.depthM();
	}

	public Field getSzekrenypar() {
		return szekrenypar;
	}

	private Field szekrenypar;


	/** A parameterben kapott egysegeket a szekrenypar ele pakolo fuggveny
	 * @param p egyseg
	 */
	@Override
	public void stepped(Unit p) {
		logger.depthP();
		logger.writeMessage(this.toString()+".stepped("+p.toString()+")");
		if(p != null)
		{
			if(szekrenypar==null){
				System.out.println("A "+this.ID+ " szekrénynek nincs párja");
				return;
			}
			if(szekrenypar.getContain() == null)//Ha nem áll senki a szekrényen
			{


				p.move(szekrenypar);//Belép a szekrénybe
			}
		}
		logger.depthM();
	}

}
