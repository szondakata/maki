package com.company;

import java.util.ArrayList;

public class Exit extends Field {


    private Entry entry;


    ArrayList<Unit> talon=new ArrayList<>();
	public void stepped(Orangutan o) {
	    logger.depthP();
            logger.writeMessage(this.toString()+".stepped("+o.toString()+")");
	    if (o != null) {
	        talon.add(getContain());
            	o.exit(entry);
            setContain(null);
            }
	    logger.depthM();
	}

    public void stepped(Panda p) {
	logger.depthP();
	logger.writeMessage(this.toString()+".stepped("+p.toString()+")");
        if (p != null) {
            entry.getContain().die();
            talon.add(getContain());
            p.exit(this);
            setContain(null);
        }
	logger.depthM();
    }

    public void setContain(Unit contain) {
	logger.depthP();
	logger.writeMessage(this.toString()+".setContain("+contain.toString()+")");
        this.stepped(contain);
	logger.depthM();
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
