package com.proj.poo.model.carcassonne.tuiles.cotes;

import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;

public class CotePre extends Cote {

	public CotePre() {
		super(3);
		cote[0] = new ZonePre();
		cote[1] = new ZonePre();
		cote[2] = new ZonePre();
	}

}
