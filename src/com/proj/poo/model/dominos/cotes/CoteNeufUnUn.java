package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteNeufUnUn extends Cote {

	public CoteNeufUnUn() {
		super(3);
		cote[0] = new IntegerZoneTuile(9);
		cote[1] = new IntegerZoneTuile(1);
		cote[2] = new IntegerZoneTuile(1);
	}

}
