package com.proj.poo.model.carcassonne.tuiles.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneLimiteVille;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;

public class CoteLimitePrePre extends Cote {

	public CoteLimitePrePre() {
		super(3);
		cote[0] = new ZoneLimiteVille();
		cote[1] = new ZonePre();
		cote[2] = new ZonePre();
		// TODO Auto-generated constructor stub
	}

}
