package com.company;




public class Jatek extends Field {
	public void Update() {
		for (Field field : getNei()) {
			if (field.getContain()!=null)
			{
				field.getContain().action(this);
			}
		}
		if (getContain() != null)
		{
			getContain().action(this);
		}
	}
}
