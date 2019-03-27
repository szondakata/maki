package com.company;


public class Fotel extends Field {//ez a szekvencia is szar volt *clap clap* kezdem unni
	public void Update() {
		if (getContain()==null)
		{
			for (Field field : getNei()) {
				if (field.getContain()!=null)
				{
					field.getContain().action(this);
				}
			}
		}
	}
}
