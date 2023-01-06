package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteUnSeptCinq extends Cote {

	public CoteUnSeptCinq() {
		super(3);
		cote[0] = new IntegerZoneTuile(1);
		cote[1] = new IntegerZoneTuile(7);
		cote[2] = new IntegerZoneTuile(5);
	}

}
