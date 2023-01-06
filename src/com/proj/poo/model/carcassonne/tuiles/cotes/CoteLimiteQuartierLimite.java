package com.proj.poo.model.carcassonne.tuiles.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneLimiteVille;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneQuartierVille;

public class CoteLimiteQuartierLimite extends Cote {

	public CoteLimiteQuartierLimite() {
		super(3);
		cote[0] = new ZoneLimiteVille();
		cote[1] = new ZoneQuartierVille();
		cote[2] = new ZoneLimiteVille();
		// TODO Auto-generated constructor stub
	}

}
