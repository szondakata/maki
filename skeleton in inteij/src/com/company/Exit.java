package com.company;

public class Exit extends Field {
	public void stepped(Orangutan o) {
	    if (o != null) {
            o.exit();
        }
	}

    public void setContain(Unit contain) {
        this.stepped(contain);
    }
}
