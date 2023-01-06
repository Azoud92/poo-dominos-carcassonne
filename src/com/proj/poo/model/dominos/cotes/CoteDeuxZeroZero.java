package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteDeuxZeroZero extends Cote {

	public CoteDeuxZeroZero() {
		super(3);
		cote[0] = new IntegerZoneTuile(2);
		cote[1] = new IntegerZoneTuile(0);
		cote[2] = new IntegerZoneTuile(0);
	}

}
