package com.company;

import java.util.ArrayList;

public class Exit extends Field {


    Entry entry;


    ArrayList<Unit> talon=new ArrayList<>();
	public void stepped(Orangutan o) {
	    if (o != null) {
	        talon.add(getContain());
            o.exit(entry);
            setContain(null);
        }
	}

    public void stepped(Panda p) {
        if (p != null) {
            talon.add(getContain());
            p.exit(this);
            setContain(null);
        }
    }

    public void setContain(Unit contain) {
        this.stepped(contain);
    }
}
