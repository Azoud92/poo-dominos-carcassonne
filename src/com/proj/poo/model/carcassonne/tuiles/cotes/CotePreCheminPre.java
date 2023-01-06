package com.proj.poo.model.carcassonne.tuiles.cotes;

import com.proj.poo.model.Cote;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneChemin;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;

public class CotePreCheminPre extends Cote {

	public CotePreCheminPre() {
		super(3);
		cote[0] = new ZonePre();
		cote[1] = new ZoneChemin();
		cote[2] = new ZonePre();
	}
	
}
