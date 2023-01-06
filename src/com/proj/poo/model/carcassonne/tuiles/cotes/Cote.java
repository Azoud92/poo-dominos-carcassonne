package com.proj.poo.model.carcassonne.tuiles.cotes;

import com.proj.poo.model.ZoneTuile;

public abstract class Cote {

	protected ZoneTuile[] cote;
	public Cote(int nbZones) {
		cote = new ZoneTuile[nbZones];
	}
	
	public ZoneTuile[] getCote() {
		return cote;
	}
	
}
