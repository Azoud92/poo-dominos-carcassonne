package com.proj.poo.model.carcassonne.tuiles.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneQuartierVille;

public class CoteQuartier extends Cote {

	public CoteQuartier() {
		super(3);
		cote[0] = new ZoneQuartierVille();
		cote[1] = new ZoneQuartierVille();
		cote[2] = new ZoneQuartierVille();
		// TODO Auto-generated constructor stub
	}

}
