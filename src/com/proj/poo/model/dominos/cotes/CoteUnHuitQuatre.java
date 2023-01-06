package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteUnHuitQuatre extends Cote {

	public CoteUnHuitQuatre() {
		super(3);
		cote[0] = new IntegerZoneTuile(1);
		cote[1] = new IntegerZoneTuile(8);
		cote[2] = new IntegerZoneTuile(4);
	}

}
