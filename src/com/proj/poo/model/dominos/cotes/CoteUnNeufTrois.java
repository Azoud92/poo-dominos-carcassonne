package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteUnNeufTrois extends Cote {

	public CoteUnNeufTrois() {
		super(3);
		cote[0] = new IntegerZoneTuile(1);
		cote[1] = new IntegerZoneTuile(9);
		cote[2] = new IntegerZoneTuile(3);
		// TODO Auto-generated constructor stub
	}

}
