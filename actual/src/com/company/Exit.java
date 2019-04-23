package com.company;

import java.util.ArrayList;

public class Exit extends Field {


    ArrayList<Unit> talon=new ArrayList<>();

    @Override
    public void stepped(Unit p) {
		logger.depthP();
        logger.writeMessage(this.toString()+".stepped("+(p==null ? "Null" : p.toString())+")");
        if (p != null) {
            talon.add(getContain());
            p.exit(this);
            setContain(null);
        }
		logger.depthM();
    }

    public void setContain(Unit contain) {
        logger.depthP();
	    logger.writeMessage(this.toString()+".setContain("+(contain==null ? "Null" : contain.toString())+")");
        this.stepped(contain);
        logger.depthM();
    }

}
