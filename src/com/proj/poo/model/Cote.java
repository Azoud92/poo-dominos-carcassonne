package com.proj.poo.model;

public abstract class Cote {

	protected ZoneTuile[] cote;
	public Cote(int nbZones) {
		cote = new ZoneTuile[nbZones];
	}
	
	public ZoneTuile[] getCote() {
		return cote;
	}
	
}
