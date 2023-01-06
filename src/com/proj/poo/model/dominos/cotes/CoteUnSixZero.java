package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteUnSixZero extends Cote {

	public CoteUnSixZero() {
		super(3);
		cote[0] = new IntegerZoneTuile(1);
		cote[1] = new IntegerZoneTuile(6);
		cote[2] = new IntegerZoneTuile(0);
	}

}
