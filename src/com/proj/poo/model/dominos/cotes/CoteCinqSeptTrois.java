package com.proj.poo.model.dominos.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.dominos.IntegerZoneTuile;

public class CoteCinqSeptTrois extends Cote {

	public CoteCinqSeptTrois() {
		super(3);
		cote[0] = new IntegerZoneTuile(5);
		cote[1] = new IntegerZoneTuile(7);
		cote[2] = new IntegerZoneTuile(3);
	}

}
