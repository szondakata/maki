package com.company;


public abstract class Unit implements Action {
	public Field getIamon() {
		return iamon;
	}

	public void setIamon(Field iamon) {
		this.iamon = iamon;
	}

	private Field iamon;//nemár srácok ezt átneveztem

	public Unit getHand1() {
		return hand1;
	}

	public void setHand1(Unit hand1) {
		this.hand1 = hand1;
	}

	public Unit getHand2() {
		return hand2;
	}

	public void setHand2(Unit hand2) {
		this.hand2 = hand2;
	}

	private Unit hand1;
	private Unit hand2;

    public boolean move(Field f)//Booleanra átírtam a control miatt
    {
        if (f.getContain()==null)
        {
            f.setContain(this);
            Field temp = this.getIamon();
            temp.setContain(null);
            if (this.getHand1() != null)
            {
                this.getHand1().move(temp);
            }
            temp = null;
            this.setIamon(f);
            return true;
        }
        else{return  false;}
    }
	
	public void grab(Unit u) {//TODO unit:grab
	}
	
	public void exit() {//TODO unit:exit
	}
	
	public void die() {//TODO unit:die
	}
	
	public void release() {
        if(getHand1() != null)
        {
            getHand1().setHand2(null);
            setHand1(null);
        }
        if(getHand2() != null)
        {
            getHand2().release();
        }
	}
}
